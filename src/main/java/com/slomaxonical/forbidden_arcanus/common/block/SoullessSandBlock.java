package com.slomaxonical.forbidden_arcanus.common.block;

import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.valhelsia.valhelsia_core.common.util.ItemStackUtils;

public class SoullessSandBlock extends SoulSandBlock {
    public SoullessSandBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.isOf(ItemRegistry.SOUL) && world.canPlayerModifyAt(player, pos)) {
            ItemStackUtils.shrinkStack(player, stack);

            world.setBlockState(pos, Blocks.SOUL_SAND.getDefaultState());
            world.syncWorldEvent(player, 2001, pos, Block.getRawIdFromState(state));
            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);

            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
