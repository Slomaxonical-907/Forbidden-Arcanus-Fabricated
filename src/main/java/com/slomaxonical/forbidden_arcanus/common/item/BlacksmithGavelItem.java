package com.slomaxonical.forbidden_arcanus.common.item;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class BlacksmithGavelItem extends PickaxeItem {
    private final int ritualUses;

    public BlacksmithGavelItem(ToolMaterial material, int attackDamage, float attackSpeed, int ritualUses, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
        this.ritualUses = ritualUses;
    }
}
