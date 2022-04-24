package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.core.config.ItemConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class EternalStellaItem extends Item {
    public EternalStellaItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stackInSlot = player.getInventory().getStack(i);

            if (stackInSlot.isEmpty() || stackInSlot.getDamage() == 0) {
                continue;
            }

            NbtCompound compound = stackInSlot.getOrCreateNbt();
            compound.putBoolean("Repair", true);
        }

        if (!player.getAbilities().creativeMode) {
            this.setRemainingUses(stack, this.getRemainingUses(stack) - 1);
        }

        if (this.getRemainingUses(stack) == 0) {
            stack.decrement(1);
        }

        return TypedActionResult.success(stack, world.isClient());
    }
    private int getRemainingUses(ItemStack stack) {
        NbtCompound tag = stack.getOrCreateNbt();

        if (!tag.contains("RemainingUses")) {
            return ItemConfig.ETERNAL_STELLA_USES.get();
        } else {
            return tag.getInt("RemainingUses");
        }
    }

    private void setRemainingUses(ItemStack stack, int uses) {
        stack.getOrCreateNbt().putInt("RemainingUses", uses);
    }
}
