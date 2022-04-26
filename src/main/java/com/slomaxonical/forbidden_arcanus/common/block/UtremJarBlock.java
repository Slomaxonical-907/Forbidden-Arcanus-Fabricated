package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.common.blockEntity.UtremJarBlockEntity;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.valhelsia.valhelsia_core.common.helper.VoxelShapeHelper;
import net.valhelsia.valhelsia_core.common.util.ItemStackUtils;
import org.jetbrains.annotations.Nullable;

public class UtremJarBlock extends Block implements Waterloggable, BlockEntityProvider {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private static final VoxelShape SHAPE = VoxelShapeHelper.combineAll(
            Block.createCuboidShape(3, 0, 3, 13, 13, 13),
            Block.createCuboidShape(5, 13, 5, 11, 15, 11)
    );
    public UtremJarBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(WATERLOGGED, false));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new UtremJarBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        boolean flag = context.getWorld().getFluidState(context.getBlockPos()).getFluid() == Fluids.WATER;
        return this.getDefaultState().with(WATERLOGGED, flag);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
//        ItemStack stack = player.getStackInHand(hand);
//
//        if (!(world.getBlockEntity(pos) instanceof UtremJarBlockEntity blockEntity) || player.isSneaking()) {
//            return super.onUse(state, world, pos, player, hand, hit);
//        }
//
////        IFluidHandler fluidHandler = blockEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).resolve().get();
//
//        if (FluidUtil.interactWithFluidHandler(player, hand, fluidHandler)) {
//            player.getInventory().markDirty();
//
//            return ActionResult.success(world.isClient());
//        } else if (fluidHandler.getFluidInTank(0).isEmpty()) {
//            BlockState newState = null;
//
//            if (stack.isOf(ItemRegistry.PIXIE)) {
//                newState = BlockRegistry.PIXIE_UTREM_JAR.getDefaultState();
//            } else if (stack.isOf(ItemRegistry.CORRUPTED_PIXIE)) {
//                newState = BlockRegistry.CORRUPTED_PIXIE_UTREM_JAR.getDefaultState();
//            }
//
//            if (newState != null) {
//                ItemStackUtils.shrinkStack(player, stack);
//
//                world.setBlockState(pos, newState.with(WATERLOGGED, state.get(WATERLOGGED)));
//                return ActionResult.success(world.isClient());
//            }
//        }

        return super.onUse(state, world, pos, player, hand, hit);
    }
    public int getLightEmission(BlockState state, BlockView world, BlockPos pos) {return 0;}//todo:setsluminance to this thing depening on the fluid

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
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
