package com.slomaxonical.forbidden_arcanus.core.registries.world;

import com.google.common.collect.ImmutableList;
import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.config.BigFungyssConfig;
import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.config.cherrytree.CherryFoliagePlacer;
import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.config.cherrytree.CherryTreeConfig;
import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.config.cherrytree.CherryTrunkPlacer;
import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.config.cherrytree.LeafCarpetDecorator;
import com.slomaxonical.forbidden_arcanus.common.worldgen.structure.config.NipaConfig;
import com.slomaxonical.forbidden_arcanus.core.config.WorldGenConfig;
import com.slomaxonical.forbidden_arcanus.core.helper.FATags;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.OptionalInt;

public class ConfiguredFeatureRegistry {
    //TARGETS
    public static final ImmutableList<OreFeatureConfig.Target> ORE_ARCANE_CRYSTAL_TARGET_LIST = ImmutableList.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, States.ARCANE_CRYSTAL_ORE), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, States.DEEPSLATE_ARCANE_CRYSTAL_ORE));
    public static final ImmutableList<OreFeatureConfig.Target> ORE_RUNIC_STONE_TARGET_LIST = ImmutableList.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, States.RUNIC_STONE), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, States.RUNIC_DEEPSLATE), OreFeatureConfig.createTarget(new BlockMatchRuleTest(BlockRegistry.DARKSTONE), States.RUNIC_DARKSTONE));
    public static final ImmutableList<OreFeatureConfig.Target> ORE_DARKSTONE_TARGET_LIST = ImmutableList.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, States.DARKSTONE), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, States.DARKSTONE));
    public static final ImmutableList<OreFeatureConfig.Target> ORE_STELLA_ARCANUM_TARGET_LIST = ImmutableList.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, States.STELLA_ARCANUM), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, States.STELLA_ARCANUM));
    public static final ImmutableList<OreFeatureConfig.Target> ORE_XPETRIFIED_TARGET_LIST = ImmutableList.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, States.XPETRIFIED_ORE), OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, States.XPETRIFIED_ORE));
    //CONFIGURED
    //Ores
    public static ConfiguredFeature<?, ?> ARCANE_CRYSTAL_ORE = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(ORE_ARCANE_CRYSTAL_TARGET_LIST, WorldGenConfig.ARCANE_CRYSTAL_ORE_MAX_VEIN_SIZE.get()));
    public static ConfiguredFeature<?, ?> RUNIC_STONE = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(ORE_RUNIC_STONE_TARGET_LIST, WorldGenConfig.RUNIC_STONE_MAX_VEIN_SIZE.get()));
    public static ConfiguredFeature<?, ?> DARKSTONE = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(ORE_DARKSTONE_TARGET_LIST, WorldGenConfig.DARKSTONE_MAX_VEIN_SIZE.get()));
    public static ConfiguredFeature<?, ?> ARCANE_GILDED_DARKSTONE = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(new BlockMatchRuleTest(BlockRegistry.DARKSTONE), States.ARCANE_GILDED_DARKSTONE, WorldGenConfig.ARCANE_CRYSTAL_ORE_MAX_VEIN_SIZE.get()));
    public static ConfiguredFeature<?, ?> STELLA_ARCANUM = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(ORE_STELLA_ARCANUM_TARGET_LIST, WorldGenConfig.STELLA_ARCANUM_MAX_VEIN_SIZE.get()));
    public static ConfiguredFeature<?, ?> XPETRIFIED_ORE = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(ORE_XPETRIFIED_TARGET_LIST, 3));
    //Trees
    public static final ConfiguredFeature<TreeFeatureConfig, ?> CHERRYWOOD_TREE = new ConfiguredFeature<>(Feature.TREE, new CherryTreeConfig(
            BlockStateProvider.of(BlockRegistry.CHERRYWOOD_LOG),
            BlockStateProvider.of(BlockRegistry.THIN_CHERRYWOOD_LOG),
            new CherryTrunkPlacer(3, 1, 1),
            BlockStateProvider.of(BlockRegistry.CHERRYWOOD_LEAVES),
            new CherryFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
            new TwoLayersFeatureSize(1, 0, 2)));
    public static final ConfiguredFeature<TreeFeatureConfig, ?> FANCY_CHERRYWOOD_TREE = new ConfiguredFeature<>(Feature.TREE, new CherryTreeConfig(
            BlockStateProvider.of(BlockRegistry.CHERRYWOOD_LOG),
            BlockStateProvider.of(BlockRegistry.THIN_CHERRYWOOD_LOG),
            new CherryTrunkPlacer(4, 2, 2),
            BlockStateProvider.of(BlockRegistry.CHERRYWOOD_LEAVES),
            new CherryFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
            new TwoLayersFeatureSize(1, 0, 2)));

    public static final ConfiguredFeature<TreeFeatureConfig, ?> MYSTERYWOOD_TREE = new ConfiguredFeature<>(Feature.TREE, new TreeFeatureConfig.Builder(
            BlockStateProvider.of(BlockRegistry.MYSTERYWOOD_LOG),
            new LargeOakTrunkPlacer(3, 11, 0),
            new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(States.MYSTERYWOOD_LEAVES, 4).add(States.NUGGETY_MYSTERYWOOD_LEAVES, 1)),
            new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
            new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
            .ignoreVines().build());
    //treeStuff
    public static final FoliagePlacerType<?> CHERRY_FOLIAGE_PLACER = new FoliagePlacerType<>(CherryFoliagePlacer.CODEC);
    public static final TrunkPlacerType<?> CHERRY_TRUNK_PLACER = new TrunkPlacerType<>(CherryTrunkPlacer.CODEC);
    public static final TreeDecoratorType<?> LEAF_CARPET_DECORATOR = new TreeDecoratorType<>(LeafCarpetDecorator.CODEC);

    //Fungus
    public static final ConfiguredFeature<BigFungyssConfig, ?> BIG_FUNGYSS_0 = new ConfiguredFeature<>(FeatureRegistry.BIG_FUNGYSS, new BigFungyssConfig(SimpleBlockStateProvider.of(States.FUNGYSS_BLOCK), SimpleBlockStateProvider.of(States.FUNGYSS_STEM), SimpleBlockStateProvider.of(States.FUNGYSS_HYPHAE), 0));
    public static final ConfiguredFeature<BigFungyssConfig, ?> BIG_FUNGYSS_1 =  new ConfiguredFeature<>(FeatureRegistry.BIG_FUNGYSS, new BigFungyssConfig(SimpleBlockStateProvider.of(States.FUNGYSS_BLOCK), SimpleBlockStateProvider.of(States.FUNGYSS_STEM), SimpleBlockStateProvider.of(States.FUNGYSS_HYPHAE), 1));
    public static final ConfiguredFeature<BigFungyssConfig, ?> MEGA_FUNGYSS_0 = new ConfiguredFeature<>(FeatureRegistry.MEGA_FUNGYSS, new BigFungyssConfig(SimpleBlockStateProvider.of(States.FUNGYSS_BLOCK), SimpleBlockStateProvider.of(States.FUNGYSS_STEM), SimpleBlockStateProvider.of(States.FUNGYSS_HYPHAE), 0));
    public static final ConfiguredFeature<BigFungyssConfig, ?> MEGA_FUNGYSS_1 = new ConfiguredFeature<>(FeatureRegistry.MEGA_FUNGYSS, new BigFungyssConfig(SimpleBlockStateProvider.of(States.FUNGYSS_BLOCK), SimpleBlockStateProvider.of(States.FUNGYSS_STEM), SimpleBlockStateProvider.of(States.FUNGYSS_HYPHAE), 1));

    //PLACED
    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> YELLOW_ORCHID = new ConfiguredFeature<>(Feature.FLOWER, new RandomPatchFeatureConfig(64, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(BlockRegistry.YELLOW_ORCHID)))));
    public static final ConfiguredFeature<DefaultFeatureConfig, ?> EDELWOOD = new ConfiguredFeature<>(FeatureRegistry.EDELWOOD, DefaultFeatureConfig.INSTANCE);
    public static final ConfiguredFeature<SingleStateFeatureConfig, ?> PETRIFIED_ROOT = new ConfiguredFeature<>(FeatureRegistry.PETRIFIED_ROOT, new SingleStateFeatureConfig(States.PETRIFIED_ROOT));

    public static void register() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "ore_arcane_crystal"), ARCANE_CRYSTAL_ORE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "ore_rune"), RUNIC_STONE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "ore_darkstone"), DARKSTONE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "ore_arcane_gilded_darkstone"), ARCANE_GILDED_DARKSTONE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "ore_stella_arcanum"), STELLA_ARCANUM);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "ore_xpetrified"), XPETRIFIED_ORE);

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "cherrywood"), CHERRYWOOD_TREE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "fancy_cherrywood"), FANCY_CHERRYWOOD_TREE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "mysterywood"), MYSTERYWOOD_TREE);

        Registry.register(Registry.FOLIAGE_PLACER_TYPE, new Identifier(ForbiddenArcanus.MOD_ID, "cherry_foliage_placer"), CHERRY_FOLIAGE_PLACER);
        Registry.register(Registry.TRUNK_PLACER_TYPE, new Identifier(ForbiddenArcanus.MOD_ID, "cherry_trunk_placer"), CHERRY_TRUNK_PLACER);
        Registry.register(Registry.TREE_DECORATOR_TYPE, new Identifier(ForbiddenArcanus.MOD_ID, "leaf_carpet_decorator"), LEAF_CARPET_DECORATOR);

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "big_fungyss_0"), BIG_FUNGYSS_0);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "big_fungyss_1"), BIG_FUNGYSS_1);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "mega_fungyss_0"), MEGA_FUNGYSS_0);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "mega_fungyss_1"), MEGA_FUNGYSS_1);

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "flower_yellow_orchid"), YELLOW_ORCHID);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "edelwood"), EDELWOOD);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "petrified_root"), PETRIFIED_ROOT);

    }
    private static final class States {
        private static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.getDefaultState();
        private static final BlockState STONE = Blocks.STONE.getDefaultState();
        private static final BlockState ARCANE_CRYSTAL_ORE = BlockRegistry.ARCANE_CRYSTAL_ORE.getDefaultState();
        private static final BlockState DEEPSLATE_ARCANE_CRYSTAL_ORE = BlockRegistry.DEEPSLATE_ARCANE_CRYSTAL_ORE.getDefaultState();
        private static final BlockState RUNIC_STONE = BlockRegistry.RUNIC_STONE.getDefaultState();
        private static final BlockState RUNIC_DEEPSLATE = BlockRegistry.RUNIC_DEEPSLATE.getDefaultState();
        private static final BlockState RUNIC_DARKSTONE = BlockRegistry.RUNIC_DARKSTONE.getDefaultState();
        private static final BlockState DARKSTONE = BlockRegistry.DARKSTONE.getDefaultState();
        private static final BlockState ARCANE_GILDED_DARKSTONE = BlockRegistry.ARCANE_GILDED_DARKSTONE.getDefaultState();
        private static final BlockState STELLA_ARCANUM = BlockRegistry.STELLA_ARCANUM.getDefaultState();
        private static final BlockState XPETRIFIED_ORE = BlockRegistry.XPETRIFIED_ORE.getDefaultState();
        private static final BlockState CHERRYWOOD_LOG = BlockRegistry.CHERRYWOOD_LOG.getDefaultState();
        private static final BlockState CHERRYWOOD_LEAVES = BlockRegistry.CHERRYWOOD_LEAVES.getDefaultState();
        private static final BlockState CHERRYWOOD_SAPLING = BlockRegistry.CHERRYWOOD_SAPLING.getDefaultState();
        private static final BlockState MYSTERYWOOD_LOG = BlockRegistry.MYSTERYWOOD_LOG.getDefaultState();
        private static final BlockState MYSTERYWOOD_LEAVES = BlockRegistry.MYSTERYWOOD_LEAVES.getDefaultState();
        private static final BlockState NUGGETY_MYSTERYWOOD_LEAVES = BlockRegistry.NUGGETY_MYSTERYWOOD_LEAVES.getDefaultState();
        private static final BlockState MYSTERYWOOD_SAPLING = BlockRegistry.MYSTERYWOOD_SAPLING.getDefaultState();
        private static final BlockState YELLOW_ORCHID = BlockRegistry.YELLOW_ORCHID.getDefaultState();
        private static final BlockState EDELWOOD_LOG = BlockRegistry.EDELWOOD_LOG.getDefaultState();
        private static final BlockState PETRIFIED_ROOT = BlockRegistry.PETRIFIED_ROOT.getDefaultState();
        private static final BlockState FUNGYSS_BLOCK = BlockRegistry.FUNGYSS_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false);
        private static final BlockState FUNGYSS_STEM = BlockRegistry.FUNGYSS_STEM.getDefaultState();
        private static final BlockState FUNGYSS_HYPHAE = BlockRegistry.FUNGYSS_HYPHAE.getDefaultState();
    }
}
