package com.phoen1x.datagen;

import com.phoen1x.ColoredLanterns;
import com.phoen1x.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        lanternRecipe(ModBlocks.RED_LANTERN_ITEM, Items.RED_DYE, exporter);
        lanternRecipe(ModBlocks.YELLOW_LANTERN_ITEM, Items.YELLOW_DYE, exporter);
        lanternRecipe(ModBlocks.GREEN_LANTERN_ITEM, Items.GREEN_DYE, exporter);
        lanternRecipe(ModBlocks.ORANGE_LANTERN_ITEM, Items.ORANGE_DYE, exporter);
        lanternRecipe(ModBlocks.LIME_LANTERN_ITEM, Items.LIME_DYE, exporter);
        lanternRecipe(ModBlocks.CYAN_LANTERN_ITEM, Items.CYAN_DYE, exporter);
        lanternRecipe(ModBlocks.BLUE_LANTERN_ITEM, Items.BLUE_DYE, exporter);
        lanternRecipe(ModBlocks.LIGHT_BLUE_LANTERN_ITEM, Items.LIGHT_BLUE_DYE, exporter);
        lanternRecipe(ModBlocks.PURPLE_LANTERN_ITEM, Items.PURPLE_DYE, exporter);
        lanternRecipe(ModBlocks.MAGENTA_LANTERN_ITEM, Items.MAGENTA_DYE, exporter);
        lanternRecipe(ModBlocks.PINK_LANTERN_ITEM, Items.PINK_DYE, exporter);
        lanternRecipe(ModBlocks.BROWN_LANTERN_ITEM, Items.BROWN_DYE, exporter);
        lanternRecipe(ModBlocks.BLACK_LANTERN_ITEM, Items.BLACK_DYE, exporter);
        lanternRecipe(ModBlocks.DARK_GRAY_LANTERN_ITEM, Items.GRAY_DYE, exporter);
        lanternRecipe(ModBlocks.LIGHT_GRAY_LANTERN_ITEM, Items.LIGHT_GRAY_DYE, exporter);
        lanternRecipe(ModBlocks.WHITE_LANTERN_ITEM, Items.WHITE_DYE, exporter);
    }

    private void lanternRecipe(Item item, Item ingredient, RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, item, 1)
                .input(ingredient)
                .input(Items.LANTERN)
                .criterion(hasItem(ingredient), conditionsFromItem(ingredient))
                .offerTo(exporter, Identifier.of(ColoredLanterns.MOD_ID, getRecipeName(item)));
    }
}