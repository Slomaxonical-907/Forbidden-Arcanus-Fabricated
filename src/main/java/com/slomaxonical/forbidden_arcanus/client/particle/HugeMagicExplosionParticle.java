package com.slomaxonical.forbidden_arcanus.client.particle;

import com.slomaxonical.forbidden_arcanus.core.registries.ParticleRegistry;
import net.minecraft.client.particle.NoRenderParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;

import javax.annotation.Nonnull;

/**
 * Huge Magic Explosion Particle <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.client.particle.HugeMagicExplosionParticle
 *
 * @author stal111
 * @version 1.17.1 - 2.0.0
 * @since 2021-08-01
 */
public class HugeMagicExplosionParticle extends NoRenderParticle {

    protected HugeMagicExplosionParticle(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z);
        this.maxAge = 9;
    }

    @Override
    public void tick() {
        for (int i = 0; i < 6; i++) {
            double x = this.x + (this.random.nextDouble() - this.random.nextDouble()) * 4.0D;
            double y = this.y + (this.random.nextDouble() - this.random.nextDouble()) * 4.0D;
            double z = this.z + (this.random.nextDouble() - this.random.nextDouble()) * 4.0D;

            this.world.addParticle(ParticleRegistry.MAGIC_EXPLOSION, x, y, z, (float) this.age / (float) this.maxAge, 0.0D, 0.0D);
        }

        if (this.age++ >= this.maxAge) {
            this.markDead();
        }
    }

    public static class Factory implements ParticleFactory<DefaultParticleType> {
        public Particle createParticle(@Nonnull DefaultParticleType type, @Nonnull ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new HugeMagicExplosionParticle(world, x, y, z);
        }
    }
}
