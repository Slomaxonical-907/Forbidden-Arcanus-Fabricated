package com.slomaxonical.forbidden_arcanus.core.registries.world;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.core.config.WorldGenConfig;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class PlacedFeatureRegistry {
    public static PlacedFeature ARCANE_CRYSTAL_ORE = new PlacedFeature(RegistryEntry.of(ConfiguredFeatureRegistry.ARCANE_CRYSTAL_ORE), commonOrePlacement(WorldGenConfig.ARCANE_CRYSTAL_ORE_COUNT.get(), HeightRangePlacementModifier.trapezoid(YOffset.fixed(-40), YOffset.fixed(14))));
    public static PlacedFeature RUNIC_STONE = new PlacedFeature(RegistryEntry.of(ConfiguredFeatureRegistry.RUNIC_STONE), commonOrePlacement(WorldGenConfig.RUNIC_STONE_COUNT.get(), HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(2))));
    public static PlacedFeature DARKSTONE = new PlacedFeature(RegistryEntry.of(ConfiguredFeatureRegistry.DARKSTONE), commonOrePlacement(28, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.aboveBottom(13))));
    public static PlacedFeature ARCANE_GILDED_DARKSTONE = new PlacedFeature(RegistryEntry.of(ConfiguredFeatureRegistry.ARCANE_GILDED_DARKSTONE), commonOrePlacement(WorldGenConfig.ARCANE_GILDED_DARKSTONE_COUNT.get(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.aboveBottom(13))));
    public static PlacedFeature STELLA_ARCANUM = new PlacedFeature(RegistryEntry.of(ConfiguredFeatureRegistry.STELLA_ARCANUM), commonOrePlacement(WorldGenConfig.STELLA_ARCANUM_COUNT.get(), HeightRangePlacementModifier.uniform(YOffset.fixed(-44), YOffset.fixed(42))));
    public static PlacedFeature XPETRIFIED_ORE = new PlacedFeature(RegistryEntry.of(ConfiguredFeatureRegistry.XPETRIFIED_ORE), commonOrePlacement(18, HeightRangePlacementModifier.uniform(YOffset.fixed(-6), YOffset.fixed(35))));

    public static PlacedFeature EDELWOOD_TREES = new PlacedFeature(RegistryEntry.of(ConfiguredFeatureRegistry.EDELWOOD), List.of(SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, PlacedFeatures.createCountExtraModifier(18, 0.25F, 3), BiomePlacementModifier.of()));
    public static PlacedFeature PETRIFIED_ROOT = new PlacedFeature(RegistryEntry.of(ConfiguredFeatureRegistry.EDELWOOD), List.of(HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(50)), SquarePlacementModifier.of(), CountPlacementModifier.of(UniformIntProvider.create(173, 256)), EnvironmentScanPlacementModifier.of(Direction.UP, BlockPredicate.matchingBlockTag(BlockTags.BASE_STONE_OVERWORLD), BlockPredicate.IS_AIR, 12), RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)), BiomePlacementModifier.of()));
    public static PlacedFeature YELLOW_ORCHID = new PlacedFeature(RegistryEntry.of(ConfiguredFeatureRegistry.EDELWOOD),  List.of(RarityFilterPlacementModifier.of(12), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, SquarePlacementModifier.of(), BiomePlacementModifier.of()));

    private static List<PlacementModifier> orePlacement(PlacementModifier countModifier, PlacementModifier placementModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), placementModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier placementModifier) {
        return orePlacement(CountPlacementModifier.of(count), placementModifier);
    }

    public static void register() {
        BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "arcane_crystal_ore"), ARCANE_CRYSTAL_ORE);
        BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "runic_stone"), RUNIC_STONE);
        BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "darkstone"), DARKSTONE);
        BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "arcane_gilded_darkstone"), ARCANE_GILDED_DARKSTONE);
        BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "stella_arcanum"), STELLA_ARCANUM);
        BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "xpetrified_ore"), XPETRIFIED_ORE);
        BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "edelwood_trees"), EDELWOOD_TREES);
        BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "petrified_root"), PETRIFIED_ROOT);
        BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "yellow_orchid"), YELLOW_ORCHID);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(ForbiddenArcanus.MOD_ID, "arcane_crystal_ore")));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(ForbiddenArcanus.MOD_ID, "runic_stone")));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(ForbiddenArcanus.MOD_ID, "darkstone")));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(ForbiddenArcanus.MOD_ID, "arcane_gilded_darkstone")));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(ForbiddenArcanus.MOD_ID, "stella_arcanum")));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(ForbiddenArcanus.MOD_ID, "xpetrified_ore")));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(ForbiddenArcanus.MOD_ID, "edelwood_trees")));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(ForbiddenArcanus.MOD_ID, "petrified_root")));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(ForbiddenArcanus.MOD_ID, "yellow_orchid")));

    }
}
