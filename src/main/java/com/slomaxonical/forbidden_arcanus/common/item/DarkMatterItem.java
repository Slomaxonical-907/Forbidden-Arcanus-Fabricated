package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.common.entity.item.DarkMatterItemEntity;
import com.slomaxonical.forbidden_arcanus.common.entity.item.FACustomEntityItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

public class DarkMatterItem extends Item implements FACustomEntityItem {
    public DarkMatterItem(Settings settings) {
        super(settings);
    }

    @Override
    public Entity replaceItemEntity(ServerWorld world, ItemEntity itemEntity, ItemStack itemStack) {
        final DarkMatterItemEntity dme = new DarkMatterItemEntity(world, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), itemStack);

        dme.setVelocity(itemEntity.getVelocity());
        dme.setPickupDelay(40);
        return dme;
    }
}
