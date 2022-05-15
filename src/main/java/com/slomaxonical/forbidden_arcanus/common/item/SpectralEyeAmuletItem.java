package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.core.registries.StatusEffectsRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpectralEyeAmuletItem extends Item {
    public SpectralEyeAmuletItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() && !this.isDeactivated(stack) && entity instanceof LivingEntity livingEntity) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffectsRegistry.SPECTRAL_VISION, 40, 0, false, false, true));
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        this.setDeactivated(stack, !this.isDeactivated(stack));

        return TypedActionResult.success(stack, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        Text toggle = new TranslatableText("tooltip." + ForbiddenArcanus.ID + ".toggle").formatted(Formatting.GRAY);

        boolean deactivated = this.isDeactivated(stack);
        tooltip.add(new TranslatableText("tooltip." + ForbiddenArcanus.ID + (deactivated ? ".deactivated" : ".activated")).formatted(deactivated ? Formatting.RED : Formatting.GREEN).append(" ").append(toggle));
    }

    public boolean isDeactivated(ItemStack stack) {
        return stack.getOrCreateNbt().getBoolean("Deactivated");
    }

    public void setDeactivated(ItemStack stack, boolean deactivated) {
        stack.getOrCreateNbt().putBoolean("Deactivated", deactivated);
    }
}
