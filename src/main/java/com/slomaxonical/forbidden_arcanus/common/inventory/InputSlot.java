package com.slomaxonical.forbidden_arcanus.common.inventory;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

import javax.annotation.Nonnull;

public class InputSlot extends Slot {

    private final InputType inputType;

    public InputSlot(Inventory inventory, int index, int xPosition, int yPosition, InputType inputType) {
        super(inventory, index, xPosition, yPosition);
        this.inputType = inputType;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
//        return HephaestusForgeInputs.getInputs().stream().anyMatch(input -> input.canInput(inputType, stack));
        return true;
    }

    public InputType getInputType() {
        return inputType;
    }
}
