package com.slomaxonical.forbidden_arcanus.core.registries;

import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.registry.Registry;

public class ParticleRegistry {

    public static final DefaultParticleType SOUL = FabricParticleTypes.simple();
    public static final DefaultParticleType AUREAL_MOTE = FabricParticleTypes.simple();
    public static final DefaultParticleType MAGIC_EXPLOSION = FabricParticleTypes.simple(true);
    public static final DefaultParticleType HUGE_MAGIC_EXPLOSION =  FabricParticleTypes.simple(true);

//    @Override
//    public Registry<ParticleType<?>> getRegistry() {
//        return Registry.PARTICLE_TYPE;
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public Class<ParticleType<?>> getTargetFieldType() {
//        return (Class<ParticleType<?>>) (Object) ParticleType.class;
//    }
}
