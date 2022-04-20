package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.core.registries.StatusEffectsRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
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
            target.addStatusEffect(new StatusEffectInstance(StatusEffectsRegistry.DARKENED, 100, 0));
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(@Nonnull ItemStack stack, World worldIn, @Nonnull List<Text> tooltip, @Nonnull TooltipContext flag) {
        super.appendTooltip(stack, worldIn, tooltip, flag);
        tooltip.add(new TranslatableText("tooltip." + ForbiddenArcanus.MOD_ID + ".mystical_dagger").formatted(Formatting.GRAY));
    }
}
