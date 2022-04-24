package com.slomaxonical.forbidden_arcanus.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

import javax.annotation.Nonnull;


public class SoulParticle extends SpriteBillboardParticle {

    private final SpriteProvider sprites;

    private SoulParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteProvider spriteSet) {
        super(world, x, y, z, 0.5D - world.getRandom().nextDouble(), ySpeed, 0.5D - world.getRandom().nextDouble());
        this.sprites = spriteSet;
        this.velocityY *= 0.20000000298023224D;
        if (xSpeed == 0.0D && zSpeed == 0.0D) {
            this.velocityX *= 0.10000000149011612D;
            this.velocityZ *= 0.10000000149011612D;
        }

        this.scale *= 1.1F;
        this.maxAge = (int)(9.5D / (world.getRandom().nextFloat() * 0.8D + 0.2D));
        this.collidesWithWorld = false;
        this.setSpriteForAge(spriteSet);
    }

    @Nonnull
//    public ParticleTextureSheet getRenderType() {
//        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
//    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.age++ >= this.maxAge) {
            this.markDead();
        } else {
            this.setSpriteForAge(this.sprites);
            this.velocityY += 0.004D;
            this.move(this.velocityX, this.velocityY, this.velocityZ);
            if (this.y == this.prevPosY) {
                this.velocityX *= 1.1D;
                this.velocityZ *= 1.1D;
            }

            this.velocityX *= 0.9599999785423279D;
            this.velocityY *= 0.9599999785423279D;
            this.velocityZ *= 0.9599999785423279D;
            if (this.onGround) {
                this.velocityX *= 0.699999988079071D;
                this.velocityZ *= 0.699999988079071D;
            }

        }
    }

    public record Factory(SpriteProvider spriteSet) implements ParticleFactory<DefaultParticleType> {

        @Override
        public Particle createParticle(@Nonnull DefaultParticleType type, @Nonnull ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new SoulParticle(world, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}
