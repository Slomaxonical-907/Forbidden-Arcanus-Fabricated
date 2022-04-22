package com.slomaxonical.forbidden_arcanus.common.block.util;

import com.slomaxonical.forbidden_arcanus.core.helper.FAHelper;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.valhelsia.valhelsia_core.common.helper.VoxelShapeHelper;
import net.valhelsia.valhelsia_core.common.util.ItemStackUtils;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.EnumMap;
import java.util.Objects;

public class FAPillarBlock extends PillarBlock implements Waterloggable {
    public static final EnumProperty<PillarType> TYPE = FABlockProperties.PILLAR_TYPE;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private static final VoxelShape[] SHAPE_PARTS = {
            Block.createCuboidShape(0, 14, 0, 16, 16, 16),
            Block.createCuboidShape(1, 13, 1, 15, 14, 15),
            Block.createCuboidShape(2, 0, 2, 14, 16, 14),
            Block.createCuboidShape(0, 0, 0, 16, 2, 16),
            Block.createCuboidShape(1, 2, 1, 15, 3, 15)
    };
    private final EnumMap<PillarType, EnumMap<Direction.Axis, VoxelShape>> shapes;

    public FAPillarBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(AXIS, Direction.Axis.Y).with(TYPE, PillarType.SINGLE).with(AXIS, Direction.Axis.Y).with(WATERLOGGED, false));
        this.shapes = this.buildShapes();
    }

    private EnumMap<PillarType, EnumMap<Direction.Axis, VoxelShape>> buildShapes() {
        EnumMap<PillarType, EnumMap<Direction.Axis, VoxelShape>> map = new EnumMap<>(PillarType.class);

        map.put(PillarType.MIDDLE, VoxelShapeHelper.rotateAxis(SHAPE_PARTS[2]));
        map.put(PillarType.TOP, VoxelShapeHelper.rotateAxis(VoxelShapes.union(SHAPE_PARTS[0], SHAPE_PARTS[1], SHAPE_PARTS[2])));
        map.put(PillarType.BOTTOM, VoxelShapeHelper.rotateAxis(VoxelShapes.union(SHAPE_PARTS[3], SHAPE_PARTS[4], SHAPE_PARTS[2])));
        map.put(PillarType.SINGLE, VoxelShapeHelper.rotateAxis(VoxelShapeHelper.combineAll(SHAPE_PARTS)));

        return map;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.shapes.get(state.get(TYPE)).get(state.get(AXIS));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        boolean flag = ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER;
        return Objects.requireNonNull(super.getPlacementState(ctx)).with(WATERLOGGED, flag);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        if (direction.getAxis() != state.get(AXIS)) {
            return state;
        }

        return state.with(TYPE, this.tryConnect(state, world, pos));
    }

    private PillarType tryConnect(BlockState state, WorldAccess world, BlockPos pos) {
        Direction.Axis axis = state.get(AXIS);

        BlockState stateDown = world.getBlockState(pos.offset(Direction.get(Direction.AxisDirection.NEGATIVE, axis)));
        BlockState stateUp = world.getBlockState(pos.offset(Direction.get(Direction.AxisDirection.POSITIVE, axis)));

        boolean axisUpEqual = stateUp.isOf(this) && stateUp.get(AXIS) == axis;
        boolean axisDownEqual = stateDown.isOf(this) && stateDown.get(AXIS) == axis;

        return PillarType.getTypeForConnections(axisUpEqual, axisDownEqual);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, AXIS, WATERLOGGED);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
}
