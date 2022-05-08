package com.slomaxonical.forbidden_arcanus.common.worldgen.feature.config.cherrytree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.FeatureSize;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.trunk.TrunkPlacer;

import java.util.List;

public class CherryTreeConfig extends TreeFeatureConfig {

    public static final Codec<CherryTreeConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("trunk_provider").forGetter((configuration) -> {
            return configuration.trunkProvider;
        }), BlockStateProvider.TYPE_CODEC.fieldOf("thin_trunk_provider").forGetter(configuration -> {
            return configuration.thinTrunkProvider;
        }), TrunkPlacer.TYPE_CODEC.fieldOf("trunk_placer").forGetter((configuration) -> {
            return configuration.trunkPlacer;
        }), BlockStateProvider.TYPE_CODEC.fieldOf("foliage_provider").forGetter((configuration) -> {
            return configuration.foliageProvider;
        }), FoliagePlacer.TYPE_CODEC.fieldOf("foliage_placer").forGetter((configuration) -> {
            return configuration.foliagePlacer;
        }), BlockStateProvider.TYPE_CODEC.fieldOf("dirt_provider").forGetter((configuration) -> {
            return configuration.dirtProvider;
        }), FeatureSize.TYPE_CODEC.fieldOf("minimum_size").forGetter((configuration) -> {
            return configuration.minimumSize;
        }), TreeDecorator.TYPE_CODEC.listOf().fieldOf("decorators").forGetter((configuration) -> {
            return configuration.decorators;
        }), Codec.BOOL.fieldOf("ignore_vines").orElse(false).forGetter((configuration) -> {
            return configuration.ignoreVines;
        }), Codec.BOOL.fieldOf("force_dirt").orElse(false).forGetter((configuration) -> {
            return configuration.forceDirt;
        })).apply(instance, CherryTreeConfig::new);
    });

    public final BlockStateProvider thinTrunkProvider;

    public CherryTreeConfig(BlockStateProvider trunkProvider, BlockStateProvider thinTrunkProvider, TrunkPlacer trunkPlacer, BlockStateProvider foliageProvider, FoliagePlacer foliagePlacer, FeatureSize featureSize) {
        this(trunkProvider, thinTrunkProvider, trunkPlacer, foliageProvider, foliagePlacer, BlockStateProvider.of(Blocks.DIRT), featureSize, List.of(new LeafCarpetDecorator(BlockStateProvider.of(BlockRegistry.CHERRYWOOD_LEAF_CARPET))), true, false);
    }

    public CherryTreeConfig(BlockStateProvider trunkProvider, BlockStateProvider thinTrunkProvider, TrunkPlacer trunkPlacer, BlockStateProvider foliageProvider, FoliagePlacer foliagePlacer, BlockStateProvider dirtProvider, FeatureSize featureSize, List<TreeDecorator> decorators, boolean ignoreVines, boolean forceDirt) {
        super(trunkProvider, trunkPlacer, foliageProvider, foliagePlacer, dirtProvider, featureSize, decorators, ignoreVines, forceDirt);
        this.thinTrunkProvider = thinTrunkProvider;
    }
}
