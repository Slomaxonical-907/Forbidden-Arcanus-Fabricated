package com.slomaxonical.forbidden_arcanus.common.enchantment;

import com.chocohead.mm.api.ClassTinkerers;

public class FAEnchantmentTargets implements Runnable {
    @Override
    public void run() {
        ClassTinkerers.enumBuilder("EnchantmentTarget").addEnumSubclass("EDELWOOD_BUCKET","com.slomaxonical.forbidden_arcanus.common.enchantment.EdelwoodBucketTarget").build();
    }
}
