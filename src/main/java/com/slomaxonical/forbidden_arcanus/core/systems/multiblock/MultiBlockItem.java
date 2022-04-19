package com.slomaxonical.forbidden_arcanus.core.systems.multiblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;

import java.util.function.Supplier;

public class MultiBlockItem extends BlockItem {
    public final Supplier<MultiBlockStructure> structure;
    public MultiBlockItem(Block block, Settings settings, Supplier<MultiBlockStructure> structure) {
        super(block, settings);
        this.structure = structure;
    }

    @Override
    protected boolean canPlace(ItemPlacementContext context, BlockState state) {
        if (!structure.get().canPlace(context.getBlockPos(), context.getWorld()))
        {
            return false;
        }
        return super.canPlace(context, state);
    }

    @Override
    protected boolean place(ItemPlacementContext context, BlockState state) {
        structure.get().place(context.getBlockPos(), context.getWorld());
        return super.place(context, state);
    }
}
