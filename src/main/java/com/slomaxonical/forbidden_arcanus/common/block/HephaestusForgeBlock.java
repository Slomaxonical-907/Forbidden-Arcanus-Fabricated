package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.common.block.util.FABlockProperties;
import com.slomaxonical.forbidden_arcanus.common.block.util.ModBlockPatterns;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.forge.HephaestusForgeBlockEntity;
import com.slomaxonical.forbidden_arcanus.common.item.util.RitualStarterItem;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import com.slomaxonical.forbidden_arcanus.mixin.CheckTypeInvoker;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.valhelsia.valhelsia_core.common.helper.VoxelShapeHelper;
import org.jetbrains.annotations.Nullable;

public class HephaestusForgeBlock extends Block implements Waterloggable, BlockEntityProvider {

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
        this.setDefaultState(this.getStateManager().getDefaultState().with(ACTIVATED, true).with(WATERLOGGED, false));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new HephaestusForgeBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(WATERLOGGED, context.getWorld().getFluidState(context.getBlockPos()).getFluid() == Fluids.WATER);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        this.updateState(state, world, pos);

        if (state.get(ACTIVATED)) {
            if (world.isClient()) {
                return ActionResult.SUCCESS;
            }
            if (world.getBlockEntity(pos) instanceof HephaestusForgeBlockEntity blockEntity) {
                ItemStack stack = player.getStackInHand(hand);

                if (stack.getItem() instanceof RitualStarterItem) {
                    blockEntity.getRitualManager().tryStartRitual((ServerWorld) world, stack, player);
                } else {
                    player.openHandledScreen(blockEntity);
                }
                return ActionResult.CONSUME;
            }
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    public void updateState(BlockState state, World world, BlockPos pos) {
        BlockPattern.Result patternHelper = ModBlockPatterns.BASE_HEPHAESTUS_PATTERN.searchAround(world, pos.down());

        if (patternHelper == null || patternHelper.getUp() != Direction.DOWN) {
            if (state.get(ACTIVATED)) {
                world.setBlockState(pos, state.with(ACTIVATED, false));
            }
        } else if (!state.get(ACTIVATED)) {
            world.setBlockState(pos, state.with(ACTIVATED, true));
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient()) {
            return CheckTypeInvoker.invokeCheckType(type, BlockEntityRegistry.HEPHAESTUS_FORGE, HephaestusForgeBlockEntity::clientTick);
        }
        return CheckTypeInvoker.invokeCheckType(type, BlockEntityRegistry.HEPHAESTUS_FORGE, HephaestusForgeBlockEntity::serverTick);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED,WATERLOGGED);
    }
}
