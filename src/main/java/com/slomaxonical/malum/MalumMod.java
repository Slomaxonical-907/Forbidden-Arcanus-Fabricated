package com.slomaxonical.malum;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MalumMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Malum");
	public static final String MOD_ID = "Malum";

	@Override
	public void onInitialize() {
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}
}
