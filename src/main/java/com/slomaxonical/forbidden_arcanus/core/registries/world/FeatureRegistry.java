package com.slomaxonical.forbidden_arcanus.core.registries.world;

import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.EdelwoodFeature;
import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.RootFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;

//TODO:Use OWO Registries
public class FeatureRegistry {
    public static final Feature<SingleStateFeatureConfig> PETRIFIED_ROOT = new RootFeature(SingleStateFeatureConfig.CODEC);
    public static final Feature<DefaultFeatureConfig> EDELWOOD = new EdelwoodFeature(DefaultFeatureConfig.CODEC);
    public static void register() {
        Registry.register(Registry.FEATURE, "petrified_root", PETRIFIED_ROOT);
        Registry.register(Registry.FEATURE, "edelwood", EDELWOOD);
    }
}

