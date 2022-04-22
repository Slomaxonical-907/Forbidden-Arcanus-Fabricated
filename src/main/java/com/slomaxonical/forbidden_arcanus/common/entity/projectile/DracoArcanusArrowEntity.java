package com.slomaxonical.forbidden_arcanus.common.entity.projectile;

import com.slomaxonical.forbidden_arcanus.core.registries.EntityRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class DracoArcanusArrowEntity extends PersistentProjectileEntity {

    public DracoArcanusArrowEntity(EntityType<? extends DracoArcanusArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public DracoArcanusArrowEntity(World world, LivingEntity shooter) {
        super(EntityRegistry.DRACO_ARCANUS_ARROW, shooter, world);
    }

    public DracoArcanusArrowEntity(World world, double x, double y, double z) {
        super(EntityRegistry.DRACO_ARCANUS_ARROW, x, y, z, world);
    }

//    @Override
//    public EntityType<?> getType() {
//        return EntityRegistry.DRACO_ARCANUS_ARROW;
//    }

    @Override
    public void tick() {
        super.tick();
        if (this.world.isClient() && !this.inGround) {
            this.world.addParticle(ParticleTypes.DRAGON_BREATH, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ItemRegistry.DRACO_ARCANUS_ARROW);
    }

    @Override
    protected void onHit(@Nonnull LivingEntity entity) {
        super.onHit(entity);

        AreaEffectCloudEntity areaEffectCloud = new AreaEffectCloudEntity(this.world, this.getX(), this.getY(), this.getZ());

        if (this.getOwner() instanceof LivingEntity owner) {
            areaEffectCloud.setOwner(owner);
        }
        areaEffectCloud.setParticleType(ParticleTypes.DRAGON_BREATH);
        areaEffectCloud.setRadius(2.0F);
        areaEffectCloud.setDuration(400);
        areaEffectCloud.setRadius((7.0F - areaEffectCloud.getRadius()) / (float) areaEffectCloud.getDuration());
        areaEffectCloud.addEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 1));

        this.world.syncWorldEvent(2006, this.getBlockPos(), 0);
        this.world.spawnEntity(areaEffectCloud);
    }
}
