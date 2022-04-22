package com.slomaxonical.forbidden_arcanus.core.config;


import net.valhelsia.valhelsia_core.core.config.ValhelsiaConfigSpec;

public class BlockConfig {

    public static ValhelsiaConfigSpec.BooleanValue STELLA_ARCANUM_EXPLODE;
    public static ValhelsiaConfigSpec.BooleanValue STELLA_ARCANUM_BLOCK_DAMAGE;
    public static ValhelsiaConfigSpec.IntValue STELLA_ARCANUM_EXPLOSION_RADIUS;

    public static ValhelsiaConfigSpec.IntValue RUNIC_CHISELED_POLISHED_DARKSTONE_RADIUS;

    public static ValhelsiaConfigSpec.DoubleValue EDELWOOD_LADDER_SPEED;

    public static void init(ValhelsiaConfigSpec.Builder builder) {
        builder.push("blocks");

        STELLA_ARCANUM_EXPLODE = builder.comment("Should Stella Arcanum explode when mined [default: true]").define("stella_arcanum.explode", true);
        STELLA_ARCANUM_BLOCK_DAMAGE = builder.comment("Should Stella Arcanum explosions deal Block Damage (if explosions enabled) [default: true]").define("stella_arcanum.block_damage", true);
        STELLA_ARCANUM_EXPLOSION_RADIUS = builder.comment("Radius of Stella Arcanum explosions (if explosions enabled) [default: 3]").defineInRange("stella_arcanum.explosion_radius", 3, 1, 10);

        RUNIC_CHISELED_POLISHED_DARKSTONE_RADIUS = builder.comment("Radius the Activated Runic Chiseled Polished Darkstone prevents Entities from spawning in (in Blocks) [default: 25]").defineInRange("runic_chiseled_polished_darkstone.entity_spawn_blocking_radius", 25, 1, 100);


        EDELWOOD_LADDER_SPEED = builder.comment("How much speed is added to the player when using an Edelwood Ladder [default: 0.07").defineInRange("edelwood_ladder.speed", 0.07, 0, 10);

        builder.pop();
    }
}
