package com.slomaxonical.forbidden_arcanus;

import com.slomaxonical.forbidden_arcanus.common.block.StellaArcanumBlock;
import com.slomaxonical.forbidden_arcanus.core.config.Config;
import com.slomaxonical.forbidden_arcanus.core.registries.*;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ForbiddenItemGroup;
import com.slomaxonical.forbidden_arcanus.core.registries.world.*;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForbiddenArcanus implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Forbidden & Arcanus");
	public static final String ID = "forbidden_arcanus";
	public static final OwoItemGroup TABBED_FORBIDDEN_GROUP = new ForbiddenItemGroup(new Identifier(ID,"main"));
	DefaultedList<ItemStack> lizt = DefaultedList.ofSize(2);

	@Override
	public void onInitialize() {
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Config.init();
		Config.loadConfig(Config.CLIENT_CONFIG, FabricLoader.getInstance().getConfigDir().resolve(ForbiddenArcanus.ID + "-client.toml").toString());
		Config.loadConfig(Config.COMMON_CONFIG, FabricLoader.getInstance().getConfigDir().resolve(ForbiddenArcanus.ID + "-common.toml").toString());

		FieldRegistrationHandler.register(BlockRegistry.class, ID, false);
		FieldRegistrationHandler.register(ItemRegistry.class, ID, false);
		FieldRegistrationHandler.register(EnchantmentRegistry.class, ID, false);
		FieldRegistrationHandler.register(StatusEffectsRegistry.class, ID, false);
		FieldRegistrationHandler.register(BlockEntityRegistry.class, ID, false);
		FieldRegistrationHandler.register(SoundRegistry.class, ID, false);
		FieldRegistrationHandler.register(EntityRegistry.class, ID, false);
		FieldRegistrationHandler.register(POIRegistry.class, ID, false);
		DispenserBehaviorRegistry.registerDispenseBehaviors();
		FeatureRegistry.register();
		ConfiguredFeatureRegistry.register();
		PlacedFeatureRegistry.register();
		StructureRegistry.setupStructures();
		ScreenHandlerTypeRegistry.init();

		Registry.register(Registry.PARTICLE_TYPE, new Identifier(ID, "soul"), ParticleRegistry.SOUL);
		Registry.register(Registry.PARTICLE_TYPE, new Identifier(ID, "aureal_mote"), ParticleRegistry.AUREAL_MOTE);
		Registry.register(Registry.PARTICLE_TYPE, new Identifier(ID, "magic_explosion"), ParticleRegistry.MAGIC_EXPLOSION);
		Registry.register(Registry.PARTICLE_TYPE, new Identifier(ID, "magic_explosion_emitter"), ParticleRegistry.HUGE_MAGIC_EXPLOSION);
//		FieldRegistrationHandler.register(ParticleRegistry.class, MOD_ID,false);

		StellaArcanumBlock.onBrokenEvent();
		//OWOItemgroupTest
		TABBED_FORBIDDEN_GROUP.initialize();
		lizt.add(new ItemStack(ItemRegistry.LENS_OF_VERITATIS));
		lizt.add(new ItemStack(BlockRegistry.ARCANE_CHISELED_DARKSTONE.asItem()));
		TABBED_FORBIDDEN_GROUP.appendStacks(lizt);

	}
}
