package com.slomaxonical.forbidden_arcanus.core.registries;

import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.registry.Registry;

public class ParticleRegistry implements AutoRegistryContainer<ParticleType<?>> {

//    public static final ParticleType<DefaultParticleType> SOUL = FabricParticleTypes.simple();
//    public static final ParticleType<DefaultParticleType> AUREAL_MOTE = FabricParticleTypes.simple();
//    public static final ParticleType<DefaultParticleType> MAGIC_EXPLOSION = FabricParticleTypes.simple(true);
//    public static final ParticleType<DefaultParticleType> HUGE_MAGIC_EXPLOSION =  FabricParticleTypes.simple(true);

    @Override
    public Registry<ParticleType<?>> getRegistry() {
        return Registry.PARTICLE_TYPE;
    }

    @Override
    public Class<ParticleType<?>> getTargetFieldType() {
        return (Class<ParticleType<?>>) (Object) ParticleType.class;
    }
}
