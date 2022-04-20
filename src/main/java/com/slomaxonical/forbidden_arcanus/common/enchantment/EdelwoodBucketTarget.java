package com.slomaxonical.forbidden_arcanus.common.enchantment;

import com.slomaxonical.forbidden_arcanus.common.item.EdelwoodBucketItem;
import com.slomaxonical.forbidden_arcanus.mixin.EnchantmentTargetMixin;
import net.minecraft.item.Item;

public class EdelwoodBucketTarget extends EnchantmentTargetMixin {
    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof EdelwoodBucketItem;
    }
}

