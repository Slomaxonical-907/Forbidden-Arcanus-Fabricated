package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.core.config.ItemConfig;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.common.util.ItemStackUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OrbOfTemporaryFlightItem extends Item {
    public OrbOfTemporaryFlightItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        ItemStackUtils.shrinkStack(player, stack);
//        player.getCapability(CounterProvider.CAPABILITY).ifPresent(counterCapability -> {
//            SimpleCounter counter = counterCapability.getCounter(new Identifier(ForbiddenArcanus.MOD_ID, "flight_timer"));
//
//            counter.setActive(true);
//            counter.setValue(counter.getValue() + ItemConfig.ORB_OF_TEMPORARY_FLIGHT_TIME.get());
//        });

        player.incrementStat(Stats.USED.getOrCreateStat(this));

        return TypedActionResult.success(stack, world.isClient());

    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("tooltip." + ForbiddenArcanus.ID + ".duration").formatted(Formatting.GRAY).append(": " + StringHelper.formatTicks(ItemConfig.ORB_OF_TEMPORARY_FLIGHT_TIME.get())));
    }
}
