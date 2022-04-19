package com.slomaxonical.forbidden_arcanus.core.systems.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

public class WaterLoggedBlock<T extends BlockEntity> extends SimpleBlock<T> implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public WaterLoggedBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
        super.appendProperties(builder);
    }


    @Override
    public BlockState getPlacementState(ItemPlacementContext p_152803_) {
        FluidState fluidstate = p_152803_.getWorld().getFluidState(p_152803_.getBlockPos());
        boolean flag = fluidstate.getFluid() == Fluids.WATER;
        return super.getPlacementState(p_152803_).with(WATERLOGGED, flag);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState p_152833_, Direction p_152834_, BlockState p_152835_, WorldAccess p_152836_, BlockPos p_152837_, BlockPos p_152838_) {
        if (p_152833_.get(WATERLOGGED)) {
            p_152836_.createAndScheduleFluidTick(p_152837_, Fluids.WATER, Fluids.WATER.getTickRate(p_152836_));
        }
        return super.getStateForNeighborUpdate(p_152833_, p_152834_, p_152835_, p_152836_, p_152837_, p_152838_);
    }

    @Override
    public FluidState getFluidState(BlockState p_152844_) {
        return p_152844_.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(p_152844_);
    }
}
