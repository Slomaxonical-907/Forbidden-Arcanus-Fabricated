package com.slomaxonical.forbidden_arcanus.core.registries.world;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.common.worldgen.structure.NipaFeature;
import com.slomaxonical.forbidden_arcanus.common.worldgen.structure.NipaGenerator;
import com.slomaxonical.forbidden_arcanus.common.worldgen.structure.config.NipaConfig;
import com.slomaxonical.forbidden_arcanus.core.config.WorldGenConfig;
import com.slomaxonical.forbidden_arcanus.core.helper.FATags;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class StructureRegistry {

    public static final StructurePieceType NIPA_PIECE = createPiece("nipa", NipaGenerator.Piece::new);
    public static final StructureFeature<NipaConfig> NIPA_FEATURE = createStructure("nipa", new NipaFeature(NipaConfig.CODEC));

    public static final RegistryEntry<ConfiguredStructureFeature<?, ?>> NIPA = register("nipa", NIPA_FEATURE.configure(new NipaConfig(0.3F), FATags.Biomes.HAS_NIPA));
    public static final RegistryEntry<ConfiguredStructureFeature<?, ?>> NIPA_ALWAYS_FLOATING = register("nipa_always_floating",NIPA_FEATURE.configure(new NipaConfig(1.0F), FATags.Biomes.HAS_NIPA_ALWAYS_FLOATING));


    static StructurePieceType createPiece(String name, StructurePieceType.ManagerAware type) {
        return Registry.register(Registry.STRUCTURE_PIECE, new Identifier(ForbiddenArcanus.MOD_ID, name), type);
    }
    static <F extends StructureFeature<?>> F createStructure(String name, F structureFeature) {
        return (F)Registry.register(Registry.STRUCTURE_FEATURE, name, structureFeature);
    }

    static RegistryEntry<ConfiguredStructureFeature<?, ?>> register(String name, ConfiguredStructureFeature<?, ?> configuredStructureFeature) {
        RegistryKey<ConfiguredStructureFeature<?, ?>> resourceKey = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier(ForbiddenArcanus.MOD_ID, name));

        return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, resourceKey, configuredStructureFeature);
    }

    public static void setupStructures() {
        RegistryKey<StructureSet> registryKey = RegistryKey.of(Registry.STRUCTURE_SET_KEY, new Identifier(ForbiddenArcanus.MOD_ID, "nipas"));
        BuiltinRegistries.add(BuiltinRegistries.STRUCTURE_SET, registryKey, new StructureSet(List.of(StructureSet.createEntry(NIPA), StructureSet.createEntry(NIPA_ALWAYS_FLOATING)), new RandomSpreadStructurePlacement(WorldGenConfig.NIPA_SPACING.get(), WorldGenConfig.NIPA_SEPARATION.get(), SpreadType.LINEAR, 710359251)));
    }

}
