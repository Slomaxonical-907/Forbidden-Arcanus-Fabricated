package com.slomaxonical.forbidden_arcanus.common.blockEntity;

import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class ArcaneCrystalObeliskBlockEntity extends BlockEntity {
    public ArcaneCrystalObeliskBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public ArcaneCrystalObeliskBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.ARCANE_CRYSTAL_OBELISK, pos, state);
    }
}
