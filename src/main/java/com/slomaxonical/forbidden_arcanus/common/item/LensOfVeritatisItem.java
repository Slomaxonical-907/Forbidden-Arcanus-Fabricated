package com.slomaxonical.forbidden_arcanus.common.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public class LensOfVeritatisItem extends Item {

    public static final String LENS_SLOT = "LensSlot";

    public static final int PARTICLE_RANGE = 20;

    public LensOfVeritatisItem(Settings settings) {
        super(settings);
    }
}
