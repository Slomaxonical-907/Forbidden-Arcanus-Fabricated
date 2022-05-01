package com.slomaxonical.forbidden_arcanus.core.registries;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;

public class ParticleRegistry {

    public static final DefaultParticleType SOUL = FabricParticleTypes.simple();
    public static final DefaultParticleType AUREAL_MOTE = FabricParticleTypes.simple();
    public static final DefaultParticleType MAGIC_EXPLOSION = FabricParticleTypes.simple(true);
    public static final DefaultParticleType HUGE_MAGIC_EXPLOSION =  FabricParticleTypes.simple(true);

}
