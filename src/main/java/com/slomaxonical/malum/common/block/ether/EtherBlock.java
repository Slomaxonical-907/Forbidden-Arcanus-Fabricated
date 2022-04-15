package com.slomaxonical.malum.common.block.ether;

import com.slomaxonical.malum.core.systems.block.SimpleBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class EtherBlock extends SimpleBlock implements Waterloggable {
    public static final VoxelShape SHAPE = Block.createCuboidShape(6, 6, 6, 10, 10, 10);
    public EtherBlock(Settings settings) {
        super(settings);
        setTile()
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
