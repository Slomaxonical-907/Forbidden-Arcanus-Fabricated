package com.slomaxonical.forbidden_arcanus.common.blockEntity;

import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NipaBlockEntity extends BlockEntity {
    private int lastSpeck = 0;
    private int speckHeight = 10;

    private Map<UUID, Integer> players = new HashMap<>();

    private int cachedPower = 0;

    public NipaBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public NipaBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.NIPA, pos, state);
    }

    public double getSpeckHeight() {
        return speckHeight / 100.0F;
    }

    public void setSpeckHeight(int speckHeight) {
        this.speckHeight = speckHeight;
    }

    public int getCachedPower() {
        return cachedPower;
    }

    public void setCachedPower(int cachedPower) {
        this.cachedPower = cachedPower;
    }
}
