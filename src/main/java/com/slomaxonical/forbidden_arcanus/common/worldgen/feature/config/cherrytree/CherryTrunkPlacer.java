package com.slomaxonical.forbidden_arcanus.common.worldgen.feature.config.cherrytree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.slomaxonical.forbidden_arcanus.common.block.ThinLogBlock;
import com.slomaxonical.forbidden_arcanus.core.registries.world.ConfiguredFeatureRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class CherryTrunkPlacer extends TrunkPlacer {
    public static final Codec<CherryTrunkPlacer> CODEC = RecordCodecBuilder.create(instance -> {
        return fillTrunkPlacerFields(instance).apply(instance, CherryTrunkPlacer::new);
    });

    public CherryTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return ConfiguredFeatureRegistry.CHERRY_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int freeTreeHeight, BlockPos startPos, TreeFeatureConfig config) {
        List<FoliagePlacer.TreeNode> list = new ArrayList<>();
        List<Direction> branchDirections = new ArrayList<>();
        BlockPos.Mutable mutable = startPos.mutableCopy();

        int height = this.getHeight(random);

        for (int i = 0; i < height; i++) {
            if ((i > 1 && random.nextBoolean()) || (i + 1 == height && branchDirections.isEmpty())) {
                Direction direction = Direction.Type.HORIZONTAL.random(random);

                if (branchDirections.contains(direction)) {
                    direction = direction.getOpposite();
                }

                if (!branchDirections.contains(direction)) {
                    branchDirections.add(direction);

                    Direction finalDirection = direction;

                    this.placeThinLog(world, replacer, random, mutable, config, state -> {
                        BooleanProperty property = ThinLogBlock.PROPERTY_BY_DIRECTION.get(finalDirection);

                        if (state.contains(property)) {
                            return state.with(property, true);
                        }

                        return state;
                    });

                    list.add(this.placeBranch(world, replacer, random, freeTreeHeight, mutable.toImmutable(), direction, config));
                } else {
                    this.placeThinLog(world, replacer, random, mutable, config);
                }
            } else if (i == 0 && height >= 6) {
                getAndSetState(world, replacer, random, mutable, config);
            } else {
                this.placeThinLog(world, replacer, random, mutable, config);
            }

            mutable.move(Direction.UP);
        }

        list.add(new FoliagePlacer.TreeNode(mutable, 0, false));

        return list;
    }

    private FoliagePlacer.TreeNode placeBranch(@Nonnull TestableWorld world, @Nonnull BiConsumer<BlockPos, BlockState> blockSetter, @Nonnull Random random, int freeTreeHeight, @Nonnull BlockPos pos, Direction direction, @Nonnull TreeFeatureConfig config) {
        BlockPos.Mutable mutable = pos.mutableCopy();

        int length = 1 + random.nextInt(2);

        for (int i = 0; i < length; i++) {
            int finalI = i;

            this.placeThinLog(world, blockSetter, random, mutable.move(direction), config, (state) -> state
                    .with(PillarBlock.AXIS, direction.getAxis())
                    .with(ThinLogBlock.PROPERTY_BY_DIRECTION.get(ThinLogBlock.getRotatedDirection(Direction.UP, direction.getAxis())), finalI + 1 == length)
            );
        }

        boolean flag = random.nextBoolean();

        this.placeThinLog(world, blockSetter, random, mutable.move(Direction.UP), config, state -> state.with(ThinLogBlock.PROPERTY_BY_DIRECTION.get(direction), flag));

        if (flag) {
            this.placeThinLog(world, blockSetter, random, mutable.move(direction), config, (state) -> state
                    .with(PillarBlock.AXIS, direction.getAxis())
                    .with(ThinLogBlock.PROPERTY_BY_DIRECTION.get(ThinLogBlock.getRotatedDirection(Direction.UP, direction.getAxis())), true)
            );
            this.placeThinLog(world, blockSetter, random, mutable.move(Direction.UP), config);
        }

        return new FoliagePlacer.TreeNode(mutable.move(Direction.UP), 0, false);
    }

    protected boolean placeThinLog(TestableWorld level, BiConsumer<BlockPos, BlockState> blockSetter, Random random, BlockPos pos, TreeFeatureConfig config) {
        return placeThinLog(level, blockSetter, random, pos, config, Function.identity());
    }

    protected boolean placeThinLog(TestableWorld level, BiConsumer<BlockPos, BlockState> blockSetter, Random random, BlockPos pos, TreeFeatureConfig config, Function<BlockState, BlockState> propertySetter) {
        if (TreeFeature.canReplace(level, pos)) {
            if (config instanceof CherryTreeConfig cherryTreeConfiguration) {
                blockSetter.accept(pos, propertySetter.apply(cherryTreeConfiguration.thinTrunkProvider.getBlockState(random, pos)));
            } else {
                blockSetter.accept(pos, propertySetter.apply(config.trunkProvider.getBlockState(random, pos)));
            }

            return true;
        }
        return false;
    }
}
