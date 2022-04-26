package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.common.block.util.FABlockProperties;
import com.slomaxonical.forbidden_arcanus.core.helper.FATags;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
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
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;
import net.valhelsia.valhelsia_core.common.helper.VoxelShapeHelper;
import net.valhelsia.valhelsia_core.common.util.ItemStackUtils;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Random;

public class EdelwoodLogBlock extends Block implements Waterloggable {

    private static final float RAIN_FILL_CHANCE = 0.15F;
    private static final float OILY_CHANCE = 0.025F;

    public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;
    public static final BooleanProperty OILY = FABlockProperties.OILY;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    protected static final EnumMap<Direction.Axis, VoxelShape> SHAPES = VoxelShapeHelper.rotateAxis(
            VoxelShapes.combine(VoxelShapes.fullCube(), Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D), BooleanBiFunction.ONLY_FIRST)
    );

    public EdelwoodLogBlock(Settings settings) {
        super(settings);
        if (this.getStateManager().getProperties().contains(AXIS)) {
            this.setDefaultState(this.getStateManager().getDefaultState().with(AXIS, Direction.Axis.Y).with(OILY, false).with(WATERLOGGED, false));
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return context.isHolding(BlockRegistry.EDELWOOD_LOG.asItem()) || context.isHolding(BlockRegistry.CARVED_EDELWOOD_LOG.asItem()) ? VoxelShapes.fullCube() : SHAPES.get(state.get(AXIS));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        boolean flag = context.getWorld().getFluidState(context.getBlockPos()).getFluid() == Fluids.WATER;
        return this.getDefaultState().with(AXIS, context.getSide().getAxis()).with(WATERLOGGED, flag);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return state;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextDouble() < OILY_CHANCE && world.isRegionLoaded(pos, pos.add(4,4,4))) {
            world.setBlockState(pos, state.with(OILY, true), 2);
        }
        super.randomTick(state, world, pos, random);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return !state.get(OILY);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(Items.GLASS_BOTTLE) && state.get(OILY)) {
            ItemStack oil = new ItemStack(ItemRegistry.EDELWOOD_OIL);

            ItemStackUtils.shrinkStack(player, stack);

            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.emitGameEvent(player, GameEvent.FLUID_PICKUP, pos);

            if (stack.isEmpty()) {
                player.setStackInHand(hand, oil);
            } else if (!player.getInventory().insertStack(oil)) {
                player.dropItem(oil, false);
            }

            world.setBlockState(pos, state.with(OILY, false));

            return ActionResult.success(world.isClient());
        } else if (stack.getItem() instanceof AxeItem && !this.isCarved() && state.get(AXIS) == Direction.Axis.Y) {
            Direction direction = hit.getSide().getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : hit.getSide();

            stack.damage(1, player, (p) -> p.sendToolBreakStatus(hand));

            if (!world.isClient()) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity) player, pos, stack);
            }
            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.setBlockState(pos, BlockRegistry.CARVED_EDELWOOD_LOG.getDefaultState().with(Properties.HORIZONTAL_FACING, direction).with(OILY, state.get(OILY)).with(WATERLOGGED, state.get(WATERLOGGED)));

            return ActionResult.success(world.isClient());
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    protected boolean shouldHandlePrecipitation(BlockState state, World world, Biome.Precipitation precipitation) {
        if (!state.get(WATERLOGGED) && (!state.contains(AXIS) || state.get(AXIS) == Direction.Axis.Y) && precipitation == Biome.Precipitation.RAIN) {
            return world.getRandom().nextFloat() < RAIN_FILL_CHANCE;
        }
        return false;
    }

    @Override
    public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
        if (shouldHandlePrecipitation(state, world, precipitation)) {
            int i = 0;
            while (world.getBlockState(pos.down(i + 1)).isIn(FATags.Blocks.EDELWOOD_LOGS) && world.getBlockState(pos.down(i + 1)).get(AXIS) == Direction.Axis.Y && !world.getBlockState(pos.down(i + 1)).get(WATERLOGGED)) {
                i++;
            }
            world.setBlockState(pos.down(i), world.getBlockState(pos.down(i)).with(WATERLOGGED, true));
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos.down(i));
        }
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return switch (rotation) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> switch (state.get(AXIS)) {
                case X -> state.with(AXIS, Direction.Axis.Z);
                case Z -> state.with(AXIS, Direction.Axis.X);
                default -> state;
            };
            default -> state;
        };
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS, OILY, WATERLOGGED);
    }

    protected boolean isCarved() {
        return false;
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
