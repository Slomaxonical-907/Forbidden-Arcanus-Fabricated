package com.slomaxonical.forbidden_arcanus.common.inventory;

import com.slomaxonical.forbidden_arcanus.common.blockEntity.forge.HephaestusForgeLevel;
import com.slomaxonical.forbidden_arcanus.core.registries.ScreenHandlerTypeRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

import javax.annotation.Nonnull;

/**
 * Hephaestus Forge Menu <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.common.inventory.HephaestusForgeMenu
 *
 * @author stal111
 * @version 1.17.1 - 2.0.0
 * @since 2021-06-28
 */
public class HephaestusForgeMenu extends ScreenHandler {

    private final PropertyDelegate hephaestusForgeData;
    private final Inventory inventory;

    public HephaestusForgeMenu(int id, Inventory player) {
        this(id, new SimpleInventory(9), new ArrayPropertyDelegate(6), player);
    }

    public HephaestusForgeMenu(int id, Inventory inventory, PropertyDelegate delegate, Inventory player) {
        super(ScreenHandlerTypeRegistry.HEPHAESTUS_FORGE, id);
        this.inventory = inventory;
        this.hephaestusForgeData = delegate;

        checkDataCount(this.hephaestusForgeData, 5);
        this.addProperties(this.hephaestusForgeData);

        int x = 26;

        // Hephaestus Forge Slots
        this.addSlot(new EnhancerSlot(inventory, 0, 32 + x, 24, HephaestusForgeLevel.getRequiredLevelForSlot(1)));
        this.addSlot(new EnhancerSlot(inventory, 1, 32 + x, 46, HephaestusForgeLevel.getRequiredLevelForSlot(2)));
        this.addSlot(new EnhancerSlot(inventory, 2, 128 + x, 24, HephaestusForgeLevel.getRequiredLevelForSlot(3)));
        this.addSlot(new EnhancerSlot(inventory, 3, 128 + x, 46, HephaestusForgeLevel.getRequiredLevelForSlot(4)));

        HephaestusForgeLevel level = HephaestusForgeLevel.getFromIndex(this.hephaestusForgeData.get(0));

        if (level.getEnhancerSlots() < 4) {
            ((EnhancerSlot) this.getSlot(3)).setUnlocked(false);

            if (level.getEnhancerSlots() < 3) {
                ((EnhancerSlot) this.getSlot(2)).setUnlocked(false);

                if (level.getEnhancerSlots() < 2) {
                    ((EnhancerSlot) this.getSlot(1)).setUnlocked(false);
                }
            }
        }

        // Main Slot
        this.addSlot(new MainSlot(inventory, 4, 80 + x, 24));

        // Input Slots
        this.addSlot(new InputSlot(inventory, 5, 8, 25, InputType.AUREAL));
        this.addSlot(new InputSlot(inventory, 6, 8, 43, InputType.SOULS));
        this.addSlot(new InputSlot(inventory, 7, x + 176 + 2, 25, InputType.BLOOD));
        this.addSlot(new InputSlot(inventory, 8, x + 176 + 2, 43, InputType.EXPERIENCE));

        // Inventory Slots
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(player, j + i * 9 + 9, 8 + j * 18 + x, 84 + i * 18));
            }
        }

        // Hotbar Slots
        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(player, k, 8 + k * 18 + x, 142));
        }
    }

    @Nonnull
    @Override
    public ItemStack transferSlot(@Nonnull PlayerEntity player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasStack()) {
            ItemStack stack = slot.getStack();
            result = stack.copy();

            if (index <= 4) {
                if (!this.insertItem(stack, 5, 41, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickTransfer(stack, result);
            } else {
                if (!this.slots.get(4).hasStack()) {
                    if (!this.insertItem(stack, 4, 5, false)) {
                        return ItemStack.EMPTY;
                    }

                    slot.onQuickTransfer(stack, result);
                } else {
                    if (!this.insertItem(stack, 0, 4, false)) {
                        if (index < 36) {
                            if (!this.insertItem(stack, 36, 45, false)) {
                                return ItemStack.EMPTY;
                            }
                        } else if (this.insertItem(stack, 5, 36, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                }
            }
        }

        return result;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
    public PropertyDelegate getHephaestusForgeData() {
        return hephaestusForgeData;
    }
}
