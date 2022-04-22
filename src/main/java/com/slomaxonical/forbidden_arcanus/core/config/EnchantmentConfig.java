package com.slomaxonical.forbidden_arcanus.core.config;

import net.valhelsia.valhelsia_core.core.config.ValhelsiaConfigSpec;

public class EnchantmentConfig {

    public static ValhelsiaConfigSpec.BooleanValue INDESTRUCTIBLE_REPAIR_ITEM;

    public static void init(ValhelsiaConfigSpec.Builder builder) {
        builder.push("enchantments");

        INDESTRUCTIBLE_REPAIR_ITEM = builder.comment("Should the item be repaired after applying the Indestructible enchantment [default: true]").define("indestructible.repair_item", true);

        builder.pop();
    }
}
