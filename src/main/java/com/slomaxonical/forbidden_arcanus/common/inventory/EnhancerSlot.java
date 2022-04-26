package com.slomaxonical.forbidden_arcanus.common.inventory;

import com.slomaxonical.forbidden_arcanus.common.blockEntity.forge.HephaestusForgeLevel;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class EnhancerSlot extends Slot {

    private boolean unlocked = true;
    private final HephaestusForgeLevel requiredLevel;

    public EnhancerSlot(Inventory inventory, int index, int xPosition, int yPosition, HephaestusForgeLevel requiredLevel) {
        super(inventory, index, xPosition, yPosition);
        this.requiredLevel = requiredLevel;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return this.unlocked;
    }

    @Override
    public boolean isEnabled() {
        return this.unlocked;
    }

    @Override
    public int getMaxItemCount() {
        return 1;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public HephaestusForgeLevel getRequiredLevel() {
        return requiredLevel;
    }
}
