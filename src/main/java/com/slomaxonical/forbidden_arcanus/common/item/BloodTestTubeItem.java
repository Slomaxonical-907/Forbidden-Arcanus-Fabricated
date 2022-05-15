package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BloodTestTubeItem extends Item {

    public static final int MAX_BLOOD = 3000;

    public BloodTestTubeItem(Settings settings) {
        super(settings);
    }

    public static int getBlood(ItemStack stack) {
        return stack.getOrCreateNbt().getInt("Blood");
    }

    public static ItemStack setBlood(ItemStack stack, int blood) {
        NbtCompound compound = stack.getOrCreateNbt();
        compound.putInt("Blood", blood);

        return stack;
    }

    public static void addBlood(ItemStack stack, int blood) {
        setBlood(stack, Math.min(getBlood(stack) + blood, MAX_BLOOD));
    }

    public static ItemStack removeBlood(ItemStack stack, int blood) {
        setBlood(stack, Math.max(getBlood(stack) - blood, 0));

        if (getBlood(stack) == 0) {
            stack = new ItemStack(ItemRegistry.TEST_TUBE);
        }
        return stack;
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return ItemRegistry.TEST_TUBE.getTranslationKey();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText(ForbiddenArcanus.ID + ".blood").append(": " + getBlood(stack) + "/" + MAX_BLOOD).formatted(Formatting.GRAY));
    }
}
