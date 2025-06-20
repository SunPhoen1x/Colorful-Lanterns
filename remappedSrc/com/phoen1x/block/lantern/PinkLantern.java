package com.phoen1x.block.lantern;

import com.mojang.serialization.MapCodec;
import com.phoen1x.ColoredLanterns;
import com.phoen1x.entity.PinkLanternBlockEntity;
import eu.pb4.factorytools.api.resourcepack.BaseItemProvider;
import eu.pb4.factorytools.api.virtualentity.ItemDisplayElementUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class PinkLantern extends RedLantern {
    public static final MapCodec<PinkLantern> CODEC = createCodec(PinkLantern::new);

    public PinkLantern(Settings settings) {
        super(settings, true);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PinkLanternBlockEntity(pos, state);
    }

    @Override
    protected RedLantern.Model createModel(BlockState initialBlockState, ServerWorld world, BlockPos pos) {
        return new PinkLantern.Model(initialBlockState, world, pos);
    }

    public static final class Model extends RedLantern.Model {
        public static final ItemStack STANDING_MODEL = BaseItemProvider.requestModel(Identifier.of(ColoredLanterns.MOD_ID, "block/pink_lantern"));
        public static final ItemStack HANGING_MODEL = BaseItemProvider.requestModel(Identifier.of(ColoredLanterns.MOD_ID, "block/pink_hanging_lantern"));
        public static final ItemStack WALL_MODEL = BaseItemProvider.requestModel(Identifier.of(ColoredLanterns.MOD_ID, "block/pink_wall_lantern"));

        public Model(BlockState state, ServerWorld world, BlockPos pos) {
            super(state, world, pos);
        }

        @Override
        public void init(BlockState state) {
            ModelType modelType = state.get(MODEL_TYPE);

            ItemStack model = switch (modelType) {
                case HANGING -> HANGING_MODEL;
                case WALL -> WALL_MODEL;
                case STANDING -> STANDING_MODEL;
            };

            this.lantern = ItemDisplayElementUtil.createSimple(model);
            this.lantern.setScale(new Vector3f(2f));

            if (modelType == ModelType.WALL) {
                float yaw = state.get(FACING).getHorizontalQuarterTurns() * 90;
                this.lantern.setYaw(yaw);
            }
            this.addElement(lantern);
        }
    }
}