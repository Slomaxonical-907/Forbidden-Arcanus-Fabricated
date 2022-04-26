package com.slomaxonical.forbidden_arcanus.common.inventory.input;

import com.slomaxonical.forbidden_arcanus.common.blockEntity.forge.HephaestusForgeBlockEntity;
import com.slomaxonical.forbidden_arcanus.common.inventory.InputType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.valhelsia.valhelsia_core.common.util.ItemStackUtils;

import java.util.Map;
import java.util.Random;

/**
 * Enchantment Input
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.common.inventory.input.EnchantmentInput
 *
 * @author stal111
 * @version 2.0.0
 * @since 2021-07-07
 */
public class EnchantmentInput implements HephaestusForgeInput {

    @Override
    public boolean canInput(InputType inputType, ItemStack stack) {
        return inputType == InputType.EXPERIENCE && stack.hasEnchantments();
    }

    @Override
    public int getInputValue(InputType inputType, ItemStack stack, Random random) {
        int xp = this.getEnchantmentXp(stack);

        if (xp <= 0) {
            return 0;
        }

        int i = (int) Math.ceil((double) xp / 2.0D);
        return i + random.nextInt(i);
    }

    @Override
    public void finishInput(InputType inputType, ItemStack stack, HephaestusForgeBlockEntity tileEntity, int slot, int inputValue) {
        if (inputValue != 0) {
            tileEntity.setStack(slot, ItemStackUtils.removeEnchantments(stack));
        }
    }

    private int getEnchantmentXp(ItemStack stack) {
        int xp = 0;
        Map<Enchantment, Integer> map = EnchantmentHelper.get(stack);

        for(Map.Entry<Enchantment, Integer> entry : map.entrySet()) {
            Enchantment enchantment = entry.getKey();
            Integer integer = entry.getValue();
            if (!enchantment.isCursed()) {
                xp += enchantment.getMinPower(integer);
            }
        }

        return xp;
    }
}
