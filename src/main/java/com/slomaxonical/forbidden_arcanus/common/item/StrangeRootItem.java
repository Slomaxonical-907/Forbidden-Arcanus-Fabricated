package com.slomaxonical.forbidden_arcanus.common.item;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class StrangeRootItem extends Item {
    public StrangeRootItem(Block block, Settings settings) {
        super(settings);
    }
    @Nonnull
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity entity) {
        super.finishUsing(stack, world, entity);

        if (!world.isClient()) entity.getStatusEffects().stream()
                .filter(mobEffectInstance -> mobEffectInstance.getEffectType().getCategory() == StatusEffectCategory.HARMFUL)
                .forEach(mobEffectInstance -> entity.removeStatusEffect(mobEffectInstance.getEffectType()));

        return stack;
    }
}
