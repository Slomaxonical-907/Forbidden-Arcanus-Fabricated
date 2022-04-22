package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.core.helper.FAHelper;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.common.util.ItemStackUtils;

import javax.annotation.Nullable;

public class ArcaneBoneMealItem extends BoneMealItem {
    public ArcaneBoneMealItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockPos offsetPos = pos.offset(context.getSide());
        BlockState state = world.getBlockState(pos);
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();

        if (state.isOf(Blocks.FARMLAND)) {
            world.setBlockState(pos, BlockRegistry.MAGICAL_FARMLAND.getDefaultState().with(Properties.MOISTURE, state.get(Properties.MOISTURE)));
            world.syncWorldEvent(player, 2001, pos, Block.getRawIdFromState(state));

            ItemStackUtils.shrinkStack(player, stack);

            return ActionResult.success(world.isClient());
        } else if (ArcaneBoneMealItem.applyBoneMeal(stack, world, pos, player)) {
            if (!world.isClient()) {
                world.syncWorldEvent(2005, pos, 0);
            }

            return ActionResult.success(world.isClient());
        } else {
            boolean flag = state.isSideSolidFullSquare(world, pos, context.getSide());

            if (flag && useOnGround(stack, world, offsetPos, context.getSide())) {
                if (!world.isClient()) {
                    world.syncWorldEvent(2005, offsetPos, 0);
                }

                return ActionResult.success(world.isClient());
            } else {
                return ActionResult.PASS;
            }
        }
    }
    public static boolean applyBoneMeal(ItemStack stack, World level, BlockPos pos, @Nullable PlayerEntity player) {
//        int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player == null ? FakePlayerFactory.getMinecraft((ServerWorld) level) : player, level, pos, level.getBlockState(pos), stack);
//        if (hook != 0) {
//            return hook > 0;
//        }

        if (canGrow(level, pos)) {
            grow(level, pos);

            stack.decrement(1);

            return true;
        }

        return false;
    }

    private static boolean canGrow(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof Fertilizable) {
            return ((Fertilizable) state.getBlock()).isFertilizable(world, pos, state, world.isClient());
        }
        return false;
    }

    private static void grow(World world, BlockPos pos) {
        if (world.isClient()) {
            return;
        }
        for (int i = 0; i < 1000; i++) {
            if (canGrow(world, pos) && !world.isClient()) {
                ((Fertilizable) world.getBlockState(pos).getBlock()).grow((ServerWorld) world, world.random, pos, world.getBlockState(pos));
            } else {
                return;
            }
        }
    }
}
