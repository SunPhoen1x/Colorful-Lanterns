package com.phoen1x.block.lantern;

import com.mojang.serialization.MapCodec;
import com.phoen1x.ColoredLanterns;
import com.phoen1x.entity.LightBlueLanternBlockEntity;
import com.phoen1x.utils.TransparentBlocks.TransparentTripWire;
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
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class LightBlueLantern extends BlockWithEntity implements TransparentTripWire, FactoryBlock, BlockEntityProvider {
    public static final BooleanProperty HANGING = Properties.HANGING;
    public static final MapCodec<LightBlueLantern> CODEC = createCodec(LightBlueLantern::new);
    private Model model;

    public LightBlueLantern(Settings settings) {
        super(settings
                .nonOpaque()
                .luminance(state -> 15)
        );
        this.setDefaultState(this.stateManager.getDefaultState().with(HANGING, false));
    }

    @Override
    public BlockState getPolymerBreakEventBlockState(BlockState state, ServerPlayerEntity player) {
        return Blocks.LANTERN.getDefaultState();
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();

        boolean canHang = world.getBlockState(pos.up()).isSolidBlock(world, pos.up());

        if (ctx.getSide() == Direction.DOWN && canHang) {
            return this.getDefaultState().with(HANGING, true);
        }
        else if (world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP)) {
            return this.getDefaultState().with(HANGING, false);
        }
        return null;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (state.get(HANGING)) {
            return world.getBlockState(pos.up()).isSolidBlock(world, pos.up());
        }
        return world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP);
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
        return new LightBlueLanternBlockEntity(pos, state);
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        ItemScatterer.onStateReplaced(state, newState, world, pos);
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HANGING);
        super.appendProperties(builder);
    }

    @Override
    public @Nullable ElementHolder createElementHolder(ServerWorld world, BlockPos pos, BlockState initialBlockState) {
        model = new Model(initialBlockState, world, pos);
        return model;
    }

    @Override
    public boolean tickElementHolder(ServerWorld world, BlockPos pos, BlockState initialBlockState) {
        this.model.tick();
        return true;
    }

    public static final class Model extends BlockModel {
        public static final ItemStack STANDING_MODEL = BaseItemProvider.requestModel(Identifier.of(ColoredLanterns.MOD_ID, "block/light_blue_lantern"));
        public static final ItemStack HANGING_MODEL = BaseItemProvider.requestModel(Identifier.of(ColoredLanterns.MOD_ID, "block/light_blue_hanging_lantern"));
        public ItemDisplayElement lantern;
        public ServerWorld world;
        public BlockPos pos;

        public Model(BlockState state, ServerWorld world, BlockPos pos) {
            this.world = world;
            this.pos = pos;
            init(state);
        }

        public void init(BlockState state) {
            boolean hanging = state.get(HANGING);
            this.lantern = ItemDisplayElementUtil.createSimple(hanging ? HANGING_MODEL : STANDING_MODEL);
            this.lantern.setScale(new Vector3f(2f));
            this.addElement(lantern);
        }
    }
}