package com.slomaxonical.forbidden_arcanus.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class MysterywoodLogBlock extends PillarBlock {
    private static final double PARTICLE_SPEED_MODIFIER = 0.3D;

    public MysterywoodLogBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double xSpeed = (random.nextFloat() - 0.5) * PARTICLE_SPEED_MODIFIER;
        double ySpeed = (random.nextFloat() - 0.5) * PARTICLE_SPEED_MODIFIER;
        double zSpeed = (random.nextFloat() - 0.5) * PARTICLE_SPEED_MODIFIER;
        world.addParticle(ParticleTypes.END_ROD, pos.getX() + random.nextFloat(), pos.getY() + random.nextFloat(), pos.getZ() + random.nextFloat(), xSpeed, ySpeed, zSpeed);
    }
}
