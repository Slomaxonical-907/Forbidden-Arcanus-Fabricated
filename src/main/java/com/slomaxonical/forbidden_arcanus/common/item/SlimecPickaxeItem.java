package com.slomaxonical.forbidden_arcanus.common.item;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.collection.DefaultedList;

public class SlimecPickaxeItem extends PickaxeItem {
    public SlimecPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material,attackDamage,attackSpeed,settings);
    }
    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> items) {
        if (this.isIn(group)) {
            ItemStack stack = new ItemStack(this);
            stack.addEnchantment(Enchantments.SILK_TOUCH, 1);
            items.add(stack);
        }
    }
}
