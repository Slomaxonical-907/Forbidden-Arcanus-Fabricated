package com.slomaxonical.forbidden_arcanus.common.block.dispenser;

import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class SoulDispenseBehavior extends FallibleItemDispenserBehavior {
    @Nonnull
    @Override
    protected ItemStack dispenseSilently(BlockPointer source, ItemStack stack) {
        World world = source.getWorld();
        BlockPos pos = source.getPos().offset(source.getBlockState().get(DispenserBlock.FACING));
        BlockState state = world.getBlockState(pos);

        if (state.isOf(BlockRegistry.SOULLESS_SAND)) {
            stack.decrement(1);

            world.setBlockState(pos, Blocks.SOUL_SAND.getDefaultState());
            world.syncWorldEvent(null, 2001, pos, Block.getRawIdFromState(state));

            return stack;
        }
        return super.dispenseSilently(source, stack);
    }
}
