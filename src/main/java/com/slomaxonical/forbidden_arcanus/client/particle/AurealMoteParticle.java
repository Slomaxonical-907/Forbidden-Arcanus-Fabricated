package com.slomaxonical.forbidden_arcanus.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

import javax.annotation.Nonnull;

/**
 * Aureal Mote Particle <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.client.particle.AurealMoteParticle
 *
 * @author stal111
 * @version 1.17.1 - 2.0.0
 * @since 2021-01-28
 */
public class AurealMoteParticle extends SpriteBillboardParticle {

    private float alpha = 0.0F;

    private AurealMoteParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);
        this.x = x;
        this.y = y;
        this.z = z;
        this.velocityY = ySpeed;
        this.scale = 0.1F * (this.random.nextFloat() * 0.3F + 0.8F);
        this.maxAge = 60 + this.random.nextInt(12);
        this.setAlpha(alpha);
    }

    @Nonnull
    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void move(double x, double y, double z) {
        this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
        this.repositionFromBoundingBox();
    }

    @Override
    public float getSize(float scaleFactor) {
        float f = ((float) this.age + scaleFactor) / (float) this.maxAge;
        return this.scale * (1.0F - f * f * 0.5F);
    }

    @Override
    public void tick() {
        if (alpha < 1.0F) {
            alpha += 0.05F;
            this.setAlpha(alpha);
        }
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.age++ >= this.maxAge) {
            this.markDead();
        } else {
            this.move(0, this.velocityY, 0);
            this.velocityY *= 0.91;
        }
    }
    @Override
    protected int getBrightness(float tint) {
        return 0xF000F0;
    }

    public record Factory(SpriteProvider spriteSet) implements ParticleFactory<DefaultParticleType> {

        @Override
        public Particle createParticle(@Nonnull DefaultParticleType type, @Nonnull ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            AurealMoteParticle particle = new AurealMoteParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.setSprite(this.spriteSet);
            return particle;
        }
    }
}
