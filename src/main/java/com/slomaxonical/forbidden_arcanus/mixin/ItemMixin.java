package com.slomaxonical.forbidden_arcanus.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

/**
 * Item Mixin <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.mixin.ItemMixin
 *
 * @author stal111
 * @version 2.0.0
 * @since 2021-08-04
 */
@Mixin(Item.class)
public class ItemMixin {

    @Inject(at = @At(value = "RETURN"), method = "inventoryTick")
    private void forbiddenArcanus_inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        if (stack.getDamage() == 0) {
            return;
        }

        NbtCompound compound = Objects.requireNonNull(stack.getNbt());

        if (compound.getBoolean("Repair")) {
            stack.setDamage(stack.getDamage() - 1);

            if (stack.getDamage() == 0) {
                compound.remove("Repair");
            }
        }
    }
}
