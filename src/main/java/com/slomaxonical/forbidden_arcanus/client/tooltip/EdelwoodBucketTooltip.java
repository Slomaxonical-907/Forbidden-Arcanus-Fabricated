package com.slomaxonical.forbidden_arcanus.client.tooltip;

import net.minecraft.client.item.TooltipData;
import net.minecraft.item.ItemStack;

public record EdelwoodBucketTooltip(ItemStack filledBucket, int fullness, int capacity) implements TooltipData {
}
