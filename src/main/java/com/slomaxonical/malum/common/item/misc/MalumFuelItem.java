package com.slomaxonical.malum.common.item.misc;

import net.minecraft.item.Item;

public class MalumFuelItem extends Item {
    public final int fuel;
    public MalumFuelItem(Settings settings, int fuel) {
        super(settings);
        this.fuel = fuel;
    }
}
