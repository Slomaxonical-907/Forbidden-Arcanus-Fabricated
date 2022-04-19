package com.slomaxonical.forbidden_arcanus.common.blockEntity;

import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class ObsidianSkullBlockEntity extends BlockEntity {
    public ObsidianSkullBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public ObsidianSkullBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.OBSIDIAN_SKULL, pos, state);
    }
}
