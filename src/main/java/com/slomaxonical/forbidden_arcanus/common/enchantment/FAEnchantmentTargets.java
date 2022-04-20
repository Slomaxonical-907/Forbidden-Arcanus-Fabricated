package com.slomaxonical.forbidden_arcanus.common.enchantment;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;

public class FAEnchantmentTargets implements Runnable {
    @Override
    public void run() {
        ClassTinkerers.enumBuilder(FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_1886")).addEnumSubclass("EDELWOOD_BUCKET","com.slomaxonical.forbidden_arcanus.common.enchantment.EdelwoodBucketTarget").build();
    }
}
