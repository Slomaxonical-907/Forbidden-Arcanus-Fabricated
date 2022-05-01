package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import com.slomaxonical.forbidden_arcanus.mixin.CombinedInventoryAccessor;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.common.capability.counter.CounterCapability;
import net.valhelsia.valhelsia_core.common.capability.counter.CounterCreator;
import net.valhelsia.valhelsia_core.common.capability.counter.CounterImpl;
import net.valhelsia.valhelsia_core.common.capability.counter.SimpleCounter;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Consumer;

public class ObsidianSkullShieldItem extends Item {
    private static final SimpleCounter COUNTER = new SimpleCounter(new Identifier(ForbiddenArcanus.MOD_ID, "tick_counter"));
    private static final int USE_DURATION = 72000;

    public ObsidianSkullShieldItem(Settings settings) {
        super(settings);
    }

//    @Override
//    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
//        return ToolActions.DEFAULT_SHIELD_ACTIONS.contains(toolAction);
//    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return USE_DURATION;
    }

//    @Override
//    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
//        player.setCurrentHand(hand);
//
//        return TypedActionResult.consume(player.getStackInHand(hand));
//    }
//
//    @Override
//    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
//        if (entity instanceof LivingEntity livingEntity) {
//            stack.getCapability(CounterProvider.CAPABILITY).ifPresent(counterCapability -> {
//                NbtCompound tag = new NbtCompound();
//
//                if (livingEntity.getRecentDamageSource() != null) {
//                    tag.putString("DamageSource", livingEntity.getRecentDamageSource().getMsgId());
//                }
//
//                this.getCounter(counterCapability).tick(tag);
//            });
//        }
//        super.inventoryTick(stack, world, entity, slot, selected);
//    }
//    public static boolean shouldProtectFromDamage(DamageSource damageSource, PlayerInventory inventory) {
//        if (!ObsidianSkullItem.DAMAGE_SOURCES.contains(damageSource)) {
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
//        return getCounterValue(stack) < ObsidianSkullItem.OBSIDIAN_SKULL_PROTECTION_TIME;
//    }
//
//    public static ItemStack getSkullWithLowestCounter(PlayerInventory inventory) {
//        ItemStack skull = ItemStack.EMPTY;
//
//        for (DefaultedList<ItemStack> defaultedList : ((CombinedInventoryAccessor) inventory).getCombinedInventory()) {
//            for (ItemStack stack : defaultedList) {
//                if (!stack.isEmpty() && stack.isOf(ItemRegistry.OBSIDIAN_SKULL_SHIELD)) {
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
//    public boolean canRepair(ItemStack stack, ItemStack repair) {
//        return repair.isOf(ItemRegistry.OBSIDIAN_INGOT) || super.canRepair(stack, repair);
//    }
//
//    @Override
//    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
//        super.appendTooltip(stack, world, tooltip, context);
//        tooltip.add(new TranslatableText("tooltip." + ForbiddenArcanus.MOD_ID + ".obsidian_skull_shield").formatted(Formatting.GRAY));
//    }
//    @Override
//    public void initializeClient(@Nonnull Consumer<IItemRenderProperties> consumer) {
//        consumer.accept(new IItemRenderProperties() {
//            private final NonNullLazy<BlockEntityWithoutLevelRenderer> renderer = NonNullLazy.of(() -> new ObsidianSkullShieldItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels()));
//
//            @Override
//            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
//                return renderer.get();
//            }
//        });
//    }
//
//    @javax.annotation.Nullable
//    @Override
//    public ICapabilityProvider initCapabilities(ItemStack stack, @javax.annotation.Nullable CompoundTag tag) {
//        return new CounterProvider(CounterCreator.of(ObsidianSkullCounter::new, COUNTER));
//    }
//    private SimpleCounter getCounter(CounterCapability counterCapability) {
//        return counterCapability.getCounter(COUNTER);
//    }
}
