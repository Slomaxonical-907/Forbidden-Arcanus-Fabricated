package com.slomaxonical.forbidden_arcanus.common.block.util.saplinggenerator;

import com.slomaxonical.forbidden_arcanus.core.registries.FeatureRegistry;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class MysterywoodTree extends SaplingGenerator {
    @Nullable
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return BuiltinRegistries.CONFIGURED_FEATURE.getEntry(BuiltinRegistries.CONFIGURED_FEATURE.getKey(FeatureRegistry.MYSTERYWOOD_TREE).orElseThrow()).orElseThrow();
    }
}
