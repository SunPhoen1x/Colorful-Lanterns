package com.phoen1x;

import com.phoen1x.block.ModBlocks;
import com.phoen1x.block.lantern.*;
import com.phoen1x.entity.ModEntities;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import eu.pb4.polymer.resourcepack.extras.api.ResourcePackExtras;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColoredLanterns implements ModInitializer {
	public static final String MOD_ID = "colored-lanterns";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.registerBlocks();
		ModEntities.register();

		initModels();
		if (PolymerResourcePackUtils.addModAssets(MOD_ID)) {
			ResourcePackExtras.forDefault().addBridgedModelsFolder(id("block"), id("item"), id("sgui"));
			LOGGER.info("Colorful Lanterns 1.21.5-0.1");
		} else {
			LOGGER.error("Failed to add mod assets for " + MOD_ID);
		}
		PolymerResourcePackUtils.markAsRequired();
	}

	public void initModels(){
		RedLantern.Model.HANGING_MODEL.isEmpty();
		RedLantern.Model.STANDING_MODEL.isEmpty();

		YellowLantern.Model.HANGING_MODEL.isEmpty();
		YellowLantern.Model.STANDING_MODEL.isEmpty();

		GreenLantern.Model.HANGING_MODEL.isEmpty();
		GreenLantern.Model.STANDING_MODEL.isEmpty();

		OrangeLantern.Model.HANGING_MODEL.isEmpty();
		OrangeLantern.Model.STANDING_MODEL.isEmpty();

		LimeLantern.Model.HANGING_MODEL.isEmpty();
		LimeLantern.Model.STANDING_MODEL.isEmpty();

		CyanLantern.Model.HANGING_MODEL.isEmpty();
		CyanLantern.Model.STANDING_MODEL.isEmpty();

		BlueLantern.Model.HANGING_MODEL.isEmpty();
		BlueLantern.Model.STANDING_MODEL.isEmpty();

		LightBlueLantern.Model.HANGING_MODEL.isEmpty();
		LightBlueLantern.Model.STANDING_MODEL.isEmpty();

		PurpleLantern.Model.HANGING_MODEL.isEmpty();
		PurpleLantern.Model.STANDING_MODEL.isEmpty();

		MagentaLantern.Model.HANGING_MODEL.isEmpty();
		MagentaLantern.Model.STANDING_MODEL.isEmpty();

		PinkLantern.Model.HANGING_MODEL.isEmpty();
		PinkLantern.Model.STANDING_MODEL.isEmpty();

		BrownLantern.Model.HANGING_MODEL.isEmpty();
		BrownLantern.Model.STANDING_MODEL.isEmpty();

		BlackLantern.Model.HANGING_MODEL.isEmpty();
		BlackLantern.Model.STANDING_MODEL.isEmpty();

		DarkGrayLantern.Model.HANGING_MODEL.isEmpty();
		DarkGrayLantern.Model.STANDING_MODEL.isEmpty();

		LightGrayLantern.Model.HANGING_MODEL.isEmpty();
		LightGrayLantern.Model.STANDING_MODEL.isEmpty();

		WhiteLantern.Model.HANGING_MODEL.isEmpty();
		WhiteLantern.Model.STANDING_MODEL.isEmpty();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}