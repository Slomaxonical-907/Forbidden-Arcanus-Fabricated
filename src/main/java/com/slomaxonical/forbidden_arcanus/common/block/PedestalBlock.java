package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.common.block.util.FABlockProperties;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.PedestalBlockEntity;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import com.slomaxonical.forbidden_arcanus.mixin.CheckTypeInvoker;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
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

public class PedestalBlock extends Block implements Waterloggable, BlockEntityProvider {
    private static final VoxelShape SHAPE = VoxelShapeHelper.combineAll(
            createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 4.0D, 15.0D),
            createCuboidShape(3.0D, 4.0D, 3.0D, 13.0D, 6.0D, 13.0D),
            createCuboidShape(4.0D, 6.0D, 4.0D, 12.0D, 11.0D, 12.0D),
            createCuboidShape(2.0D, 11.0D, 2.0D, 14.0D, 14.0D, 14.0D)
    );

    public static final BooleanProperty RITUAL = FABlockProperties.RITUAL;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public PedestalBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(RITUAL, false).with(WATERLOGGED,false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PedestalBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();

        return this.getDefaultState()
                .with(RITUAL, world.getBlockState(pos.down()).isOf(BlockRegistry.ARCANE_CHISELED_POLISHED_DARKSTONE))
                .with(WATERLOGGED, world.getFluidState(pos).getFluid() == Fluids.WATER);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        if (direction != Direction.DOWN) {
            return state;
        }

        return state.with(RITUAL, world.getBlockState(neighborPos).isOf(BlockRegistry.ARCANE_CHISELED_POLISHED_DARKSTONE));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
            ItemStack stackInHand = player.getStackInHand(hand);

            if (!(world.getBlockEntity(pos) instanceof PedestalBlockEntity blockEntity)) {
                return ActionResult.PASS;
            }

            if (blockEntity.hasStack()) {
                ItemStack pedestalStack = blockEntity.getStack();
                blockEntity.markDirty();

                if (stackInHand.isEmpty()) {
                    player.setStackInHand(hand, pedestalStack);
                } else if (!player.giveItemStack(pedestalStack)) {
                    player.dropItem(pedestalStack, false);
                }

                blockEntity.clearStack(world, pos);

            } else if (!stackInHand.isEmpty() && !blockEntity.hasStack()) {
                blockEntity.setStackAndSync(stackInHand.copy().split(1), world, pos);

                ItemStackUtils.shrinkStack(player, stackInHand);

            } else {
                return ActionResult.PASS;
            }

            return ActionResult.success(world.isClient);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())) {
            return;
        }

        if (world.getBlockEntity(pos) instanceof PedestalBlockEntity blockEntity) {
            world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 1.1, pos.getZ() + 0.5, blockEntity.getStack()));
        }

        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient()) {
            return CheckTypeInvoker.invokeCheckType(type, BlockEntityRegistry.PEDESTAL, PedestalBlockEntity::clientTick);
        }
        return null;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(RITUAL, WATERLOGGED);
    }
}
