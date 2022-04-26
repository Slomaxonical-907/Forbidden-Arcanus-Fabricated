package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.core.helper.FATags;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Random;

public class FungyssBlock extends PlantBlock implements Fertilizable {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D);

    public FungyssBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        //Changed the tag here... temporarily?
        return world.getBlockState(pos.down()).isIn(BlockTags.MUSHROOM_GROW_BLOCK);
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random rand, BlockPos pos, BlockState state) {
        return rand.nextDouble() < 0.4D;
    }

    @Override
    public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        for(int xOffset = 0; xOffset >= -1; --xOffset) {
            for(int zOffset = 0; zOffset >= -1; --zOffset) {
                if (canMegaFungyssSpawnAt(state, world, pos, xOffset, zOffset)) {
                    this.growMegaFungyss(world, pos, state, rand, xOffset, zOffset);
                    return;
                }
            }
        }
        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);

//        Holder<ConfiguredFeature<BigFungyssFeatureConfig, ?>> configuredFeature = world.random.nextBoolean() ? ModConfiguredFeatures.BIG_FUNGYSS_0 : ModConfiguredFeatures.BIG_FUNGYSS_1;

//        if (!configuredFeature.value().place(world, world.().getGenerator(), rand, pos)) {
            world.setBlockState(pos, state, 4);
//        }
    }
    private void growMegaFungyss(ServerWorld world, BlockPos pos, BlockState state, Random rand, int xOffset, int zOffset) {
        world.setBlockState(pos.add(xOffset, 0, zOffset), Blocks.AIR.getDefaultState(), 4);
        world.setBlockState(pos.add(xOffset + 1, 0, zOffset), Blocks.AIR.getDefaultState(), 4);
        world.setBlockState(pos.add(xOffset + 1, 0, zOffset + 1), Blocks.AIR.getDefaultState(), 4);
        world.setBlockState(pos.add(xOffset, 0, zOffset + 1), Blocks.AIR.getDefaultState(), 4);

//        Holder<ConfiguredFeature<BigFungyssFeatureConfig, ?>> configuredFeature = rand.nextBoolean() ? ModConfiguredFeatures.MEGA_FUNGYSS_0 : ModConfiguredFeatures.MEGA_FUNGYSS_1;

//        if (!configuredFeature.value().place(world, world.getChunkManager().getChunkGenerator(), rand, pos.add(xOffset, 0, zOffset))) {
            world.setBlockState(pos.add(xOffset, 0, zOffset), state, 4);
            world.setBlockState(pos.add(xOffset + 1, 0, zOffset), state, 4);
            world.setBlockState(pos.add(xOffset + 1, 0, zOffset + 1), state, 4);
            world.setBlockState(pos.add(xOffset, 0, zOffset + 1), state, 4);
//        }
    }

    private boolean canMegaFungyssSpawnAt(BlockState state, BlockView view, BlockPos pos, int xOffset, int zOffset) {
        Block block = state.getBlock();
        return block == view.getBlockState(pos.add(xOffset, 0, zOffset)).getBlock() && block == view.getBlockState(pos.add(xOffset + 1, 0, zOffset)).getBlock() && block == view.getBlockState(pos.add(xOffset, 0, zOffset + 1)).getBlock() && block == view.getBlockState(pos.add(xOffset + 1, 0, zOffset + 1)).getBlock();
    }
}
