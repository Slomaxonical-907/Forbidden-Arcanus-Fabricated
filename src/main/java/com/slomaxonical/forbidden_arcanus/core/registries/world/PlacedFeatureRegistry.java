package com.slomaxonical.forbidden_arcanus.core.registries.world;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.core.config.WorldGenConfig;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class PlacedFeatureRegistry {
    public static PlacedFeature ARCANE_CRYSTAL_ORE = new PlacedFeature(RegistryEntry.of(ConfiguredFeatureRegistry.ARCANE_CRYSTAL_ORE), commonOrePlacement(WorldGenConfig.ARCANE_CRYSTAL_ORE_COUNT.get(), HeightRangePlacementModifier.trapezoid(YOffset.fixed(-40), YOffset.fixed(14))));
    public static PlacedFeature RUNIC_STONE = new PlacedFeature(RegistryEntry.of(ConfiguredFeatureRegistry.RUNIC_STONE), commonOrePlacement(WorldGenConfig.RUNIC_STONE_COUNT.get(), HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(2))));
    public static PlacedFeature DARKSTONE = new PlacedFeature(RegistryEntry.of(ConfiguredFeatureRegistry.DARKSTONE), commonOrePlacement(28, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.aboveBottom(13))));
    public static PlacedFeature ARCANE_GILDED_DARKSTONE = new PlacedFeature(RegistryEntry.of(ConfiguredFeatureRegistry.ARCANE_GILDED_DARKSTONE), commonOrePlacement(WorldGenConfig.ARCANE_GILDED_DARKSTONE_COUNT.get(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.aboveBottom(13))));
    public static PlacedFeature STELLA_ARCANUM = new PlacedFeature(RegistryEntry.of(ConfiguredFeatureRegistry.STELLA_ARCANUM), commonOrePlacement(WorldGenConfig.STELLA_ARCANUM_COUNT.get(), HeightRangePlacementModifier.uniform(YOffset.fixed(-44), YOffset.fixed(42))));
    public static PlacedFeature XPETRIFIED_ORE = new PlacedFeature(RegistryEntry.of(ConfiguredFeatureRegistry.XPETRIFIED_ORE), commonOrePlacement(18, HeightRangePlacementModifier.uniform(YOffset.fixed(-6), YOffset.fixed(35))));

    private static List<PlacementModifier> orePlacement(PlacementModifier countModifier, PlacementModifier placementModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), placementModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier placementModifier) {
        return orePlacement(CountPlacementModifier.of(count), placementModifier);
    }

    public static void register() {
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "arcane_crystal_ore"), ARCANE_CRYSTAL_ORE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "runic_stone"), RUNIC_STONE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "darkstone"), DARKSTONE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "arcane_gilded_darkstone"), ARCANE_GILDED_DARKSTONE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "stella_arcanum"), STELLA_ARCANUM);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "xpetrified_ore"), XPETRIFIED_ORE);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(ForbiddenArcanus.MOD_ID, "arcane_crystal_ore")));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(ForbiddenArcanus.MOD_ID, "runic_stone")));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(ForbiddenArcanus.MOD_ID, "darkstone")));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(ForbiddenArcanus.MOD_ID, "arcane_gilded_darkstone")));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(ForbiddenArcanus.MOD_ID, "stella_arcanum")));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(ForbiddenArcanus.MOD_ID, "xpetrified_ore")));

    }
}
