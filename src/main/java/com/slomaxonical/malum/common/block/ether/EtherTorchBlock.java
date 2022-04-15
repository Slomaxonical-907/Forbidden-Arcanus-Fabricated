package com.slomaxonical.malum.common.block.ether;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.util.shape.VoxelShape;

public class EtherTorchBlock extends EtherBlock {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);

    public EtherTorchBlock(Settings settings) {
        super(settings);
    }
}
