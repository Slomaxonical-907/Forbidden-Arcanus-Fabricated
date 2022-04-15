package com.slomaxonical.malum.common.block.misc;

import com.slomaxonical.malum.core.systems.block.SimpleBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FacingBlock;

public class MalumDirectionalBlock extends FacingBlock {
    public MalumDirectionalBlock(FabricBlockSettings blockSettings) {
        super(blockSettings);
    }
}
