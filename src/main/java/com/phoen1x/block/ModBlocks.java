package com.phoen1x.block;

import com.phoen1x.ColoredLanterns;
import com.phoen1x.block.lantern.*;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static com.phoen1x.ColoredLanterns.MOD_ID;

public class ModBlocks {
    public static final Block RED_LANTERN = registerBlock("red_lantern", settings -> new RedLantern(settings), Block.Settings.copy(Blocks.LANTERN));
    public static final BlockItem RED_LANTERN_ITEM = registerBlockItem("red_lantern", settings -> new TexturedPolyBlockItem(RED_LANTERN, settings), new Item.Settings());

    public static final Block YELLOW_LANTERN = registerBlock("yellow_lantern", settings -> new YellowLantern(settings), Block.Settings.copy(Blocks.LANTERN));
    public static final BlockItem YELLOW_LANTERN_ITEM = registerBlockItem("yellow_lantern", settings -> new TexturedPolyBlockItem(YELLOW_LANTERN, settings), new Item.Settings());

    public static final Block GREEN_LANTERN = registerBlock("green_lantern", settings -> new GreenLantern(settings), Block.Settings.copy(Blocks.LANTERN));
    public static final BlockItem GREEN_LANTERN_ITEM = registerBlockItem("green_lantern", settings -> new TexturedPolyBlockItem(GREEN_LANTERN, settings), new Item.Settings());

    public static final Block ORANGE_LANTERN = registerBlock("orange_lantern", settings -> new OrangeLantern(settings), Block.Settings.copy(Blocks.LANTERN));
    public static final BlockItem ORANGE_LANTERN_ITEM = registerBlockItem("orange_lantern", settings -> new TexturedPolyBlockItem(ORANGE_LANTERN, settings), new Item.Settings());

    public static final Block LIME_LANTERN = registerBlock("lime_lantern", settings -> new LimeLantern(settings), Block.Settings.copy(Blocks.LANTERN));
    public static final BlockItem LIME_LANTERN_ITEM = registerBlockItem("lime_lantern", settings -> new TexturedPolyBlockItem(LIME_LANTERN, settings), new Item.Settings());

    public static final Block CYAN_LANTERN = registerBlock("cyan_lantern", settings -> new CyanLantern(settings), Block.Settings.copy(Blocks.LANTERN));
    public static final BlockItem CYAN_LANTERN_ITEM = registerBlockItem("cyan_lantern", settings -> new TexturedPolyBlockItem(CYAN_LANTERN, settings), new Item.Settings());

    public static final Block BLUE_LANTERN = registerBlock("blue_lantern", settings -> new BlueLantern(settings), Block.Settings.copy(Blocks.LANTERN));
    public static final BlockItem BLUE_LANTERN_ITEM = registerBlockItem("blue_lantern", settings -> new TexturedPolyBlockItem(BLUE_LANTERN, settings), new Item.Settings());

    public static final Block LIGHT_BLUE_LANTERN = registerBlock("light_blue_lantern", settings -> new LightBlueLantern(settings), Block.Settings.copy(Blocks.LANTERN));
    public static final BlockItem LIGHT_BLUE_LANTERN_ITEM = registerBlockItem("light_blue_lantern", settings -> new TexturedPolyBlockItem(LIGHT_BLUE_LANTERN, settings), new Item.Settings());

    public static final Block PURPLE_LANTERN = registerBlock("purple_lantern", settings -> new PurpleLantern(settings), Block.Settings.copy(Blocks.LANTERN));
    public static final BlockItem PURPLE_LANTERN_ITEM = registerBlockItem("purple_lantern", settings -> new TexturedPolyBlockItem(PURPLE_LANTERN, settings), new Item.Settings());

    public static final Block MAGENTA_LANTERN = registerBlock("magenta_lantern", settings -> new MagentaLantern(settings), Block.Settings.copy(Blocks.LANTERN));
    public static final BlockItem MAGENTA_LANTERN_ITEM = registerBlockItem("magenta_lantern", settings -> new TexturedPolyBlockItem(MAGENTA_LANTERN, settings), new Item.Settings());

