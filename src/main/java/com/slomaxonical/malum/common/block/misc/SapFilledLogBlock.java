package com.slomaxonical.malum.common.block.misc;

import com.slomaxonical.malum.core.systems.block.SimpleBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.awt.*;
import java.util.function.Supplier;

public class SapFilledLogBlock extends Block {
    public final Block stripped;
    public final Item sap;
    public final Color sapColor;
    public SapFilledLogBlock(Settings settings, Block strippedRunewoodLog, Item sap, Color sapColor) {
        super(settings);
        this.stripped = strippedRunewoodLog;
        this.sap = sap;
        this.sapColor = sapColor;
    }
}
