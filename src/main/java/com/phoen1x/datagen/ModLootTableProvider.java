package com.phoen1x.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static com.phoen1x.block.ModBlocks.*;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }
    @Override
    public void generate() {
        addDrop(RED_LANTERN);
        addDrop(YELLOW_LANTERN);
        addDrop(GREEN_LANTERN);
        addDrop(ORANGE_LANTERN);
        addDrop(LIME_LANTERN);
        addDrop(CYAN_LANTERN);
        addDrop(BLUE_LANTERN);
        addDrop(LIGHT_BLUE_LANTERN);
        addDrop(PURPLE_LANTERN);
        addDrop(MAGENTA_LANTERN);
        addDrop(PINK_LANTERN);
        addDrop(BROWN_LANTERN);
        addDrop(BLACK_LANTERN);
        addDrop(DARK_GRAY_LANTERN);
        addDrop(LIGHT_GRAY_LANTERN);
        addDrop(WHITE_LANTERN);
    }
}
