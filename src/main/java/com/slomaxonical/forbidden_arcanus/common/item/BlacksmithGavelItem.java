package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.common.item.util.RitualStarterItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;

public class BlacksmithGavelItem extends PickaxeItem implements RitualStarterItem {
    private final int ritualUses;

    public BlacksmithGavelItem(ToolMaterial material, int attackDamage, float attackSpeed, int ritualUses, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
        this.ritualUses = ritualUses;
    }

    @Override
    public int getRitualUses() {
        return this.ritualUses;
    }

    @Override
    public int getRemainingUses(ItemStack stack) {
        NbtCompound tag = stack.getOrCreateNbt();

        if (tag.contains("RemainingRitualUses")) {
            return tag.getInt("RemainingRitualUses");
        }

        return this.getRitualUses();
    }

    @Override
    public void setRemainingUses(ItemStack stack, int remainingUses) {
        stack.getOrCreateNbt().putInt("RemainingRitualUses", remainingUses);
    }
}
