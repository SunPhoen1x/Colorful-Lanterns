package com.phoen1x.entity;

import com.phoen1x.ColoredLanterns;
import com.phoen1x.block.ModBlocks;
import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final BlockEntityType<RedLanternBlockEntity> RED_LANTERN = register("red_lantern", BlockEntityType.Builder.create(RedLanternBlockEntity::new, ModBlocks.RED_LANTERN));
    public static final BlockEntityType<YellowLanternBlockEntity> YELLOW_LANTERN = register("yellow_lantern", BlockEntityType.Builder.create(YellowLanternBlockEntity::new, ModBlocks.YELLOW_LANTERN));
    public static final BlockEntityType<GreenLanternBlockEntity> GREEN_LANTERN = register("green_lantern", BlockEntityType.Builder.create(GreenLanternBlockEntity::new, ModBlocks.GREEN_LANTERN));
    public static final BlockEntityType<OrangeLanternBlockEntity> ORANGE_LANTERN = register("orange_lantern", BlockEntityType.Builder.create(OrangeLanternBlockEntity::new, ModBlocks.ORANGE_LANTERN));
    public static final BlockEntityType<LimeLanternBlockEntity> LIME_LANTERN = register("lime_lantern", BlockEntityType.Builder.create(LimeLanternBlockEntity::new, ModBlocks.LIME_LANTERN));
    public static final BlockEntityType<CyanLanternBlockEntity> CYAN_LANTERN = register("cyan_lantern", BlockEntityType.Builder.create(CyanLanternBlockEntity::new, ModBlocks.CYAN_LANTERN));
    public static final BlockEntityType<BlueLanternBlockEntity> BLUE_LANTERN = register("blue_lantern", BlockEntityType.Builder.create(BlueLanternBlockEntity::new, ModBlocks.BLUE_LANTERN));
    public static final BlockEntityType<LightBlueLanternBlockEntity> LIGHT_BLUE_LANTERN = register("light_blue_lantern", BlockEntityType.Builder.create(LightBlueLanternBlockEntity::new, ModBlocks.LIGHT_BLUE_LANTERN));
    public static final BlockEntityType<PurpleLanternBlockEntity> PURPLE_LANTERN = register("purple_lantern", BlockEntityType.Builder.create(PurpleLanternBlockEntity::new, ModBlocks.PURPLE_LANTERN));
    public static final BlockEntityType<MagentaLanternBlockEntity> MAGENTA_LANTERN = register("magenta_lantern", BlockEntityType.Builder.create(MagentaLanternBlockEntity::new, ModBlocks.MAGENTA_LANTERN));
    public static final BlockEntityType<PinkLanternBlockEntity> PINK_LANTERN = register("pink_lantern", BlockEntityType.Builder.create(PinkLanternBlockEntity::new, ModBlocks.PINK_LANTERN));
    public static final BlockEntityType<BrownLanternBlockEntity> BROWN_LANTERN = register("brown_lantern", BlockEntityType.Builder.create(BrownLanternBlockEntity::new, ModBlocks.BROWN_LANTERN));
    public static final BlockEntityType<BlackLanternBlockEntity> BLACK_LANTERN = register("black_lantern", BlockEntityType.Builder.create(BlackLanternBlockEntity::new, ModBlocks.BLACK_LANTERN));
    public static final BlockEntityType<DarkGrayLanternBlockEntity> DARK_GRAY_LANTERN = register("dark_gray_lantern", BlockEntityType.Builder.create(DarkGrayLanternBlockEntity::new, ModBlocks.DARK_GRAY_LANTERN));
    public static final BlockEntityType<LightGrayLanternBlockEntity> LIGHT_GRAY_LANTERN = register("light_gray_lantern", BlockEntityType.Builder.create(LightGrayLanternBlockEntity::new, ModBlocks.LIGHT_GRAY_LANTERN));
    public static final BlockEntityType<WhiteLanternBlockEntity> WHITE_LANTERN = register("white_lantern", BlockEntityType.Builder.create(WhiteLanternBlockEntity::new, ModBlocks.WHITE_LANTERN));
    public static void register() {}

    public static <T extends BlockEntity> BlockEntityType<T> register(String path, BlockEntityType.Builder<T> builder) {
        BlockEntityType<T> blockEntityType = Registry.register(Registries.BLOCK_ENTITY_TYPE,Identifier.of(ColoredLanterns.MOD_ID, path), builder.build());

        PolymerBlockUtils.registerBlockEntity(blockEntityType);

        return blockEntityType;
    }
}
