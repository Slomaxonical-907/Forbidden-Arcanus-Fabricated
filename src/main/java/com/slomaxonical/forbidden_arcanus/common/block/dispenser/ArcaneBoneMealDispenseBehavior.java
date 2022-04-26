package com.slomaxonical.forbidden_arcanus.common.block.dispenser;

import com.slomaxonical.forbidden_arcanus.common.item.ArcaneBoneMealItem;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ArcaneBoneMealDispenseBehavior extends FallibleItemDispenserBehavior {
    @Nonnull
    @Override
    protected ItemStack dispenseSilently(BlockPointer source, ItemStack stack) {
        World world = source.getWorld();
        BlockPos pos = source.getPos().offset(source.getBlockState().get(DispenserBlock.FACING));
        BlockState state = world.getBlockState(pos);

        this.setSuccess(true);

        if (state.isOf(Blocks.FARMLAND)) {
            stack.decrement(1);

            world.setBlockState(pos, BlockRegistry.MAGICAL_FARMLAND.getDefaultState().with(Properties.MOISTURE, state.get(Properties.MOISTURE)));
            world.syncWorldEvent(null, 2001, pos, Block.getRawIdFromState(state));
        } else if (ArcaneBoneMealItem.applyBoneMeal(stack, world, pos, null)) {
            if (!world.isClient()) {
                world.syncWorldEvent(2005, pos, 0);
            }
        } else {
            this.setSuccess(false);
        }

        return stack;
    }
}
