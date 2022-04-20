package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.core.helper.FAUtils;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SuspiciousStewItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

public class EdelwoodSuspiciousStewBucketItem extends SuspiciousStewItem {
    public EdelwoodSuspiciousStewBucketItem(Settings settings) {
        super(settings);
    }
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity livingEntity) {
        super.finishUsing(stack,world,livingEntity);
        return livingEntity instanceof PlayerEntity player && player.getAbilities().creativeMode ? stack : FAUtils.transferEnchantments(stack,new ItemStack(ItemRegistry.EDELWOOD_BUCKET));
    }
    //todo remainders
}
