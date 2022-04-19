package com.slomaxonical.forbidden_arcanus.core.registries;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoundRegistry implements AutoRegistryContainer<SoundEvent> {
    public static final SoundEvent SOULSTONE_BREAK = createSound("soulstone_break");
    public static final SoundEvent SOULSTONE_PLACE = createSound("soulstone_place");
    public static final SoundEvent SOULSTONE_STEP = createSound("soulstone_step");
    public static final SoundEvent SOULSTONE_HIT = createSound("soulstone_hit");

    public static final SoundEvent DEEPSLATE_SOULSTONE_BREAK = createSound("deepslate_soulstone_break");
    public static final SoundEvent DEEPSLATE_SOULSTONE_PLACE = createSound("deepslate_soulstone_place");
    public static final SoundEvent DEEPSLATE_SOULSTONE_STEP = createSound("deepslate_soulstone_step");
    public static final SoundEvent DEEPSLATE_SOULSTONE_HIT = createSound("deepslate_soulstone_hit");

    public static final SoundEvent BLAZING_QUARTZ_ORE_BREAK = createSound("blazing_quartz_ore_break");
    public static final SoundEvent BLAZING_QUARTZ_ORE_PLACE = createSound("blazing_quartz_ore_place");

    public static final SoundEvent BLAZING_QUARTZ_BLOCK_BREAK = createSound("blazing_quartz_block_break");
    public static final SoundEvent BLAZING_QUARTZ_BLOCK_PLACE = createSound("blazing_quartz_block_place");
    public static final SoundEvent BLAZING_QUARTZ_BLOCK_STEP = createSound("blazing_quartz_block_step");
    public static final SoundEvent BLAZING_QUARTZ_BLOCK_HIT = createSound("blazing_quartz_block_hit");

    public static final SoundEvent ARCANE_CHARCOAL_BLOCK_BREAK = createSound("arcane_charcoal_block_break");
    public static final SoundEvent ARCANE_CHARCOAL_BLOCK_PLACE = createSound("arcane_charcoal_block_place");
    public static final SoundEvent ARCANE_CHARCOAL_BLOCK_STEP = createSound("arcane_charcoal_block_step");
    public static final SoundEvent ARCANE_CHARCOAL_BLOCK_HIT = createSound("arcane_charcoal_block_hit");

    public static final SoundEvent TAINTED_ROCK_BREAK = createSound("tainted_rock_break");
    public static final SoundEvent TAINTED_ROCK_PLACE = createSound("tainted_rock_place");
    public static final SoundEvent TAINTED_ROCK_STEP = createSound("tainted_rock_step");
    public static final SoundEvent TAINTED_ROCK_HIT = createSound("tainted_rock_hit");

    public static final SoundEvent HALLOWED_GOLD_BREAK = createSound("hallowed_gold_break");
    public static final SoundEvent HALLOWED_GOLD_HIT = createSound("hallowed_gold_hit");
    public static final SoundEvent HALLOWED_GOLD_PLACE = createSound("hallowed_gold_place");
    public static final SoundEvent HALLOWED_GOLD_STEP = createSound("hallowed_gold_step");

    public static final SoundEvent SOUL_STAINED_STEEL_BREAK = createSound("soul_stained_steel_break");
    public static final SoundEvent SOUL_STAINED_STEEL_HIT = createSound("soul_stained_steel_hit");
    public static final SoundEvent SOUL_STAINED_STEEL_PLACE = createSound("soul_stained_steel_place");
    public static final SoundEvent SOUL_STAINED_STEEL_STEP = createSound("soul_stained_steel_step");

    public static final SoundEvent ETHER_PLACE = createSound("ether_place");
    public static final SoundEvent ETHER_BREAK = createSound("ether_break");

    public static final SoundEvent SCYTHE_CUT = createSound("scythe_cut");
    public static final SoundEvent SPIRIT_HARVEST = createSound("spirit_harvest");

    public static final SoundEvent TOTEM_CHARGE = createSound("totem_charge");
    public static final SoundEvent TOTEM_ACTIVATED = createSound("totem_activated");
    public static final SoundEvent TOTEM_CANCELLED = createSound("totem_cancelled");
    public static final SoundEvent TOTEM_ENGRAVE = createSound("totem_engrave");

    public static final SoundEvent ALTAR_CRAFT = createSound("altar_craft");
    public static final SoundEvent ALTAR_LOOP = createSound("altar_loop");
    public static final SoundEvent ALTAR_CONSUME = createSound("altar_consume");
    public static final SoundEvent ALTAR_SPEED_UP = createSound("altar_speed_up");

    public static final SoundEvent CRUCIBLE_CRAFT = createSound("crucible_craft");
    public static final SoundEvent CRUCIBLE_LOOP = createSound("crucible_loop");

    public static final SoundEvent SINISTER_EQUIP = createSound("sinister_equip");
    public static final SoundEvent HOLY_EQUIP = createSound("holy_equip");

    public static final SoundEvent VOID_SLASH = createSound("void_slash");

    public static final SoundEvent SOUL_WARD_HIT = createSound("soul_ward_hit");
    public static final SoundEvent SOUL_WARD_GROW = createSound("soul_ward_grow");
    public static final SoundEvent SOUL_WARD_CHARGE = createSound("soul_ward_charge");

    public static final SoundEvent HEART_OF_STONE_HIT = createSound("heart_of_stone_hit");
    public static final SoundEvent HEART_OF_STONE_GROW = createSound("heart_of_stone_grow");

    public static final SoundEvent SUSPICIOUS_SOUND = createSound("suspicious_sound");

    public static final BlockSoundGroup SOULSTONE = new BlockSoundGroup(1.0F, 1.0F, SOULSTONE_BREAK, SOULSTONE_STEP, SOULSTONE_PLACE, SOULSTONE_HIT, SoundEvents.BLOCK_STONE_FALL);
    public static final BlockSoundGroup DEEPSLATE_SOULSTONE = new BlockSoundGroup(1.0F, 1.0F, DEEPSLATE_SOULSTONE_BREAK, DEEPSLATE_SOULSTONE_STEP, DEEPSLATE_SOULSTONE_PLACE, DEEPSLATE_SOULSTONE_HIT, SoundEvents.BLOCK_DEEPSLATE_FALL);
    public static final BlockSoundGroup BLAZING_QUARTZ_ORE = new BlockSoundGroup(1.0F, 1.0F, BLAZING_QUARTZ_ORE_BREAK, SoundEvents.BLOCK_NETHER_ORE_STEP, BLAZING_QUARTZ_ORE_PLACE, SoundEvents.BLOCK_NETHER_ORE_HIT, SoundEvents.BLOCK_NETHER_GOLD_ORE_FALL);
    public static final BlockSoundGroup BLAZING_QUARTZ_BLOCK = new BlockSoundGroup(1.0F, 1.25f, BLAZING_QUARTZ_BLOCK_BREAK, BLAZING_QUARTZ_BLOCK_STEP, BLAZING_QUARTZ_BLOCK_PLACE, BLAZING_QUARTZ_BLOCK_HIT, SoundEvents.BLOCK_NETHER_GOLD_ORE_FALL);
    public static final BlockSoundGroup ARCANE_CHARCOAL_BLOCK = new BlockSoundGroup(1.0F, 0.9f, ARCANE_CHARCOAL_BLOCK_BREAK, ARCANE_CHARCOAL_BLOCK_STEP, ARCANE_CHARCOAL_BLOCK_PLACE, ARCANE_CHARCOAL_BLOCK_HIT, SoundEvents.BLOCK_NETHER_GOLD_ORE_FALL);
    public static final BlockSoundGroup TAINTED_ROCK = new BlockSoundGroup(1.0F, 1.1F, TAINTED_ROCK_BREAK, TAINTED_ROCK_STEP, TAINTED_ROCK_PLACE, TAINTED_ROCK_HIT, SoundEvents.BLOCK_BASALT_FALL);
    public static final BlockSoundGroup TWISTED_ROCK = new BlockSoundGroup(1.0F, 0.85F, TAINTED_ROCK_BREAK, TAINTED_ROCK_STEP, TAINTED_ROCK_PLACE, TAINTED_ROCK_HIT, SoundEvents.BLOCK_BASALT_FALL);
    public static final BlockSoundGroup HALLOWED_GOLD = new BlockSoundGroup(1.0F, 1.0F, HALLOWED_GOLD_BREAK, HALLOWED_GOLD_STEP, HALLOWED_GOLD_PLACE, HALLOWED_GOLD_HIT, SoundEvents.BLOCK_METAL_FALL);
    public static final BlockSoundGroup SOUL_STAINED_STEEL = new BlockSoundGroup(1.0F, 1.0F, SOUL_STAINED_STEEL_BREAK, SOUL_STAINED_STEEL_STEP, SOUL_STAINED_STEEL_PLACE, SOUL_STAINED_STEEL_HIT, SoundEvents.BLOCK_METAL_FALL);
    public static final BlockSoundGroup ETHER = new BlockSoundGroup(1.0F, 1.0F, ETHER_BREAK, SoundEvents.BLOCK_WOOL_STEP, ETHER_PLACE, SoundEvents.BLOCK_ANCIENT_DEBRIS_HIT, SoundEvents.BLOCK_WOOL_FALL);

    private static SoundEvent createSound(String key) {
        Identifier id = new Identifier(ForbiddenArcanus.MOD_ID, key);
        return new SoundEvent(id);
    }
    @Override
    public Registry<SoundEvent> getRegistry() {
        return Registry.SOUND_EVENT;
    }

    @Override
    public Class<SoundEvent> getTargetFieldType() {
        return SoundEvent.class;
    }

    public static void init(){}
}
