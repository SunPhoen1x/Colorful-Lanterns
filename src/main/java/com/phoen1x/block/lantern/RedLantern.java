package com.phoen1x.block.lantern;

import com.mojang.serialization.MapCodec;
import com.phoen1x.ColoredLanterns;
import com.phoen1x.entity.RedLanternBlockEntity;
import com.phoen1x.entity.LimeLanternBlockEntity;
import com.phoen1x.utils.TransparentTripWire;
import eu.pb4.factorytools.api.block.FactoryBlock;
import eu.pb4.factorytools.api.resourcepack.BaseItemProvider;
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
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class RedLantern extends BlockWithEntity implements TransparentTripWire, FactoryBlock, BlockEntityProvider {
    public static final BooleanProperty HANGING = Properties.HANGING;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
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

    @Override
    public BlockState getPolymerBreakEventBlockState(BlockState state, ServerPlayerEntity player) {
        return Blocks.LANTERN.getDefaultState();
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();

        BlockState stateAbove = world.getBlockState(pos.up());
        boolean canHang = stateAbove.isSolidBlock(world, pos.up()) ||
                stateAbove.getBlock() == Blocks.CHAIN ||
                (stateAbove.getBlock() instanceof LimeLantern && !stateAbove.get(HANGING));

        if (ctx.getSide() == Direction.DOWN && canHang) {
            return this.getDefaultState()
                    .with(HANGING, true)
                    .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                    .with(MODEL_TYPE, ModelType.HANGING);
        } else if (world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP)) {
            return this.getDefaultState()
                    .with(HANGING, false)
                    .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                    .with(MODEL_TYPE, ModelType.STANDING);
        } else if (ctx.getSide().getHorizontal() != -1) {
            BlockPos wallPos = pos.offset(ctx.getSide().getOpposite());
            Direction facing = ctx.getSide();
            if (world.getBlockState(wallPos).isSideSolidFullSquare(world, wallPos, facing)) {
                return this.getDefaultState()
                        .with(HANGING, false)
                        .with(FACING, facing)
                        .with(MODEL_TYPE, ModelType.WALL);
            }
        }
        return null;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (state.get(HANGING)) {
            BlockState stateAbove = world.getBlockState(pos.up());
            return stateAbove.isSolidBlock(world, pos.up()) ||
                    stateAbove.getBlock() == Blocks.CHAIN ||
                    (stateAbove.getBlock() instanceof LimeLantern && !stateAbove.get(HANGING));
        }

        Direction facing = state.get(FACING);
        BlockPos supportPos = pos.offset(facing.getOpposite());
        BlockState downState = world.getBlockState(pos.down());

        if (downState.isSideSolidFullSquare(world, pos.down(), Direction.UP)) {
            return true;
        }
        return world.getBlockState(supportPos).isSideSolidFullSquare(world, supportPos, facing);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.get(HANGING) && world.getBlockState(pos.down()).getBlock() != this) {
            Direction facing = state.get(FACING);
            BlockPos supportPos = pos.offset(facing.getOpposite());
            boolean isWallMounted = !world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP) &&
                    world.getBlockState(supportPos).isSideSolidFullSquare(world, supportPos, facing);
            if (isWallMounted &&
                    (direction == Direction.UP ||
                            (direction.getHorizontal() != -1 && direction != facing.getOpposite())) &&
                    neighborState.isSolidBlock(world, neighborPos)) {
                ItemStack droppedItem = neighborState.getBlock().getPickStack(world, neighborPos, neighborState);
                world.setBlockState(neighborPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
                if (world instanceof ServerWorld serverWorld && !droppedItem.isEmpty()) {
                    ItemScatterer.spawn(serverWorld, neighborPos.getX() + 0.5, neighborPos.getY() + 0.5, neighborPos.getZ() + 0.5, droppedItem);
                }
                return state;
            }
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (state.get(HANGING)) {
            BlockState stateAbove = world.getBlockState(pos.up());
            if (!stateAbove.isSolidBlock(world, pos.up()) &&
                    stateAbove.getBlock() != Blocks.CHAIN &&
                    !(stateAbove.getBlock() instanceof LimeLantern && !stateAbove.get(HANGING))) {
                world.breakBlock(pos, true);
            }
        } else {
            Direction facing = state.get(FACING);
            BlockPos supportPos = pos.offset(facing.getOpposite());
            if (!world.getBlockState(supportPos).isSideSolidFullSquare(world, supportPos, facing) &&
                    !world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP)) {
                world.breakBlock(pos, true);
            }
        }
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return Blocks.BARRIER.getDefaultState();
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RedLanternBlockEntity(pos, state);
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        ItemScatterer.onStateReplaced(state, newState, world, pos);
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HANGING, FACING, MODEL_TYPE);
        super.appendProperties(builder);
    }

    @Override
    public @Nullable ElementHolder createElementHolder(ServerWorld world, BlockPos pos, BlockState initialBlockState) {
        model = new RedLantern.Model(initialBlockState, world, pos);
        return model;
    }

    @Override
    public boolean tickElementHolder(ServerWorld world, BlockPos pos, BlockState initialBlockState) {
        this.model.tick();
        return true;
    }

    public static final class Model extends BlockModel {
        public static final ItemStack STANDING_MODEL = BaseItemProvider.requestModel(Identifier.of(ColoredLanterns.MOD_ID, "block/red_lantern"));
        public static final ItemStack HANGING_MODEL = BaseItemProvider.requestModel(Identifier.of(ColoredLanterns.MOD_ID, "block/red_hanging_lantern"));
        public static final ItemStack WALL_MODEL = BaseItemProvider.requestModel(Identifier.of(ColoredLanterns.MOD_ID, "block/red_wall_lantern"));
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
                float yaw = facing.getHorizontal() * 90;
                this.lantern.setYaw(yaw);
            }
            this.addElement(lantern);
        }
    }
}