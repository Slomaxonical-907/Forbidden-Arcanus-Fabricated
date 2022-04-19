package com.slomaxonical.forbidden_arcanus.common.item;

import com.google.common.collect.ImmutableMap;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Map;

public class EdelwoodBucketItem extends BucketItem {

    public static final Map<Item, Item> ITEM_TO_BUCKET = new ImmutableMap.Builder<Item, Item>()
            .put(Items.WATER_BUCKET, ItemRegistry.EDELWOOD_WATER_BUCKET)
            .put(Items.LAVA_BUCKET, ItemRegistry.EDELWOOD_LAVA_BUCKET)
            .put(Items.MILK_BUCKET, ItemRegistry.EDELWOOD_MILK_BUCKET)
            .put(Items.POWDER_SNOW_BUCKET, ItemRegistry.EDELWOOD_POWDER_SNOW_BUCKET)
            .build();
    private static final double BURN_CHANCE = 0.005;

    private final int capacity;

    public EdelwoodBucketItem(Fluid fluid, Settings properties) {
        this(fluid, 0, properties);
    }

    public EdelwoodBucketItem( Fluid fluid, int capacity, Settings properties) {
        super(fluid, properties);
        this.capacity = capacity;
    }
}
