package com.slomaxonical.forbidden_arcanus.core.registries.block;

import com.slomaxonical.forbidden_arcanus.common.blockEntity.*;
import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class BlockEntityRegistry implements AutoRegistryContainer<BlockEntityType<?>> {
    public static final BlockEntityType<BlackHoleBlockEntity> BLACK_HOLE = FabricBlockEntityTypeBuilder.create(BlackHoleBlockEntity::new, BlockRegistry.BLACK_HOLE).build();
    public static final BlockEntityType<ObsidianSkullBlockEntity> OBSIDIAN_SKULL = FabricBlockEntityTypeBuilder.create(ObsidianSkullBlockEntity::new, BlockRegistry.OBSIDIAN_SKULL, BlockRegistry.OBSIDIAN_WALL_SKULL, BlockRegistry.ETERNAL_OBSIDIAN_SKULL, BlockRegistry.ETERNAL_OBSIDIAN_WALL_SKULL).build(null);
    public static final BlockEntityType<UtremJarBlockEntity> UTREM_JAR = FabricBlockEntityTypeBuilder.create(UtremJarBlockEntity::new, BlockRegistry.UTREM_JAR).build(null);
    public static final BlockEntityType<NipaBlockEntity> NIPA = FabricBlockEntityTypeBuilder.create(NipaBlockEntity::new, BlockRegistry.NIPA).build(null);
    public static final BlockEntityType<HephaestusForgeBlockEntity> HEPHAESTUS_FORGE = FabricBlockEntityTypeBuilder.create(HephaestusForgeBlockEntity::new, BlockRegistry.HEPHAESTUS_FORGE).build(null);
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL = FabricBlockEntityTypeBuilder.create(PedestalBlockEntity::new, BlockRegistry.DARKSTONE_PEDESTAL, BlockRegistry.ARCANE_DARKSTONE_PEDESTAL).build(null);
    public static final BlockEntityType<ArcaneCrystalObeliskBlockEntity> ARCANE_CRYSTAL_OBELISK = FabricBlockEntityTypeBuilder.create(ArcaneCrystalObeliskBlockEntity::new, BlockRegistry.ARCANE_CRYSTAL_OBELISK).build(null);


    @Override
    public Registry<BlockEntityType<?>> getRegistry() {
        return Registry.BLOCK_ENTITY_TYPE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<BlockEntityType<?>> getTargetFieldType() {
        return (Class<BlockEntityType<?>>) (Object) BlockEntityType.class;
    }
}
