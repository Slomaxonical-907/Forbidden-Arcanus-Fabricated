package com.slomaxonical.malum.common.block.misc;

import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;

public class MalumSaplingBlock extends SaplingBlock {
    private final SaplingGenerator generator;
    public MalumSaplingBlock(SaplingGenerator generator, Settings settings) {
        super(generator,settings);
        this.generator = generator;
        this.setDefaultState((this.stateManager.getDefaultState()).with(STAGE, 0));
    }
}