    public static final Block PINK_LANTERN = registerBlock("pink_lantern", settings -> new PinkLantern(settings), Block.Settings.copy(Blocks.LANTERN));
    public static final BlockItem PINK_LANTERN_ITEM = registerBlockItem("pink_lantern", settings -> new TexturedPolyBlockItem(PINK_LANTERN, settings), new Item.Settings());

    public static final Block BROWN_LANTERN = registerBlock("brown_lantern", settings -> new BrownLantern(settings), Block.Settings.copy(Blocks.LANTERN));
    public static final BlockItem BROWN_LANTERN_ITEM = registerBlockItem("brown_lantern", settings -> new TexturedPolyBlockItem(BROWN_LANTERN, settings), new Item.Settings());

    public static final Block BLACK_LANTERN = registerBlock("black_lantern", settings -> new BlackLantern(settings), Block.Settings.copy(Blocks.LANTERN));
    public static final BlockItem BLACK_LANTERN_ITEM = registerBlockItem("black_lantern", settings -> new TexturedPolyBlockItem(BLACK_LANTERN, settings), new Item.Settings());

    public static final Block DARK_GRAY_LANTERN = registerBlock("dark_gray_lantern", settings -> new DarkGrayLantern(settings), Block.Settings.copy(Blocks.LANTERN));
    public static final BlockItem DARK_GRAY_LANTERN_ITEM = registerBlockItem("dark_gray_lantern", settings -> new TexturedPolyBlockItem(DARK_GRAY_LANTERN, settings), new Item.Settings());

    public static final Block LIGHT_GRAY_LANTERN = registerBlock("light_gray_lantern", settings -> new LightGrayLantern(settings), Block.Settings.copy(Blocks.LANTERN));
    public static final BlockItem LIGHT_GRAY_LANTERN_ITEM = registerBlockItem("light_gray_lantern", settings -> new TexturedPolyBlockItem(LIGHT_GRAY_LANTERN, settings), new Item.Settings());

    public static final Block WHITE_LANTERN = registerBlock("white_lantern", settings -> new WhiteLantern(settings), Block.Settings.copy(Blocks.LANTERN));
    public static final BlockItem WHITE_LANTERN_ITEM = registerBlockItem("white_lantern", settings -> new TexturedPolyBlockItem(WHITE_LANTERN, settings), new Item.Settings());

    public static void registerBlocks() {
        ItemGroup.Builder builder = PolymerItemGroupUtils.builder();
        builder.icon(() -> new ItemStack(ModBlocks.RED_LANTERN_ITEM, 1));
        builder.displayName(Text.literal("Colorful Lanterns"));
        builder.entries((displayContext, entries) -> {
            entries.add(RED_LANTERN);
            entries.add(ORANGE_LANTERN);
            entries.add(YELLOW_LANTERN);
            entries.add(LIME_LANTERN);
            entries.add(GREEN_LANTERN);
            entries.add(CYAN_LANTERN);
            entries.add(LIGHT_BLUE_LANTERN);
            entries.add(BLUE_LANTERN);
            entries.add(PURPLE_LANTERN);
            entries.add(MAGENTA_LANTERN);
            entries.add(PINK_LANTERN);
            entries.add(BROWN_LANTERN);
            entries.add(BLACK_LANTERN);
            entries.add(DARK_GRAY_LANTERN);
            entries.add(LIGHT_GRAY_LANTERN);
            entries.add(WHITE_LANTERN);
        });
        ItemGroup polymerGroup = builder.build();
        PolymerItemGroupUtils.registerPolymerItemGroup(Identifier.of(MOD_ID, "blocks"), polymerGroup);
    }

    public static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings){
        var key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(ColoredLanterns.MOD_ID, name));
        Block block = factory.apply(settings.registryKey(key));

        return Registry.register(Registries.BLOCK, key, block);
    }

    public static BlockItem registerBlockItem(String name, Function<Item.Settings, BlockItem> factory, Item.Settings settings){
        var key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ColoredLanterns.MOD_ID, name));
        BlockItem item = factory.apply(settings.registryKey(key).useBlockPrefixedTranslationKey());

        return Registry.register(Registries.ITEM, key, item);
    }
}