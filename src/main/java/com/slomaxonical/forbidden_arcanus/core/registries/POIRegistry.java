package com.slomaxonical.forbidden_arcanus.core.registries;

import com.google.common.collect.ImmutableSet;
import com.slomaxonical.forbidden_arcanus.common.block.HephaestusForgeBlock;
import com.slomaxonical.forbidden_arcanus.common.block.PedestalBlock;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.fabricmc.fabric.mixin.object.builder.PointOfInterestTypeAccessor;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.poi.PointOfInterestType;

public class POIRegistry implements AutoRegistryContainer<PointOfInterestType> {

    public static final PointOfInterestType PEDESTAL = PointOfInterestTypeAccessor.callCreate("pedestal", ImmutableSet.of(BlockRegistry.DARKSTONE_PEDESTAL.getDefaultState().with(PedestalBlock.RITUAL, true)), 0, 1);
    public static final PointOfInterestType HEPHAESTUS_FORGE = PointOfInterestTypeAccessor.callCreate("hephaestus_forge", ImmutableSet.of(BlockRegistry.HEPHAESTUS_FORGE.getDefaultState().with(HephaestusForgeBlock.ACTIVATED, true)), 0, 1);

    @Override
    public Registry<PointOfInterestType> getRegistry() {
        return Registry.POINT_OF_INTEREST_TYPE;
    }

    @Override
    public Class<PointOfInterestType> getTargetFieldType() {
        return PointOfInterestType.class;
    }
}
