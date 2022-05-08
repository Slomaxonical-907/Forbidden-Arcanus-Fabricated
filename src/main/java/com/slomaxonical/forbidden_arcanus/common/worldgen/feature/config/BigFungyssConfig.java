package com.slomaxonical.forbidden_arcanus.common.worldgen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class BigFungyssConfig implements FeatureConfig {

    public static final Codec<BigFungyssConfig> CODEC = RecordCodecBuilder.create((builder) -> {
        return builder.group(BlockStateProvider.TYPE_CODEC.fieldOf("cap_provider").forGetter((config) -> {
            return config.capProvider;
        }), BlockStateProvider.TYPE_CODEC.fieldOf("stem_provider").forGetter((config) -> {
            return config.stemProvider;
        }), BlockStateProvider.TYPE_CODEC.fieldOf("hyphae_provider").forGetter((config) -> {
            return config.stemProvider;
        }), Codec.intRange(0, 1).fieldOf("variant").forGetter((config) -> {
            return config.variant;
        })).apply(builder, BigFungyssConfig::new);
    });
    public final BlockStateProvider capProvider;
    public final BlockStateProvider stemProvider;
    public final BlockStateProvider hyphaeProvider;
    public final int variant;

    public BigFungyssConfig(BlockStateProvider capProvider, BlockStateProvider stemProvider, BlockStateProvider hyphaeProvider, int variant) {
        this.capProvider = capProvider;
        this.stemProvider = stemProvider;
        this.hyphaeProvider = hyphaeProvider;
        this.variant = variant;
    }
}