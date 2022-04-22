package com.slomaxonical.forbidden_arcanus.common.entity.projectile;

import com.slomaxonical.forbidden_arcanus.core.config.ItemConfig;
import com.slomaxonical.forbidden_arcanus.core.registries.EntityRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class BoomArrowEntity extends PersistentProjectileEntity {

    public BoomArrowEntity(EntityType<? extends BoomArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public BoomArrowEntity(World world, LivingEntity shooter) {
        super(EntityRegistry.BOOM_ARROW, shooter, world);
    }

    public BoomArrowEntity(World world, double x, double y, double z) {
        super(EntityRegistry.BOOM_ARROW, x, y, z, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.world.isClient() && !this.inGround) {
            this.world.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ItemRegistry.BOOM_ARROW);
    }

    @Override
    protected void onHit(LivingEntity entity) {
        super.onHit(entity);
        this.world.createExplosion(this, entity.getX(), entity.getY(), entity.getZ(), ItemConfig.BOOM_ARROW_EXPLOSION_RADIUS.get(), ItemConfig.BOOM_ARROW_BLOCK_DAMAGE.get() ? Explosion.DestructionType.BREAK : Explosion.DestructionType.NONE);
    }
}
