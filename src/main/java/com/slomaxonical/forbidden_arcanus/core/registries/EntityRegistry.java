package com.slomaxonical.forbidden_arcanus.core.registries;

import com.slomaxonical.forbidden_arcanus.common.entity.CrimsonLightningBoltEntity;
import com.slomaxonical.forbidden_arcanus.common.entity.projectile.BoomArrowEntity;
import com.slomaxonical.forbidden_arcanus.common.entity.projectile.DracoArcanusArrowEntity;
import com.slomaxonical.forbidden_arcanus.common.entity.projectile.EnergyBallEntity;
import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;

public class EntityRegistry implements AutoRegistryContainer<EntityType<?>> {
    public static final EntityType<EnergyBallEntity> ENERGY_BALL = FabricEntityTypeBuilder.<EnergyBallEntity>create(SpawnGroup.MISC, EnergyBallEntity::new).dimensions(EntityDimensions.changing(1.0F,1.0F)).trackRangeBlocks(64).build();
    public static final EntityType<BoomArrowEntity> BOOM_ARROW = FabricEntityTypeBuilder.<BoomArrowEntity>create(SpawnGroup.MISC, BoomArrowEntity::new).dimensions(EntityDimensions.changing(0.5F,0.5F)).trackRangeBlocks(4).build();
    public static final EntityType<DracoArcanusArrowEntity> DRACO_ARCANUS_ARROW = FabricEntityTypeBuilder.<DracoArcanusArrowEntity>create(SpawnGroup.MISC, DracoArcanusArrowEntity::new).dimensions(EntityDimensions.changing(0.5F,0.5F)).trackRangeBlocks(4).build();
    public static final EntityType<CrimsonLightningBoltEntity> CRIMSON_LIGHTNING_BOLT = FabricEntityTypeBuilder.<CrimsonLightningBoltEntity>create(SpawnGroup.MISC, CrimsonLightningBoltEntity::new).dimensions(EntityDimensions.changing(0.0F,0.0F)).trackRangeBlocks(16).build();
    @Override
    public Registry<EntityType<?>> getRegistry() {
        return Registry.ENTITY_TYPE;
    }

    @Override
    public Class<EntityType<?>> getTargetFieldType() {
        return (Class<EntityType<?>>) (Object) EntityType.class;
    }
}
