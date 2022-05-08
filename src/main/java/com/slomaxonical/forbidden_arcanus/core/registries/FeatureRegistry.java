package com.slomaxonical.forbidden_arcanus.core.registries;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.config.cherrytree.CherryFoliagePlacer;
import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.config.cherrytree.CherryTreeConfig;
import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.config.CherryTrunkPlacer;
import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.config.cherrytree.LeafCarpetDecorator;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.OptionalInt;

public class FeatureRegistry {
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
            new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(BlockRegistry.MYSTERYWOOD_LEAVES.getDefaultState(), 4).add(BlockRegistry.NUGGETY_MYSTERYWOOD_LEAVES.getDefaultState(), 1)),
            new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
            new TwoLayersFeatureSize(0, 0, 0,OptionalInt.of(4)))
            .ignoreVines().build());

    public static final FoliagePlacerType<?> CHERRY_FOLIAGE_PLACER = new FoliagePlacerType<>(CherryFoliagePlacer.CODEC);
    public static final TrunkPlacerType<?> CHERRY_TRUNK_PLACER = new TrunkPlacerType<>(CherryTrunkPlacer.CODEC);
    public static final TreeDecoratorType<?> LEAF_CARPET_DECORATOR = new TreeDecoratorType<>(LeafCarpetDecorator.CODEC);

    public static void register(){
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "cherrywood"), CHERRYWOOD_TREE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "fancy_cherrywood"), FANCY_CHERRYWOOD_TREE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "mysterywood"), MYSTERYWOOD_TREE);

        Registry.register(Registry.FOLIAGE_PLACER_TYPE, new Identifier(ForbiddenArcanus.MOD_ID,"cherry_foliage_placer"), CHERRY_FOLIAGE_PLACER);
        Registry.register(Registry.TRUNK_PLACER_TYPE, new Identifier(ForbiddenArcanus.MOD_ID,"cherry_trunk_placer"), CHERRY_TRUNK_PLACER);
        Registry.register(Registry.TREE_DECORATOR_TYPE, new Identifier(ForbiddenArcanus.MOD_ID,"leaf_carpet_decorator"), LEAF_CARPET_DECORATOR);

    }
}
