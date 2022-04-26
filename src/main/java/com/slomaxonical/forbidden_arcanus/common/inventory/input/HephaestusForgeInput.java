package com.slomaxonical.forbidden_arcanus.common.inventory.input;

import com.slomaxonical.forbidden_arcanus.common.blockEntity.forge.HephaestusForgeBlockEntity;
import com.slomaxonical.forbidden_arcanus.common.inventory.InputType;
import net.minecraft.item.ItemStack;

import java.util.Random;


public interface HephaestusForgeInput {

    boolean canInput(InputType inputType, ItemStack stack);
    int getInputValue(InputType inputType, ItemStack stack, Random random);
    void finishInput(InputType inputType, ItemStack stack, HephaestusForgeBlockEntity tileEntity, int slot, int inputValue);
}
