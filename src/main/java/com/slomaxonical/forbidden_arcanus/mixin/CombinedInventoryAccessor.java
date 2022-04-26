package com.slomaxonical.forbidden_arcanus.mixin;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(PlayerInventory.class)
public interface CombinedInventoryAccessor {
    @Accessor
    List<DefaultedList<ItemStack>> getCombinedInventory();
}
