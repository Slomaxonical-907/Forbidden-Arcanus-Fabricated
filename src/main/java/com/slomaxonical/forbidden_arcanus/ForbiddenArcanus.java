package com.slomaxonical.forbidden_arcanus;

import com.slomaxonical.forbidden_arcanus.core.registries.EnchantmentRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.StatusEffectsRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.SoundRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ForbiddenItemGroup;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForbiddenArcanus implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Forbidden & Arcanus");
	public static final String MOD_ID = "forbidden_arcanus";
	public static final OwoItemGroup TABBED_FORBIDDEN_GROUP = new ForbiddenItemGroup(new Identifier(MOD_ID,"main"));

	@Override
	public void onInitialize() {
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		FieldRegistrationHandler.register(ItemRegistry.class, MOD_ID, false);
		FieldRegistrationHandler.register(BlockRegistry.class, MOD_ID, false);
		FieldRegistrationHandler.register(StatusEffectsRegistry.class, MOD_ID, false);
		FieldRegistrationHandler.register(EnchantmentRegistry.class, MOD_ID, false);
		FieldRegistrationHandler.register(BlockEntityRegistry.class, MOD_ID, false);
		FieldRegistrationHandler.register(SoundRegistry.class, MOD_ID, false);
		TABBED_FORBIDDEN_GROUP.initialize();
	}
}
