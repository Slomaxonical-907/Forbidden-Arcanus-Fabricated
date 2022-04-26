package com.slomaxonical.forbidden_arcanus.common.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.function.Supplier;

public class UtremJarItem extends Item {
    public UtremJarItem(Supplier<Block> utremJar, Settings settings) {
        super(settings);
    }
}
