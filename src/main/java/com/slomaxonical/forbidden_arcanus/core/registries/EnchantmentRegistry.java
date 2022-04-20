package com.slomaxonical.forbidden_arcanus.core.registries;

import com.chocohead.mm.api.ClassTinkerers;
import com.slomaxonical.forbidden_arcanus.common.enchantment.FAEnchantmentTargets;
import com.slomaxonical.forbidden_arcanus.common.enchantment.PermafrostEnchantment;
import com.slomaxonical.forbidden_arcanus.common.item.EdelwoodBucketItem;
import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.registry.Registry;

public class EnchantmentRegistry implements AutoRegistryContainer<Enchantment> {
    public static final Enchantment PERMAFROST = new PermafrostEnchantment(Enchantment.Rarity.UNCOMMON, ClassTinkerers.getEnum(EnchantmentTarget.class, "EDELWOOD_BUCKET") , EquipmentSlot.MAINHAND);


    @Override
    public Registry getRegistry() {
        return Registry.ENCHANTMENT;
    }

    @Override
    public Class getTargetFieldType() {
        return Enchantment.class;
    }
}
