package com.slomaxonical.forbidden_arcanus.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.function.Supplier;

public class PixieUtremJarBlock extends Block {
    public PixieUtremJarBlock(Supplier<Item> pixie, Settings settings) {
        super(settings);
    }
}
