package com.slomaxonical.forbidden_arcanus.common.worldgen.feature.config.cherrytree;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.slomaxonical.forbidden_arcanus.common.block.ThinLogBlock;
import com.slomaxonical.forbidden_arcanus.core.registries.world.ConfiguredFeatureRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class CherryFoliagePlacer extends FoliagePlacer {

    public static final Codec<CherryFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> {
        return fillFoliagePlacerFields(instance).apply(instance, CherryFoliagePlacer::new);
    });

    private static final List<Pair<Integer, Integer>> LAYOUT = ImmutableList.of(Pair.of(0, 3), Pair.of(-2, 4), Pair.of(-2, 4), Pair.of(-2, 4), Pair.of(0, 3));
    private static final List<Pair<Integer, Integer>> LAYOUT_TOP = ImmutableList.of(Pair.of(0, 1), Pair.of(-1, 2), Pair.of(0, 1));

    public CherryFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Nonnull
    @Override
    protected FoliagePlacerType<?> getType() {
        return ConfiguredFeatureRegistry.CHERRY_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
        BlockPos pos = treeNode.getCenter();
        List<Direction> directions = new ArrayList<>();
        BlockStateProvider trunkProvider = this.getTrunkProvider(config);

        for (Direction direction : Direction.Type.HORIZONTAL) {

            if (world.testBlockState(pos.down().offset(direction), state -> state.isOf(trunkProvider.getBlockState(random, pos).getBlock()))) {
                directions.add(direction);
            }
        }

        if (directions.size() == 1 && world.testBlockState(pos.down(2), state -> state.isOf(trunkProvider.getBlockState(random, pos).getBlock()) && state.get(ThinLogBlock.AXIS) == Direction.Axis.Y)) {
            BlockPos.Mutable mutable = pos.mutableCopy();
            Direction direction = directions.get(0).getOpposite();

            mutable.move(Direction.DOWN).move(direction);
            placeFoliageBlock(world, replacer, random, config, mutable);

            mutable.move(direction);
            placeFoliageBlock(world, replacer, random, config, mutable);

            mutable.move(direction.getOpposite(), 2).move(direction.rotateYClockwise());
            placeFoliageBlock(world, replacer, random, config, mutable);

            mutable.move(direction);
            placeFoliageBlock(world, replacer, random, config, mutable);

            mutable.move(direction.getOpposite()).move(direction.rotateYCounterclockwise(), 2);
            placeFoliageBlock(world, replacer, random, config, mutable);

            mutable.move(direction);
            placeFoliageBlock(world, replacer, random, config, mutable);
        } else {
            BlockPos.Mutable mutable = pos.mutableCopy();

            Direction direction = null;
            Direction.Axis axis;

            if (world.testBlockState(mutable.move(Direction.DOWN, 2), state -> state.isOf(trunkProvider.getBlockState(random, pos).getBlock()) && state.get(ThinLogBlock.AXIS) == Direction.Axis.X)) {
                axis = Direction.Axis.X;
            } else {
                axis = Direction.Axis.Z;
            }

            for (Direction.AxisDirection axisDirection : Direction.AxisDirection.values()) {
                Direction offsetDirection = Direction.from(axis, axisDirection);

                if (world.testBlockState(mutable.move(offsetDirection), state -> state.isOf(trunkProvider.getBlockState(random, pos).getBlock()))) {
                    direction = offsetDirection.getOpposite();
                    break;
                }
            }

            if (direction != null) {
                this.placeFromLayout(world, replacer, random, config, LAYOUT, pos, direction);
                this.placeFromLayout(world, replacer, random, config, LAYOUT_TOP, pos.up(), direction);

                for (Direction direction1 : Direction.Type.HORIZONTAL) {
                    mutable.set(pos.getX(), pos.getY() - 1, pos.getZ());

                    if (direction1 != direction.getOpposite()) {
                        placeFoliageBlock(world, replacer, random, config, mutable.move(direction1));
                    }
                }
            }
        }
    }

    private void placeFromLayout(@Nonnull TestableWorld world, @Nonnull BiConsumer<BlockPos, BlockState> replacer, @Nonnull Random random, @Nonnull TreeFeatureConfig config, List<Pair<Integer, Integer>> layout, BlockPos pos, Direction direction) {
        BlockPos.Mutable mutable = pos.mutableCopy();

        mutable.move(direction.rotateYClockwise(), (layout.size() - 1) / 2);

        for (int row = 0; row < layout.size(); row++) {
            Pair<Integer, Integer> pair = layout.get(row);

            for (int i = pair.getFirst(); i < 1 + pair.getSecond(); i++) {
                if ((i == pair.getFirst() || i == 1 + pair.getSecond()) && random.nextBoolean() && !this.isMiddleRow(layout, row)) {
                    continue;
                }
                placeFoliageBlock(world, replacer, random, config, mutable.offset(direction, i));
            }

            mutable.move(direction.rotateYCounterclockwise());
        }
    }

    private boolean isMiddleRow(List<Pair<Integer, Integer>> layout, int i) {
        return layout.size() / 2 == i;
    }

    private BlockStateProvider getTrunkProvider(TreeFeatureConfig configuration) {
        if (configuration instanceof CherryTreeConfig cherryTreeConfiguration) {
            return cherryTreeConfiguration.thinTrunkProvider;
        }

        return configuration.trunkProvider;
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return 0;
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }

    @Override
    protected boolean isPositionInvalid(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }
}
