package com.slomaxonical.malum.common.block.item_storage;

import com.slomaxonical.malum.core.systems.block.SimpleBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.util.shape.VoxelShape;

import java.util.stream.Stream;

public class WoodItemPedestalBlock extends ItemPedestalBlock {
//    public static final VoxelShape SHAPE = Stream.of(
//            Block.createCuboidShape(4, 0, 4, 12, 3, 12),
//            Block.createCuboidShape(5, 3, 5, 11, 11, 11),
//            Block.createCuboidShape(4, 11, 4, 12, 13, 12)
//    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public WoodItemPedestalBlock(Settings settings) {
        super(settings);
    }
}
