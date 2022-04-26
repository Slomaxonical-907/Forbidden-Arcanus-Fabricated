package com.slomaxonical.forbidden_arcanus.common.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

public interface FACustomEntityItem {
    Entity replaceItemEntity(ServerWorld world, ItemEntity itemEntity, ItemStack itemStack);
}
