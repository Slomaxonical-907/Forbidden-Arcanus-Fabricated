package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.core.helper.FAHelper;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SuspiciousStewItem;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.common.util.ItemStackUtils;

public class EdelwoodSuspiciousStewBucketItem extends SuspiciousStewItem {
    public EdelwoodSuspiciousStewBucketItem(Settings settings) {
        super(settings);
    }
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity livingEntity) {
        super.finishUsing(stack,world,livingEntity);
        return livingEntity instanceof PlayerEntity player && player.getAbilities().creativeMode ? stack : ItemStackUtils.transferEnchantments(stack,new ItemStack(ItemRegistry.EDELWOOD_BUCKET));
    }
    //todo remainders
}
