package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.common.blockEntity.BlackHoleBlockEntity;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import com.slomaxonical.forbidden_arcanus.mixin.CheckTypeInvoker;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class BlackHoleBlock extends Block implements BlockEntityProvider {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(5.0D, 5.0D, 5.0D, 11.0D, 11.0D, 11.0D);

    public BlackHoleBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new BlackHoleBlockEntity(pos, state);
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape(@Nonnull BlockState state, @Nonnull BlockView level, @Nonnull BlockPos pos, @Nonnull ShapeContext context) {
        return SHAPE;
    }

    @Nonnull
    @Override
    public BlockRenderType getRenderType(@Nonnull BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient()) {
                return CheckTypeInvoker.invokeCheckType(type, BlockEntityRegistry.BLACK_HOLE, BlackHoleBlockEntity::clientTick);
        }
        return CheckTypeInvoker.invokeCheckType(type, BlockEntityRegistry.BLACK_HOLE, BlackHoleBlockEntity::serverTick);
    }


    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random rand) {
        for (int i = 0; i < 3; i++) {
            int j = rand.nextInt(2) * 2 - 1;
            int k = rand.nextInt(2) * 2 - 1;
            double x = pos.getX() + 0.5D + 0.25D * j;
            double y = pos.getY() + 0.5D;
            double z = pos.getZ() + 0.5D + 0.25D * k;

            world.addParticle(ParticleTypes.PORTAL, x, y, z, rand.nextFloat() * j, (rand.nextFloat() - 0.5D) * 0.125D, rand.nextFloat() * k);
        }
    }
}
