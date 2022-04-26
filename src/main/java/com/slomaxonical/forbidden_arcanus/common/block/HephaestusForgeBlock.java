package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.common.block.util.FABlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.valhelsia.valhelsia_core.common.helper.VoxelShapeHelper;

public class HephaestusForgeBlock extends Block {

    public static final BooleanProperty ACTIVATED = FABlockProperties.ACTIVATED;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private static final VoxelShape SHAPE = VoxelShapes.combine(
            VoxelShapeHelper.combineAll(
                    createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 3.0D, 15.0D),
                    createCuboidShape(2.0D, 3.0D, 2.0D, 14.0D, 4.0D, 14.0D),
                    createCuboidShape(4.0D, 4.0D, 4.0D, 12.0D, 8.0D, 12.0D),
                    createCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D)
            ),
            VoxelShapeHelper.combineAll(
                    createCuboidShape(0.0D, 15.0D, 3.0D, 16.0D, 16.0D, 13.0D),
                    createCuboidShape(3.0D, 15.0D, 0.0D, 13.0D, 16.0D, 16.0D)
            ),
            BooleanBiFunction.ONLY_FIRST
    );
    public HephaestusForgeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(ACTIVATED, false).with(WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED,WATERLOGGED);
    }
}
