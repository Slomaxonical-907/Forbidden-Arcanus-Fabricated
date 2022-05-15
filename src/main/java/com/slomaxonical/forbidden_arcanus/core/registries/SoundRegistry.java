package com.slomaxonical.forbidden_arcanus.core.registries;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoundRegistry implements AutoRegistryContainer<SoundEvent> {
    public static final SoundEvent DARK_BOLT_LAUNCH = createSound("dark_bolt_launch");
    public static final SoundEvent DARK_BOLT_HIT = createSound("dark_bolt_hit");

    public static final SoundEvent PIXI_ACTIVATED = createSound("pixi_activated");

    public static final SoundEvent CORE_ACTIVATED = createSound("runic_tenebris_core_activated");
    public static final SoundEvent CORE_AMBIENT = createSound("runic_tenebris_core_ambient");


//    public static final BlockSoundGroup ETHER = new BlockSoundGroup(1.0F, 1.0F, ETHER_BREAK, SoundEvents.BLOCK_WOOL_STEP, ETHER_PLACE, SoundEvents.BLOCK_ANCIENT_DEBRIS_HIT, SoundEvents.BLOCK_WOOL_FALL);

    private static SoundEvent createSound(String key) {
        Identifier id = new Identifier(ForbiddenArcanus.ID, key);
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

}
