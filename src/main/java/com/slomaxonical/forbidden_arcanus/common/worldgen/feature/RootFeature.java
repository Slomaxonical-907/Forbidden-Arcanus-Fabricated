package com.slomaxonical.forbidden_arcanus.common.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class RootFeature extends Feature<SingleStateFeatureConfig> {
    private static final double DIRECTION_CHANGE_CHANCE = 0.25D;

    public RootFeature(Codec<SingleStateFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<SingleStateFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockState state = context.getConfig().state;
        BlockPos.Mutable pos = context.getOrigin().mutableCopy();
        Random rand = context.getRandom();

        world.setBlockState(pos, state, 2);

        for (int i = 1; i < 6; i++) {
            pos = this.tryChangeDirection(world, pos.move(Direction.DOWN), rand, DIRECTION_CHANGE_CHANCE);

            if (world.isAir(pos)) {
                world.setBlockState(pos, state, 2);
            } else {
                break;
            }
        }

        return true;
    }

    private BlockPos.Mutable tryChangeDirection(StructureWorldAccess worldAccess, BlockPos.Mutable pos, Random random, double chance) {
        if (random.nextFloat() >= chance) {
            return pos;
        }
        Direction direction = this.getRandomDirection(random);
        BlockPos.Mutable relativePos = pos.move(direction);

        return !worldAccess.getBlockState(relativePos).isOpaqueFullCube(worldAccess, relativePos) ? relativePos : pos;
    }

    private Direction getRandomDirection(Random random) {
        return Direction.fromHorizontal(random.nextInt(4));
    }
}

