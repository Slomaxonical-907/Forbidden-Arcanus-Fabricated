package com.slomaxonical.forbidden_arcanus.common.blockEntity;

import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class UtremJarBlockEntity extends BlockEntity {
    public UtremJarBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public UtremJarBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.UTREM_JAR, pos, state);
    }
}
