package com.slomaxonical.forbidden_arcanus.common.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

import java.util.function.Supplier;

public class EdelwoodSoupBucketItem extends Item {
    private final Item soup;

    public EdelwoodSoupBucketItem(Item soup, Settings properties) {
        super(properties);
        this.soup = soup;
    }
}
