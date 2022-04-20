package com.slomaxonical.forbidden_arcanus.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

/**
 * Permafrost Enchantment <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.common.enchantment.PermafrostEnchantment
 *
 * @author stal111
 * @version 1.17.1 - 2.0.0
 * @since 2021-12-21
 */
public class PermafrostEnchantment extends Enchantment {

    public PermafrostEnchantment(Rarity rarity, EnchantmentTarget target, EquipmentSlot... equipmentSlots) {
        super(rarity, target, equipmentSlots);
    }

    @Override
    public int getMinPower(int level) {
        return 12 + (level - 1) * 20;
    }

    @Override
    public int getMaxPower(int level) {
        return 50;
    }

}
