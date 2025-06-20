package com.phoen1x.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

import static com.phoen1x.block.ModBlocks.*;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(RED_LANTERN)
                .add(YELLOW_LANTERN)
                .add(GREEN_LANTERN)
                .add(ORANGE_LANTERN)
                .add(LIME_LANTERN)
                .add(CYAN_LANTERN)
                .add(BLUE_LANTERN)
                .add(LIGHT_BLUE_LANTERN)
                .add(PURPLE_LANTERN)
                .add(MAGENTA_LANTERN)
                .add(PINK_LANTERN)
                .add(BROWN_LANTERN)
                .add(BLACK_LANTERN)
                .add(DARK_GRAY_LANTERN)
                .add(LIGHT_GRAY_LANTERN)
                .add(WHITE_LANTERN);
    }
}
