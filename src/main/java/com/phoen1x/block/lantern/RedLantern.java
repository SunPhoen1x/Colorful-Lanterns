package com.phoen1x.block.lantern;

import com.mojang.serialization.MapCodec;
import com.phoen1x.ColoredLanterns;
import com.phoen1x.entity.RedLanternBlockEntity;
import com.phoen1x.utils.TransparentTripWire;
import eu.pb4.factorytools.api.block.FactoryBlock;
import eu.pb4.factorytools.api.virtualentity.BlockModel;
import eu.pb4.factorytools.api.virtualentity.ItemDisplayElementUtil;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.elements.ItemDisplayElement;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import xyz.nucleoid.packettweaker.PacketContext;

public class RedLantern extends BlockWithEntity implements TransparentTripWire, FactoryBlock, BlockEntityProvider {
    public static final BooleanProperty HANGING = Properties.HANGING;
    public static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;
    public static final EnumProperty<ModelType> MODEL_TYPE = EnumProperty.of("model_type", ModelType.class);
    public static final MapCodec<RedLantern> CODEC = createCodec(RedLantern::new);
    private RedLantern.Model model;

    public enum ModelType implements StringIdentifiable {
        STANDING("standing"),
        HANGING("hanging"),
        WALL("wall");

        private final String name;

        ModelType(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return this.name;
        }
    }

