package com.slomaxonical.forbidden_arcanus.common.enchantment;

import com.slomaxonical.forbidden_arcanus.common.item.EdelwoodBucketItem;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

public class EdelwoodBucketTarget extends EnchantmentTargetMixin {
    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof EdelwoodBucketItem;
    }
}
@Mixin(EnchantmentTarget.class)
abstract class EnchantmentTargetMixin{
    @Shadow
    abstract boolean isAcceptableItem(Item item);
}

