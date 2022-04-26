package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.common.block.util.FABlockProperties;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.valhelsia.valhelsia_core.common.util.ItemStackUtils;
import org.jetbrains.annotations.Nullable;

public class CarvedEdelwoodLogBlock extends EdelwoodLogBlock {
    public static final BooleanProperty LEAVES = FABlockProperties.LEAVES;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public CarvedEdelwoodLogBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(LEAVES,false).with(OILY, false).with(WATERLOGGED,false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return context.isHolding(BlockRegistry.EDELWOOD_LOG.asItem()) || context.isHolding(BlockRegistry.CARVED_EDELWOOD_LOG.asItem()) ? VoxelShapes.fullCube() : SHAPES.get(Direction.Axis.Y);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext context) {
        boolean flag = context.getWorld().getFluidState(context.getBlockPos()).getFluid() == Fluids.WATER;
        return this.getDefaultState().with(FACING, context.getPlayerFacing().getOpposite()).with(WATERLOGGED, flag);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
            ItemStack stack = player.getStackInHand(hand);

            if (stack.getItem() instanceof AxeItem && state.get(LEAVES)) {
                stack.damage(1, player, (p) -> p.sendToolBreakStatus(hand));

                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(player, GameEvent.SHEAR, pos);

                world.setBlockState(pos, state.with(LEAVES, false));

                return ActionResult.success(world.isClient());
            } else if (stack.getItem() instanceof BoneMealItem && !state.get(LEAVES)) {
                ItemStackUtils.shrinkStack(player, stack);

                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);

                world.setBlockState(pos, state.with(LEAVES, true));

                return ActionResult.success(world.isClient());
            }

            return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, OILY, LEAVES, WATERLOGGED);
    }

    @Override
    protected boolean isCarved() {
        return true;
    }
}
