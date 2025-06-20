package com.phoen1x.datagen;

import com.phoen1x.block.ModBlocks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider{
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModBlocks.RED_LANTERN_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.YELLOW_LANTERN_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.GREEN_LANTERN_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.ORANGE_LANTERN_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.LIME_LANTERN_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.CYAN_LANTERN_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.BLUE_LANTERN_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.LIGHT_BLUE_LANTERN_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.PURPLE_LANTERN_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.MAGENTA_LANTERN_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.PINK_LANTERN_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.BROWN_LANTERN_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.BLACK_LANTERN_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.DARK_GRAY_LANTERN_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.LIGHT_GRAY_LANTERN_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.WHITE_LANTERN_ITEM, Models.GENERATED);
    }
}
