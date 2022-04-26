package com.slomaxonical.forbidden_arcanus.common.item;

import com.google.common.collect.ImmutableList;
import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import com.slomaxonical.forbidden_arcanus.mixin.CombinedInventoryAccessor;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.world.World;
import net.minecraft.world.gen.BlockSource;
import net.valhelsia.valhelsia_core.common.capability.counter.CounterCapability;
import net.valhelsia.valhelsia_core.common.capability.counter.CounterImpl;
import net.valhelsia.valhelsia_core.common.capability.counter.SimpleCounter;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Supplier;

public class ObsidianSkullItem extends WallStandingBlockItem {
//WallStandingBlockItem
    public static final int OBSIDIAN_SKULL_PROTECTION_TIME = 600;

    private static final Identifier COUNTER = new Identifier(ForbiddenArcanus.MOD_ID, "tick_counter");

    public static final List<DamageSource> DAMAGE_SOURCES = ImmutableList.of(DamageSource.LAVA, DamageSource.IN_FIRE, DamageSource.ON_FIRE, DamageSource.HOT_FLOOR);

    private final boolean eternal;

    public ObsidianSkullItem(Supplier<Block> floorBlock, Supplier<Block> wallBlock, boolean eternal, Settings settings) {
        super(floorBlock.get(),wallBlock.get(),settings);
        this.eternal = eternal;
    }

//    public static DispenserBehavior getDispenseBehavior() {
//        return new FallibleItemDispenserBehavior() {
//            @Override
//            protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
//                this.setSuccess(ArmorItem.dispenseArmor(pointer, stack));
//                return stack;
//            }
//        };
//    }
////    @Override
////    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
////        return EquipmentSlot.HEAD;
////    }
//
//    @Override
//    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
//        if (entity instanceof LivingEntity livingEntity && !this.eternal) {
//            stack.getCapability(CounterProvider.CAPABILITY).ifPresent(counterCapability -> {
//                NbtCompound tag = new NbtCompound();
//
//                if (livingEntity.getRecentDamageSource() != null) {
//                    tag.putString("DamageSource", livingEntity.getRecentDamageSource().getName());
//                }
//
//                this.getCounter(counterCapability).tick(tag);
//            });
//        }
//        super.inventoryTick(stack, world, entity, slot, selected);
//    }
//
//    public static boolean shouldProtectFromDamage(DamageSource damageSource, PlayerInventory inventory) {
//        if (!DAMAGE_SOURCES.contains(damageSource)) {
//            return false;
//        }
//
//        if (inventory.contains(new ItemStack(ItemRegistry.ETERNAL_OBSIDIAN_SKULL))) {
//            return true;
//        }
//
//        ItemStack stack = getSkullWithLowestCounter(inventory);
//
//        if (stack.isEmpty()) {
//            return false;
//        }
//
//        return getCounterValue(stack) < OBSIDIAN_SKULL_PROTECTION_TIME;
//    }
//    public static ItemStack getSkullWithLowestCounter(PlayerInventory inventory) {
//        ItemStack skull = ItemStack.EMPTY;
//
//        for (DefaultedList<ItemStack> defaultedList : ((CombinedInventoryAccessor) inventory).getCombinedInventory()) {
//            for (ItemStack stack : defaultedList) {
//                if (!stack.isEmpty() && stack.isOf(ItemRegistry.OBSIDIAN_SKULL)) {
//                    if (skull.isEmpty() || getCounterValue(skull) > getCounterValue(stack)) {
//                        skull = stack;
//                    }
//                }
//            }
//        }
//
//        return skull;
//    }
//
//    public static int getCounterValue(ItemStack stack) {
//        return stack.getCapability(CounterProvider.CAPABILITY).orElse(new CounterImpl()).getCounter(COUNTER).getValue();
//    }
//
//    @Override
//    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
//        super.appendTooltip(stack, world, tooltip, context);
//        tooltip.add(new TranslatableText("tooltip." + ForbiddenArcanus.MOD_ID + (eternal ? ".eternal_" : ".") + "obsidian_skull").formatted(Formatting.GRAY));
//    }
//
//    private SimpleCounter getCounter(CounterCapability counterCapability) {
//        return counterCapability.getCounter(COUNTER);
//    }
}
