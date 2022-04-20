package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.core.helper.FAUtils;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

import java.util.*;

public class ThinLogBlock extends PillarBlock implements Waterloggable {
    public static final BooleanProperty NORTH = Properties.NORTH;
    public static final BooleanProperty EAST = Properties.EAST;
    public static final BooleanProperty SOUTH = Properties.SOUTH;
    public static final BooleanProperty WEST = Properties.WEST;

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private static final EnumMap<Direction.Axis, VoxelShape> SHAPES = FAUtils.rotateAxis(Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D));

    private static final VoxelShape CONNECT_SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D);

    private final Map<BlockState, VoxelShape> shapesCache;

    public static final EnumMap<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = Util.make(new EnumMap<>(Direction.class), (map) -> {
        map.put(Direction.NORTH, NORTH);
        map.put(Direction.EAST, EAST);
        map.put(Direction.SOUTH, SOUTH);
        map.put(Direction.WEST, WEST);
    });

    public ThinLogBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(AXIS, Direction.Axis.Y).with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(WATERLOGGED, false));
        this.shapesCache = this.getShapesForStates(this::calculateShape);

    }
    private VoxelShape calculateShape(BlockState state) {
        List<VoxelShape> connectedSides = new ArrayList<>();

        for (Direction direction : Direction.values()) {
            Direction.Axis axis = state.get(AXIS);

            if (axis == direction.getAxis()) {
                continue;
            }

            if (state.get(PROPERTY_BY_DIRECTION.get(getRotatedDirection(direction, axis)))) {
                connectedSides.add(FAUtils.rotateShape(CONNECT_SHAPE, direction));
            }
        }

        return VoxelShapes.union(SHAPES.get(state.get(AXIS)), connectedSides.toArray(new VoxelShape[0]));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.shapesCache.get(state);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = Objects.requireNonNull(super.getPlacementState(ctx));
        boolean flag = ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER;

        for (Direction direction : Direction.values()) {
            Direction.Axis axis = state.get(AXIS);

            if (axis == direction.getAxis()) {
                continue;
            }

            state = state.with(PROPERTY_BY_DIRECTION.get(getRotatedDirection(direction, axis)), this.shouldConnect(state, ctx.getWorld().getBlockState(ctx.getBlockPos().offset(direction))));
        }
        return state.with(WATERLOGGED, flag);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        Direction.Axis axis = state.get(AXIS);

        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        if (direction.getAxis() == axis) {
            return state;
        }

        return state.with(PROPERTY_BY_DIRECTION.get(getRotatedDirection(direction, axis)), this.shouldConnect(state, neighborState));
    }

    private boolean shouldConnect(BlockState state, BlockState neighborState) {
        return neighborState.getBlock() instanceof ThinLogBlock && state.get(AXIS) != neighborState.get(AXIS);
    }

    public static Direction getRotatedDirection(Direction direction, Direction.Axis axis) {
        if (axis == Direction.Axis.X) {
            return switch (direction) {
                case UP -> Direction.EAST;
                case DOWN -> Direction.WEST;
                default -> direction;
            };
        } else if (axis == Direction.Axis.Z) {
            return switch (direction) {
                case UP -> Direction.NORTH;
                case DOWN -> Direction.SOUTH;
                default -> direction;
            };
        }

        return direction;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS,NORTH,EAST, SOUTH, WEST, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
}
