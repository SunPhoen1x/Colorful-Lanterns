package com.phoen1x.block.lantern;

import com.mojang.serialization.MapCodec;
import com.phoen1x.ColoredLanterns;
import com.phoen1x.entity.RedLanternBlockEntity;
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
    public BlockState getPolymerBreakEventBlockState(BlockState state, ServerPlayerEntity player) {
        return Blocks.LANTERN.getDefaultState();
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        Direction playerFacing = ctx.getHorizontalPlayerFacing().getOpposite();

        if (ctx.getSide() == Direction.DOWN) {
            BlockState hangingState = this.getDefaultState()
                    .with(HANGING, true)
                    .with(FACING, playerFacing)
                    .with(MODEL_TYPE, ModelType.HANGING);
            if (hangingState.canPlaceAt(world, pos)) {
                return hangingState;
            }
        }

        BlockState standingState = this.getDefaultState()
                .with(HANGING, false)
                .with(FACING, playerFacing)
                .with(MODEL_TYPE, ModelType.STANDING);
        if (standingState.canPlaceAt(world, pos)) {
            return standingState;
        }

        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction.getAxis().isHorizontal()) {
                BlockState wallState = this.getDefaultState()
                        .with(HANGING, false)
                        .with(FACING, direction)
                        .with(MODEL_TYPE, ModelType.WALL);
                if (wallState.canPlaceAt(world, pos)) {
                    return wallState;
                }
            }
        }
        return null;
    }


    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        ModelType modelType = state.get(MODEL_TYPE);
        Direction facing = state.get(FACING);

        if (modelType == ModelType.HANGING) {
            return Block.sideCoversSmallSquare(world, pos.up(), Direction.DOWN);
        } else if (modelType == ModelType.STANDING) {
            return Block.sideCoversSmallSquare(world, pos.down(), Direction.UP);
        } else if (modelType == ModelType.WALL) {
            return Block.sideCoversSmallSquare(world, pos.offset(facing.getOpposite()), facing);
        }
        return false;
    }

    protected static Direction getAttachmentDirection(BlockState state) {
        ModelType modelType = state.get(MODEL_TYPE);
        if (modelType == ModelType.HANGING) {
            return Direction.DOWN;
        } else if (modelType == ModelType.STANDING) {
            return Direction.UP;
        } else if (modelType == ModelType.WALL) {
            return state.get(FACING);
        }
        return Direction.NORTH;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        }
        super.neighborUpdate(state, world, pos, block, fromPos, notify);
    }


    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        boolean isHanging = state.get(HANGING);
        return Blocks.LANTERN.getDefaultState().with(net.minecraft.state.property.Properties.HANGING, isHanging);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RedLanternBlockEntity(pos, state);
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            ItemScatterer.onStateReplaced(state, newState, world, pos);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
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