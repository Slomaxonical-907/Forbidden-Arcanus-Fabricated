package com.slomaxonical.forbidden_arcanus.core.registries.world;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.BigFungyssFeature;
import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.EdelwoodFeature;
import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.MegaFungyssFeature;
import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.RootFeature;
import com.slomaxonical.forbidden_arcanus.common.worldgen.feature.config.BigFungyssConfig;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;

//TODO:Use OWO Registries
public class FeatureRegistry {
    public static final Feature<SingleStateFeatureConfig> PETRIFIED_ROOT = new RootFeature(SingleStateFeatureConfig.CODEC);
    public static final Feature<DefaultFeatureConfig> EDELWOOD = new EdelwoodFeature(DefaultFeatureConfig.CODEC);
    public static final Feature<BigFungyssConfig> BIG_FUNGYSS =  new BigFungyssFeature(BigFungyssConfig.CODEC);
    public static final Feature<BigFungyssConfig> MEGA_FUNGYSS =  new MegaFungyssFeature(BigFungyssConfig.CODEC);

    public static void register() {
        Registry.register(Registry.FEATURE, new Identifier(ForbiddenArcanus.MOD_ID,"petrified_root"), PETRIFIED_ROOT);
        Registry.register(Registry.FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "edelwood"), EDELWOOD);
        Registry.register(Registry.FEATURE, new Identifier(ForbiddenArcanus.MOD_ID, "big_fungyss"), BIG_FUNGYSS);
        Registry.register(Registry.FEATURE, new Identifier(ForbiddenArcanus.MOD_ID,"mega_fungyss"), MEGA_FUNGYSS);
    }
}

