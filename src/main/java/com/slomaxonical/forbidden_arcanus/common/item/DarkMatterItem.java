package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.common.entity.DarkMatterItemEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

public class DarkMatterItem extends Item {
    public DarkMatterItem(Settings settings) {
        super(settings);
    }

    public Entity replaceItemEntity(ServerWorld world, ItemEntity itemEntity, ItemStack itemStack) {
        final DarkMatterItemEntity egc = new DarkMatterItemEntity(world, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), itemStack);

        egc.setVelocity(itemEntity.getVelocity());
        egc.setPickupDelay(40);
        return egc;
    }
}
