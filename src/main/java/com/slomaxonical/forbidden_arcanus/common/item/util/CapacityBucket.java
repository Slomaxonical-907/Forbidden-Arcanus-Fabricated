package com.slomaxonical.forbidden_arcanus.common.item.util;

import com.mojang.datafixers.util.Pair;
import com.slomaxonical.forbidden_arcanus.core.helper.FAHelper;
import net.minecraft.item.ItemStack;
import net.valhelsia.valhelsia_core.common.util.ItemStackUtils;

import java.util.Objects;

/**
 * Capacity Bucket <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.common.item.CapacityBucket
 *
 * @author stal111
 * @version 2.0.0
 * @since 2021-12-03
 */
public interface CapacityBucket {
    int getCapacity();
    ItemStack getEmptyBucket();

    default int getFullness(ItemStack stack) {
        if (stack.hasNbt() && Objects.requireNonNull(stack.getNbt()).contains("Fullness")) {
            return stack.getOrCreateNbt().getInt("Fullness");
        }
        if (!this.isValidBucket(stack)) {
            return 0;
        }
        if (stack.getOrCreateNbt().getInt("Fullness") == 0) {
            this.setFullness(stack, 1);
        }
        return stack.getOrCreateNbt().getInt("Fullness");
    }

    default ItemStack setFullness(ItemStack stack, int fullness) {
        if (this.isValidBucket(stack)) {
            stack.getOrCreateNbt().putInt("Fullness", fullness);
        }

        return stack;
    }

    default boolean isFull(ItemStack stack) {
        return this.getFullness(stack) >= this.getCapacity();
    }

    default Pair<Boolean, ItemStack> tryFill(ItemStack stack) {
        if (this.isFull(stack)) {
            return Pair.of(false, stack);
        }
        this.setFullness(stack, this.getFullness(stack) + 1);

        return Pair.of(true, stack);
    }

    default ItemStack tryDrain(ItemStack stack) {
        if (this.getFullness(stack) - 1 <= 0) {
            return ItemStackUtils.transferEnchantments(stack, this.getEmptyBucket());
        }
        return this.setFullness(stack, this.getFullness(stack) - 1);
    }

    private boolean isValidBucket(ItemStack stack) {
        return stack.getItem() instanceof CapacityBucket && this.getCapacity() != 0;
    }

    default ItemStack transferFullness(ItemStack oldStack, ItemStack newStack) {
        newStack.getOrCreateNbt().putInt("Fullness", this.getFullness(oldStack));
        return newStack;
    }
}
