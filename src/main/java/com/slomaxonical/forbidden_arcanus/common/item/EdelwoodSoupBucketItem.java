package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class EdelwoodSoupBucketItem extends Item implements CapacityBucket {
    private final Item soup;

    public EdelwoodSoupBucketItem(Item soup, Settings properties) {
        super(properties);
        this.soup = soup;
    }
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity livingEntity) {
        if (!world.isClient) {
            livingEntity.clearStatusEffects();
        }
        if (livingEntity instanceof ServerPlayerEntity serverPlayer) {
            Criteria.CONSUME_ITEM.trigger(serverPlayer, stack);
            serverPlayer.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        super.finishUsing(stack.copy(),world,livingEntity);

        return this.tryDrain(stack);
    }
    //todo: change to configs
    @Override
    public int getCapacity() {
        return 8;
    }

    @Override
    public ItemStack getEmptyBucket() {
        return new ItemStack(ItemRegistry.EDELWOOD_BUCKET);
    }
    public Item getSoup() {
        return soup;
    }
    //todo:also remainders

}
