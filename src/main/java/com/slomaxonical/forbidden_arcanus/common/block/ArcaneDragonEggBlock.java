package com.slomaxonical.forbidden_arcanus.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.valhelsia.valhelsia_core.common.helper.VoxelShapeHelper;

public class ArcaneDragonEggBlock extends FallingBlock {
    private static final VoxelShape SHAPE = VoxelShapeHelper.combineAll(
            Block.createCuboidShape(4, 0, 4, 12, 15, 12),
            Block.createCuboidShape(3, 1, 3, 13, 13, 13),
            Block.createCuboidShape(2, 3, 2, 14, 11, 14),
            Block.createCuboidShape(5, 15, 5, 11, 16, 11)
    );
    public ArcaneDragonEggBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected int getFallDelay() {
        return 5;
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }
}
