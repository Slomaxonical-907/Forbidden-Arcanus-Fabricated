package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.List;

public class MysticalDaggerItem extends SwordItem {
    public MysticalDaggerItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, (int) attackDamage, attackSpeed, settings);
    }
    @Override
    public boolean hasRecipeRemainder(){return true;}

//    @Override
//    public Item getRecipeRemainder() {
//        ItemStack stack = new ItemStack(remainder);
//        ItemStack copy = stack.copy();
//        copy.setDamage(stack.getDamage() + 10);
//        return copy;
//    }

    @Override
    public boolean postHit(@Nonnull ItemStack stack, @Nonnull LivingEntity target, @Nonnull LivingEntity attacker) {
        if (attacker.getRandom().nextInt(5) == 0) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKENED.get(), 100, 0));
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(@Nonnull ItemStack stack, World worldIn, @Nonnull List<Text> tooltip, @Nonnull TooltipContext flag) {
        super.appendTooltip(stack, worldIn, tooltip, flag);
        tooltip.add(new TranslatableText("tooltip." + ForbiddenArcanus.MOD_ID + ".mystical_dagger").formatted(Formatting.GRAY));
    }
}