    public RedLantern(Settings settings) {
        super(settings
                .nonOpaque()
                .luminance(state -> 15)
        );
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(HANGING, false)
                .with(FACING, Direction.NORTH)
                .with(MODEL_TYPE, ModelType.STANDING));
    }

    protected RedLantern(Settings settings, boolean isSubclass) {
        super(settings
                .nonOpaque()
                .luminance(state -> 15)
        );
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(HANGING, false)
                .with(FACING, Direction.NORTH)
                .with(MODEL_TYPE, ModelType.STANDING));
    }


    @Override
    public BlockState getPolymerBreakEventBlockState(BlockState state, PacketContext context) {
        return Blocks.LANTERN.getDefaultState();
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        Direction playerHorizontalFacing = ctx.getHorizontalPlayerFacing().getOpposite();

        Direction clickedFace = ctx.getSide();

        // 1. Прикріплення до стіни (якщо клікнули на горизонтальну грань)
        if (clickedFace.getAxis().isHorizontal()) {
            BlockPos wallPos = pos.offset(clickedFace.getOpposite());
            if (world.getBlockState(wallPos).isSideSolidFullSquare(world, wallPos, clickedFace)) {
                return this.getDefaultState()
                        .with(HANGING, false)
                        .with(FACING, clickedFace)
                        .with(MODEL_TYPE, ModelType.WALL);
            }
        }
        // 2. Підвішування (якщо клікнули на нижню грань)
        else if (clickedFace == Direction.DOWN) {
            BlockState stateAbove = world.getBlockState(pos.up());
            if (stateAbove.isSolidBlock(world, pos.up()) ||
                    stateAbove.getBlock() == Blocks.CHAIN ||
                    stateAbove.isOf(Blocks.LANTERN) ||
                    (stateAbove.getBlock() instanceof RedLantern && !stateAbove.get(HANGING))) {
                return this.getDefaultState()
                        .with(HANGING, true)
                        .with(FACING, playerHorizontalFacing)
                        .with(MODEL_TYPE, ModelType.HANGING);
            }
        }

        // 3. Стоячий ліхтар (якщо жоден з попередніх варіантів не спрацював)
        if (world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP)) {
            return this.getDefaultState()
                    .with(HANGING, false)
                    .with(FACING, playerHorizontalFacing)
                    .with(MODEL_TYPE, ModelType.STANDING);
        }

        return null;
    }


    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        ModelType modelType = state.get(MODEL_TYPE);

        if (modelType == ModelType.HANGING) {
            // Висячий ліхтар перевіряє тільки опору зверху
            BlockState stateAbove = world.getBlockState(pos.up());
            return stateAbove.isSolidBlock(world, pos.up()) ||
                    stateAbove.getBlock() == Blocks.CHAIN ||
                    stateAbove.isOf(Blocks.LANTERN) ||
                    (stateAbove.getBlock() instanceof RedLantern && !stateAbove.get(HANGING));
        } else if (modelType == ModelType.WALL) {
            // Настінний ліхтар перевіряє тільки опору збоку, за напрямком FACING
            Direction facing = state.get(FACING);
            BlockPos supportPos = pos.offset(facing.getOpposite());
            return world.getBlockState(supportPos).isSideSolidFullSquare(world, supportPos, facing);
        } else { // ModelType.STANDING
            // Стоячий ліхтар перевіряє тільки опору знизу
            return world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP);
        }
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        // Запланувати тік для перевірки опори
        if (!this.canPlaceAt(state, world, pos)) {
            // Перетворення WorldView на World безпечне у цьому контексті для scheduleBlockTick на сервері.
            if (world instanceof World) {
                ((World)world).scheduleBlockTick(pos, this, 1);
            }
        }
        // Викликаємо батьківський метод. Цей метод не видаляє блок сам по собі,
        // а лише інформує про зміни в сусідніх блоках.
        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // Цей метод викликається після запланованого тіка
        if (!this.canPlaceAt(state, world, pos)) {
            // Якщо блок більше не може бути розміщений (втратив опору), руйнуємо його.
            world.breakBlock(pos, true);
        }
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        boolean isHanging = state.get(HANGING);
        return Blocks.LANTERN.getDefaultState().with(net.minecraft.state.property.Properties.HANGING, isHanging);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RedLanternBlockEntity(pos, state);
    }

    @Override
    protected void onStateReplaced(BlockState state, ServerWorld world, BlockPos pos, boolean moved) {
        // Якщо блок замінюється іншим або руйнується, викидаємо предмети
        ItemScatterer.onStateReplaced(state, world, pos);
        super.onStateReplaced(state, world, pos, moved);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HANGING, FACING, MODEL_TYPE);
        super.appendProperties(builder);
    }

    @Override
    public @Nullable ElementHolder createElementHolder(ServerWorld world, BlockPos pos, BlockState initialBlockState) {
        model = createModel(initialBlockState, world, pos);
        return model;
    }

    @Override
    public boolean tickElementHolder(ServerWorld world, BlockPos pos, BlockState initialBlockState) {
        this.model.tick();
        return true;
    }

    protected RedLantern.Model createModel(BlockState initialBlockState, ServerWorld world, BlockPos pos) {
        return new RedLantern.Model(initialBlockState, world, pos);
    }

    public static class Model extends BlockModel {
        public static final ItemStack STANDING_MODEL = ItemDisplayElementUtil.getModel(Identifier.of(ColoredLanterns.MOD_ID, "block/red_lantern"));
        public static final ItemStack HANGING_MODEL = ItemDisplayElementUtil.getModel(Identifier.of(ColoredLanterns.MOD_ID, "block/red_hanging_lantern"));
        public static final ItemStack WALL_MODEL = ItemDisplayElementUtil.getModel(Identifier.of(ColoredLanterns.MOD_ID, "block/red_wall_lantern"));
        public ItemDisplayElement lantern;
        public ServerWorld world;
        public BlockPos pos;

        public Model(BlockState state, ServerWorld world, BlockPos pos) {
            this.world = world;
            this.pos = pos;
            init(state);
        }

        public void init(BlockState state) {
            ModelType modelType = state.get(MODEL_TYPE);
            Direction facing = state.get(FACING);

            ItemStack model = switch (modelType) {
                case HANGING -> HANGING_MODEL;
                case WALL -> WALL_MODEL;
                case STANDING -> STANDING_MODEL;
            };

            this.lantern = ItemDisplayElementUtil.createSimple(model);
            this.lantern.setScale(new Vector3f(2f));

            if (modelType == ModelType.WALL) {
                float yaw = facing.getPositiveHorizontalDegrees();
                this.lantern.setYaw(yaw);
            }
            this.addElement(lantern);
        }
    }
}