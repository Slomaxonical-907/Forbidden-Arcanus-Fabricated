package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.common.entity.item.DarkMatterItemEntity;
import com.slomaxonical.forbidden_arcanus.common.entity.item.FACustomEntityItem;
import com.slomaxonical.forbidden_arcanus.common.entity.item.SoapItemEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

public class PurifyingSoapItem extends Item implements FACustomEntityItem {
    public PurifyingSoapItem(Settings settings) {
        super(settings);
    }

    @Override
    public Entity replaceItemEntity(ServerWorld world, ItemEntity itemEntity, ItemStack itemStack) {
        final SoapItemEntity egc = new SoapItemEntity(world, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), itemStack);

        egc.setVelocity(itemEntity.getVelocity());
        egc.setPickupDelay(40);
        return egc;
    }
}
