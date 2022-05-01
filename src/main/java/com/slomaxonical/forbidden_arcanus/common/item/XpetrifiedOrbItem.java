package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.core.config.ItemConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.common.util.ItemStackUtils;

public class XpetrifiedOrbItem extends Item {
    public XpetrifiedOrbItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        player.addExperience(ItemConfig.XPETRIFIED_ORB_EXPERIENCE_POINTS.get());
        ItemStackUtils.shrinkStack(player, stack);

        return new TypedActionResult<>(ActionResult.SUCCESS, stack);
    }
}
