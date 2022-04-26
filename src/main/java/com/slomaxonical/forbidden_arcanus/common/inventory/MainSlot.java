package com.slomaxonical.forbidden_arcanus.common.inventory;

import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;

public class MainSlot extends Slot {

    public MainSlot(Inventory inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
    }

    @Override
    public int getMaxItemCount() {
        return 1;
    }
}
