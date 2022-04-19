package com.slomaxonical.forbidden_arcanus.core.systems.multiblock;

import com.slomaxonical.forbidden_arcanus.core.systems.block.SimpleBlock;

public abstract class ComponentBlock extends SimpleBlock<MultiBlockComponentEntity> {
    public ComponentBlock(Settings properties) {
        super(properties);
//        setTile(BlockEntityRegistry.MULTIBLOCK_COMPONENT);
    }
}
