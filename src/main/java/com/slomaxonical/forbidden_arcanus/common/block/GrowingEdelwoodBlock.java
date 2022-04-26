package com.slomaxonical.forbidden_arcanus.common.block;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class GrowingEdelwoodBlock extends CropBlock implements Fertilizable {
    private static final float BONEMEAL_CHANCE = 0.45F;
    private static final int REQUIRED_BRIGHTNESS = 9;

    private static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

    public GrowingEdelwoodBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return super.getOutlineShape(state, world, pos, context);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random rand, BlockPos pos, BlockState state) {
        return world.random.nextFloat() < BONEMEAL_CHANCE;
    }

    @Override
    public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        this.growEdelwood(world, pos, state, rand);
    }
    public void growEdelwood(ServerWorld world, BlockPos pos, BlockState state, Random random) {
//        if (!ForgeEventFactory.saplingGrowTree(world, random, pos)) {
//            return;
//        }

        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);

//        if (!ModConfiguredFeatures.EDELWOOD.value().place(world, world.getChunkManager().getChunkGenerator(), random, pos)) {
        world.setBlockState(pos, state, 4);
//        }
    }
}
