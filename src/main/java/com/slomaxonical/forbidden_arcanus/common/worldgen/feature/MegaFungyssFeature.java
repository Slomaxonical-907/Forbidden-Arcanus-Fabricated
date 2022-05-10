package com.slomaxonical.forbidden_arcanus.common.worldgen.feature;

import com.mojang.serialization.Codec;
import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.config.BigFungyssConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.ConnectingBlock;
import net.minecraft.block.MushroomBlock;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class MegaFungyssFeature extends Feature<BigFungyssConfig> {

    public MegaFungyssFeature(Codec<BigFungyssConfig> codec) {
        super(codec);
    }

    private int getRandomHeight(Random random) {
        return random.nextInt(4) + 11;
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
        WorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        Random rand = context.getRandom();

        int height = this.getRandomHeight(context.getRandom());

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        if (!this.canGenerate(world, context.getGenerator(), pos, height, mutable)) {
            return false;
        }

        this.placeStem(world, rand, pos, height, mutable, context.getConfig());
        this.placeCap(world, rand, pos, height, mutable, context.getConfig());
        return true;
    }


    private void placeStem(WorldAccess world, Random random, BlockPos pos, int height, BlockPos.Mutable mutable, BigFungyssConfig config) {
        for (int i = 0; i < height; i++) {
            this.placeStemBlock(world, config.stemProvider.getBlockState(random, pos), mutable, pos, 0, i, 0);
            this.placeStemBlock(world, config.stemProvider.getBlockState(random, pos), mutable, pos, 1, i, 0);
            this.placeStemBlock(world, config.stemProvider.getBlockState(random, pos), mutable, pos, 1, i, 1);
            this.placeStemBlock(world, config.stemProvider.getBlockState(random, pos), mutable, pos, 0, i, 1);
        }
    }

    private void placeStemBlock(WorldAccess world, BlockState state, BlockPos.Mutable mutable, BlockPos pos, int xOffset, int yOffset, int zOffset) {
        mutable.set(pos).move(xOffset, yOffset, zOffset);

        if (!world.getBlockState(mutable).isOpaqueFullCube(world, mutable)) {
            this.setBlockState(world, mutable, state);
        }
    }

    private void placeCap(WorldAccess world, Random random, BlockPos pos, int height, BlockPos.Mutable mutable, BigFungyssConfig config) {
        for (int i = height - 3; i <= height; i++) {
            int distanceToStem = 2;

            for (int xOffset = -distanceToStem; xOffset <= distanceToStem + 1; xOffset++) {
                for (int zOffset = -distanceToStem; zOffset <= distanceToStem + 1; zOffset++) {
                    boolean flag1 = i >= height && xOffset != -distanceToStem && xOffset != distanceToStem + 1 && zOffset != -distanceToStem && zOffset != distanceToStem + 1;
                    boolean flag2 = i < height && (xOffset == -distanceToStem || xOffset == distanceToStem + 1 || zOffset == -distanceToStem || zOffset == distanceToStem + 1) && !((xOffset == -distanceToStem || xOffset == distanceToStem + 1) && (zOffset == -distanceToStem || zOffset == distanceToStem + 1));

                    if (flag1 || flag2) {
                        mutable.set(pos, xOffset, i, zOffset);

                        boolean moveDown = (xOffset == -1 && zOffset == -1) || (xOffset == -1 && zOffset == 2) || (xOffset == 2 && zOffset == -1) || (xOffset == 2 && zOffset == 2);
                        if (moveDown) {
                            mutable.move(Direction.DOWN);
                        }
                        BlockState state = i == height && !moveDown ? config.capProvider.getBlockState(random, pos) : config.capProvider.getBlockState(random, pos).with(MushroomBlock.WEST, xOffset < 0).with(MushroomBlock.EAST, xOffset > 0).with(MushroomBlock.NORTH, zOffset < 0).with(MushroomBlock.SOUTH, zOffset > 0);
                        this.setBlockState(world, mutable, state);
                    }
                }
            }

            for (int xOffset = 0; xOffset <= 1; xOffset++) {
                for (int zOffset = 0; zOffset <= 1; zOffset++) {
                    for (Direction direction : Direction.values()) {
                        if (direction.getAxis() != Direction.Axis.Y) {
                            mutable.set(pos, xOffset, height - 5, zOffset);
                            mutable.move(direction);
                            if (world.getBlockState(mutable).isAir()) {
                                this.setBlockState(world, mutable, config.capProvider.getBlockState(random, pos).with(ConnectingBlock.FACING_PROPERTIES.get(direction.getOpposite()), false));
                            }
                        }
                    }
                }
            }
        }
        int xOffset = random.nextInt(2);
        int zOffset = random.nextInt(2);

        if (config.variant == 0) {
            placeSmallCap(world, random, pos, height, mutable, xOffset, zOffset, config);
            placeSmallCap(world, random, pos, height - 2, mutable, xOffset == 0 ? 1 : 0, zOffset == 0 ? 1 : 0, config);
        } else {
            placeSmallFungyss(world, random, pos, height - 1, mutable, xOffset, zOffset, config);
            placeSmallFungyss(world, random, pos, height - 2, mutable, xOffset == 0 ? 1 : 0, zOffset == 0 ? 1 : 0, config);
        }
    }

    private void placeSmallCap(WorldAccess world, Random random, BlockPos pos, int height, BlockPos.Mutable mutable, int xOffset, int zOffset, BigFungyssConfig config) {
        Direction direction = getDirectionFromOffset(xOffset, zOffset);

        mutable.set(pos, xOffset, height - 8, zOffset);
        mutable.move(direction);

        this.setBlockState(world, mutable, config.capProvider.getBlockState(random, pos).with(ConnectingBlock.FACING_PROPERTIES.get(direction.getOpposite()), false));

        for (int i = 0; i <= 1; i++) {
            direction = direction == Direction.SOUTH ? Direction.EAST : Direction.fromHorizontal(direction.getHorizontal() - 1);
            mutable.move(direction);

            this.setBlockState(world, mutable, config.capProvider.getBlockState(random, pos).with(ConnectingBlock.FACING_PROPERTIES.get(direction.getOpposite().rotateYClockwise()), false));
        }
    }

    private void placeSmallFungyss(WorldAccess world, Random random, BlockPos pos, int height, BlockPos.Mutable mutable, int xOffset, int zOffset, BigFungyssConfig config) {
        Direction direction = getDirectionFromOffset(xOffset, zOffset);
        int stemHeight = world.getRandom().nextInt(2) + 2;

        mutable.set(pos, xOffset, height - 8, zOffset);
        mutable.move(direction);

        if (stemHeight == 3) {
            mutable.move(Direction.DOWN);
        }

        this.setBlockState(world, mutable, config.stemProvider.getBlockState(random, pos).with(Properties.AXIS, direction.getAxis()));

        mutable.move(direction);
        this.setBlockState(world, mutable, config.hyphaeProvider.getBlockState(random, pos).with(Properties.AXIS, direction.getAxis()));

        for (int i = 0; i < stemHeight; i++) {
            mutable.move(Direction.UP);
            this.setBlockState(world, mutable, config.stemProvider.getBlockState(random, pos).with(Properties.AXIS, Direction.Axis.Y));
        }

        pos = mutable.toImmutable();

        int distanceToStem = 1;
        for (int i = stemHeight; i <= stemHeight + 1; i++) {
            for (int xPos = -distanceToStem; xPos <= distanceToStem; xPos++) {
                for (int zPos = -distanceToStem; zPos <= distanceToStem; zPos++) {
                    if ((i < stemHeight + 1 && !(xPos == 0 && zPos == 0)) || !isCorner(xPos, zPos, distanceToStem)) {
                        mutable.set(pos, xPos, i - stemHeight, zPos);
                        this.setBlockState(world, mutable, config.capProvider.getBlockState(random, pos));
                    }
                }
            }
        }
        this.setBlockState(world, pos.up(), config.capProvider.getBlockState(random, pos));
    }

    private Direction getDirectionFromOffset(int xOffset, int zOffset) {
        if (xOffset == 0 && zOffset == 0) {
            return Direction.NORTH;
        } else if (xOffset == 0 && zOffset == 1) {
            return Direction.WEST;
        } else if (xOffset == 1 && zOffset == 0) {
            return Direction.EAST;
        }
        return Direction.SOUTH;
    }

    private boolean isCorner(int xOffset, int zOffset, int distanceToStem) {
        return (xOffset == -distanceToStem || xOffset == distanceToStem) == (zOffset == -distanceToStem || zOffset == distanceToStem);
    }
}