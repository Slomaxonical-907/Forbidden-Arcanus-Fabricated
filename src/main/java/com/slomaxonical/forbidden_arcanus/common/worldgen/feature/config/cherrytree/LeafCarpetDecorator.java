package com.slomaxonical.forbidden_arcanus.common.worldgen.feature.config.cherrytree;

import com.mojang.serialization.Codec;
import com.slomaxonical.forbidden_arcanus.core.registries.world.ConfiguredFeatureRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class LeafCarpetDecorator extends TreeDecorator {

    public static final Codec<LeafCarpetDecorator> CODEC = BlockStateProvider.TYPE_CODEC.fieldOf("provider").xmap(LeafCarpetDecorator::new, (decorator) -> {
        return decorator.provider;
    }).codec();

    private final BlockStateProvider provider;

    public LeafCarpetDecorator(BlockStateProvider provider) {
        this.provider = provider;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return ConfiguredFeatureRegistry.LEAF_CARPET_DECORATOR;
    }

    @Override
    public void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, List<BlockPos> logPositions, List<BlockPos> leafPositions) {
        leafPositions.forEach(pos -> {
            if (random.nextDouble() < 0.1D) {
                BlockPos groundPos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos);

                if (world.testBlockState(groundPos, state -> state.getMaterial().isReplaceable())
                        && !world.testBlockState(groundPos.down(), state -> state.isOf(provider.getBlockState(random, groundPos).getBlock()))
                        && !logPositions.contains(groundPos.down())) {
                    replacer.accept(groundPos, provider.getBlockState(random, groundPos));
                }
            }
        });
    }
}
