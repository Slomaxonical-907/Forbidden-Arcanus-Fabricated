package com.slomaxonical.forbidden_arcanus.common.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SanityMeterItem extends Item {
    public SanityMeterItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (world.isClient()) {
//            IAureal aureal = AurealHelper.getCapability(player);

//            player.sendMessage(new TranslatableText("forbidden_arcanus.aureal").append(": " + aureal.getAureal() + "/200 - ").append(new TranslatableText("forbidden_arcanus.corruption").append(": " + aureal.getCorruption() + "/100")), true);
            player.sendMessage(new TranslatableText("forbidden_arcanus.aureal").append(": " + "aureal.getAureal()" + "/200 - ").append(new TranslatableText("forbidden_arcanus.corruption").append(": " + "aureal.getCorruption()" + "/100")), true);
        }
        return new TypedActionResult<>(ActionResult.success(world.isClient()),stack);
    }
}
