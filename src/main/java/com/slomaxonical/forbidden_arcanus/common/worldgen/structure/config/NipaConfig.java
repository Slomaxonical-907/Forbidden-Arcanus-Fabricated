package com.slomaxonical.forbidden_arcanus.common.worldgen.structure.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;

public class NipaConfig implements FeatureConfig {

    public static final Codec<NipaConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(Codec.floatRange(0.0F, 1.0F).fieldOf("floating_probability").forGetter((config) -> {
            return config.floatingProbability;
        })).apply(builder, NipaConfig::new);
    });

    private final float floatingProbability;

    public NipaConfig(float floatingProbability) {
        this.floatingProbability = floatingProbability;
    }

    public float getFloatingProbability() {
        return floatingProbability;
    }
}
