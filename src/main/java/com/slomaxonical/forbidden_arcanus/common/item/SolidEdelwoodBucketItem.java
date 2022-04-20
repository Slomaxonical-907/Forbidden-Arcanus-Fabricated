package com.slomaxonical.forbidden_arcanus.common.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;

public class SolidEdelwoodBucketItem extends Item {
    public SolidEdelwoodBucketItem(Block powderSnow, SoundEvent blockPowderSnowBreak, Settings settings) {
        super(settings);
    }
}
