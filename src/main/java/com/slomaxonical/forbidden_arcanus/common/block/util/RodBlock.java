package com.slomaxonical.forbidden_arcanus.common.block.util;

import com.slomaxonical.forbidden_arcanus.common.block.util.FAPillarBlock;
import com.slomaxonical.forbidden_arcanus.common.block.util.PillarType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.valhelsia.valhelsia_core.common.helper.VoxelShapeHelper;

import java.util.EnumMap;

public class RodBlock extends FAPillarBlock {

    private static final VoxelShape[] SHAPE_PARTS = {
            Block.createCuboidShape(6, 0, 6, 10, 16, 10),
            Block.createCuboidShape(5, 0, 5, 11, 2, 11),
            Block.createCuboidShape(5, 14, 5, 11, 16, 11),
    };
    private final EnumMap<PillarType, EnumMap<Direction.Axis, VoxelShape>> shapes;

    public RodBlock(Settings settings) {
        super(settings);
        this.shapes = this.buildShapes();
    }

    private EnumMap<PillarType, EnumMap<Direction.Axis, VoxelShape>> buildShapes() {
        EnumMap<PillarType, EnumMap<Direction.Axis, VoxelShape>> map = new EnumMap<>(PillarType.class);

        map.put(PillarType.MIDDLE, VoxelShapeHelper.rotateAxis(SHAPE_PARTS[0]));
        map.put(PillarType.TOP, VoxelShapeHelper.rotateAxis(VoxelShapes.union(SHAPE_PARTS[0], SHAPE_PARTS[2])));
        map.put(PillarType.BOTTOM, VoxelShapeHelper.rotateAxis(VoxelShapes.union(SHAPE_PARTS[0], SHAPE_PARTS[1])));
        map.put(PillarType.SINGLE, VoxelShapeHelper.rotateAxis(VoxelShapeHelper.combineAll(SHAPE_PARTS)));

        return map;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return shapes.get(state.get(TYPE)).get(state.get(AXIS));
    }
}
