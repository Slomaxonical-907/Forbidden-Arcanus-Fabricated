package com.slomaxonical.forbidden_arcanus.common.worldgen.feature;

import com.mojang.serialization.Codec;
import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.config.BigFungyssConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.ConnectingBlock;
import net.minecraft.block.MushroomBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class BigFungyssFeature extends Feature<BigFungyssConfig> {

    public BigFungyssFeature(Codec<BigFungyssConfig> codec) {
        super(codec);
    }

    private int getRandomHeight(Random random, int variant) {
        if (variant == 0) {
            return random.nextInt(3) + 4;
        } else {
            return random.nextInt(2) + 6;
        }
    }

    private boolean canGenerate(WorldAccess world, ChunkGenerator generator, BlockPos pos, int height, BlockPos.Mutable mutable) {
        if (pos.getY() < 1 || pos.getY() + height + 1 >= generator.getWorldHeight()) {
            return false;
        }

        if (!world.getBlockState(pos.down()).isIn(BlockTags.BASE_STONE_OVERWORLD)) {
            return false;
        }

        for (int i = 0; i <= height; i++) {
            BlockState state = world.getBlockState(mutable.set(pos, 0, i, 0));

            if (!state.isAir()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean generate(FeatureContext<BigFungyssConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        Random rand = context.getRandom();

        int height = this.getRandomHeight(rand, context.getConfig().variant);
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        if (!this.canGenerate(world, context.getGenerator(), pos, height, mutable)) {
            return false;
        }

        this.placeCap(world, rand, pos, height, mutable, context.getConfig());
        this.placeStem(world, rand, pos, height, mutable, context.getConfig());

        return true;
    }

    private void placeStem(WorldAccess world, Random random, BlockPos pos, int height, BlockPos.Mutable mutable, BigFungyssConfig config) {
        for (int i = 0; i < height; i++) {
            mutable.set(pos).move(Direction.UP, i);

            if (!world.getBlockState(mutable).isOpaqueFullCube(world, mutable)) {
                this.setBlockState(world, mutable, config.stemProvider.getBlockState(random, pos));
            }
        }
    }

    private void placeCap(WorldAccess world, Random random, BlockPos pos, int height, BlockPos.Mutable mutable, BigFungyssConfig config) {
        if (config.variant == 0) {
            int distanceToStem = 1;
            for (int i = height - 2; i <= height; i++) {
                for (int xOffset = -distanceToStem; xOffset <= distanceToStem; xOffset++) {
                    for (int zOffset = -distanceToStem; zOffset <= distanceToStem; zOffset++) {
                        if ((i < height && !(xOffset == 0 && zOffset == 0)) || !isCorner(xOffset, zOffset, distanceToStem)) {
                            mutable.set(pos, xOffset, i, zOffset);
                            this.setBlockState(world, mutable, config.capProvider.getBlockState(random, pos));
                        }
                    }
                }
            }
            this.setBlockState(world, pos.up(height), config.capProvider.getBlockState(random, pos));
        } else {
            for (int i = height - 2; i <= height; i++) {
                int distanceToStem = i < height ? 2 : 1;

                for (int xOffset = -distanceToStem; xOffset <= distanceToStem; xOffset++) {
                    for (int zOffset = -distanceToStem; zOffset <= distanceToStem; zOffset++) {
                        if (i >= height || !isCorner(xOffset, zOffset, distanceToStem)) {
                            mutable.set(pos, xOffset, i, zOffset);
                            this.setBlockState(world, mutable, config.capProvider.getBlockState(random, pos)
                                    .with(MushroomBlock.UP, i >= height - 1).with(MushroomBlock.WEST, xOffset < 0).with(MushroomBlock.EAST, xOffset > 0).with(MushroomBlock.NORTH, zOffset < 0).with(MushroomBlock.SOUTH, zOffset > 0));
                        }
                    }
                }
            }

            for (Direction direction : Direction.values()) {
                if (direction.getAxis() != Direction.Axis.Y) {
                    mutable.set(pos, 0, height - 4, 0);
                    mutable.move(direction);
                    this.setBlockState(world, mutable, config.capProvider.getBlockState(random, pos).with(ConnectingBlock.FACING_PROPERTIES.get(direction.getOpposite()), false));
                }
            }
        }
    }

    private boolean isCorner(int xOffset, int zOffset, int distanceToStem) {
        return (xOffset == -distanceToStem || xOffset == distanceToStem) == (zOffset == -distanceToStem || zOffset == distanceToStem);
    }
}