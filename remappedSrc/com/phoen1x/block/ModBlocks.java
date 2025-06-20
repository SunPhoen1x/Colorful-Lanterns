package com.phoen1x.block;

import com.phoen1x.block.lantern.*;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.phoen1x.ColoredLanterns.MOD_ID;

public class ModBlocks {
    public static final Block RED_LANTERN = registerBlock("red_lantern", new RedLantern(Block.Settings.copy(Blocks.LANTERN)));
    public static final BlockItem RED_LANTERN_ITEM = registerBlockItem("red_lantern", new TexturedPolyBlockItem(RED_LANTERN, new Item.Settings(), "item/red_lantern"));

    public static final Block YELLOW_LANTERN = registerBlock("yellow_lantern", new YellowLantern(Block.Settings.copy(Blocks.LANTERN)));
    public static final BlockItem YELLOW_LANTERN_ITEM = registerBlockItem("yellow_lantern", new TexturedPolyBlockItem(YELLOW_LANTERN, new Item.Settings(), "item/yellow_lantern"));

    public static final Block GREEN_LANTERN = registerBlock("green_lantern", new GreenLantern(Block.Settings.copy(Blocks.LANTERN)));
    public static final BlockItem GREEN_LANTERN_ITEM = registerBlockItem("green_lantern", new TexturedPolyBlockItem(GREEN_LANTERN, new Item.Settings(), "item/green_lantern"));

    public static final Block ORANGE_LANTERN = registerBlock("orange_lantern", new OrangeLantern(Block.Settings.copy(Blocks.LANTERN)));
    public static final BlockItem ORANGE_LANTERN_ITEM = registerBlockItem("orange_lantern", new TexturedPolyBlockItem(ORANGE_LANTERN, new Item.Settings(), "item/orange_lantern"));

    public static final Block LIME_LANTERN = registerBlock("lime_lantern", new LimeLantern(Block.Settings.copy(Blocks.LANTERN)));
    public static final BlockItem LIME_LANTERN_ITEM = registerBlockItem("lime_lantern", new TexturedPolyBlockItem(LIME_LANTERN, new Item.Settings(), "item/lime_lantern"));

    public static final Block CYAN_LANTERN = registerBlock("cyan_lantern", new CyanLantern(Block.Settings.copy(Blocks.LANTERN)));
    public static final BlockItem CYAN_LANTERN_ITEM = registerBlockItem("cyan_lantern", new TexturedPolyBlockItem(CYAN_LANTERN, new Item.Settings(), "item/cyan_lantern"));

    public static final Block BLUE_LANTERN = registerBlock("blue_lantern", new BlueLantern(Block.Settings.copy(Blocks.LANTERN)));
    public static final BlockItem BLUE_LANTERN_ITEM = registerBlockItem("blue_lantern", new TexturedPolyBlockItem(BLUE_LANTERN, new Item.Settings(), "item/blue_lantern"));

    public static final Block LIGHT_BLUE_LANTERN = registerBlock("light_blue_lantern", new LightBlueLantern(Block.Settings.copy(Blocks.LANTERN)));
    public static final BlockItem LIGHT_BLUE_LANTERN_ITEM = registerBlockItem("light_blue_lantern", new TexturedPolyBlockItem(LIGHT_BLUE_LANTERN, new Item.Settings(), "item/light_blue_lantern"));

    public static final Block PURPLE_LANTERN = registerBlock("purple_lantern", new PurpleLantern(Block.Settings.copy(Blocks.LANTERN)));
    public static final BlockItem PURPLE_LANTERN_ITEM = registerBlockItem("purple_lantern", new TexturedPolyBlockItem(PURPLE_LANTERN, new Item.Settings(), "item/purple_lantern"));

    public static final Block MAGENTA_LANTERN = registerBlock("magenta_lantern", new MagentaLantern(Block.Settings.copy(Blocks.LANTERN)));
    public static final BlockItem MAGENTA_LANTERN_ITEM = registerBlockItem("magenta_lantern", new TexturedPolyBlockItem(MAGENTA_LANTERN, new Item.Settings(), "item/magenta_lantern"));

    public static final Block PINK_LANTERN = registerBlock("pink_lantern", new PinkLantern(Block.Settings.copy(Blocks.LANTERN)));
    public static final BlockItem PINK_LANTERN_ITEM = registerBlockItem("pink_lantern", new TexturedPolyBlockItem(PINK_LANTERN, new Item.Settings(), "item/pink_lantern"));

    public static final Block BROWN_LANTERN = registerBlock("brown_lantern", new BrownLantern(Block.Settings.copy(Blocks.LANTERN)));
    public static final BlockItem BROWN_LANTERN_ITEM = registerBlockItem("brown_lantern", new TexturedPolyBlockItem(BROWN_LANTERN, new Item.Settings(), "item/brown_lantern"));

    public static final Block BLACK_LANTERN = registerBlock("black_lantern", new BlackLantern(Block.Settings.copy(Blocks.LANTERN)));
    public static final BlockItem BLACK_LANTERN_ITEM = registerBlockItem("black_lantern", new TexturedPolyBlockItem(BLACK_LANTERN, new Item.Settings(), "item/black_lantern"));

    public static final Block DARK_GRAY_LANTERN = registerBlock("dark_gray_lantern", new DarkGrayLantern(Block.Settings.copy(Blocks.LANTERN)));
    public static final BlockItem DARK_GRAY_LANTERN_ITEM = registerBlockItem("dark_gray_lantern", new TexturedPolyBlockItem(DARK_GRAY_LANTERN, new Item.Settings(), "item/dark_gray_lantern"));

    public static final Block LIGHT_GRAY_LANTERN = registerBlock("light_gray_lantern", new LightGrayLantern(Block.Settings.copy(Blocks.LANTERN)));
    public static final BlockItem LIGHT_GRAY_LANTERN_ITEM = registerBlockItem("light_gray_lantern", new TexturedPolyBlockItem(LIGHT_GRAY_LANTERN, new Item.Settings(), "item/light_gray_lantern"));

    public static final Block WHITE_LANTERN = registerBlock("white_lantern", new WhiteLantern(Block.Settings.copy(Blocks.LANTERN)));
    public static final BlockItem WHITE_LANTERN_ITEM = registerBlockItem("white_lantern", new TexturedPolyBlockItem(WHITE_LANTERN, new Item.Settings(), "item/white_lantern"));

    public static void registerBlocks() {
        ItemGroup.Builder builder = PolymerItemGroupUtils.builder();
        builder.icon(() -> new ItemStack(ModBlocks.RED_LANTERN_ITEM, 1));
        builder.displayName(Text.translatable("Colorful Lantern"));
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

    public static Block registerBlock(String name, Block block){
        return Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, name), block);
    }
    public static BlockItem registerBlockItem(String name, BlockItem item){
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }
}
