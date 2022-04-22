package com.slomaxonical.forbidden_arcanus.common.entity.projectile;

import com.slomaxonical.forbidden_arcanus.core.registries.EntityRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
public class EnergyBallEntity extends ProjectileEntity {

    private static final float MOTION_FACTOR = 0.95F;
    private static final float MOTION_FACTOR_WATER = 0.8F;
    private static final float ROTATION_SPEED = 0.2F;
    private static final float DAMAGE_AMOUNT = 5.5F;

    private LivingEntity shootingEntity;
    private int ticksAlive;
    private double accelerationX;
    private double accelerationY;
    private double accelerationZ;

    public EnergyBallEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(EntityRegistry.ENERGY_BALL, world);
        this.shootingEntity = shooter;
        this.refreshPositionAndAngles(shooter.getX(), shooter.getY(), shooter.getZ(), shooter.prevYaw, shooter.prevPitch);
        this.setPos(this.getX(), this.getY(), this.getZ());
        this.setVelocity(Vec3d.ZERO);

        double d0 = MathHelper.sqrt((float) (accelX * accelX + accelY * accelY + accelZ * accelZ));

        this.accelerationX = accelX / d0 * 0.1D;
        this.accelerationY = accelY / d0 * 0.1D;
        this.accelerationZ = accelZ / d0 * 0.1D;
    }

    public EnergyBallEntity(EntityType<? extends EnergyBallEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    public boolean shouldRender(double distance) {
        double d0 = this.getBoundingBox().getAverageSideLength() * 4.0D;
        if (Double.isNaN(d0)) {
            d0 = 4.0D;
        }

        d0 = d0 * 64.0D;
        return distance < d0 * d0;
    }

    @Override
    public void tick() {
        super.tick();

        HitResult hitResult = ProjectileUtil.getCollision(this, entity -> entity.isAlive() && entity != this.shootingEntity);
//        if (hitResult.getType() != HitResult.Type.MISS && !ForgeEventFactory.onProjectileImpact(this, hitResult)) {
//            this.onImpact(hitResult);
//        }

        Vec3d vec3 = this.getVelocity();
        this.setPos(getX() + vec3.x, getY() + (vec3.y - 0.01), getZ() + vec3.z);
        ProjectileUtil.setRotationFromVelocity(this, ROTATION_SPEED);

        float motionFactor = MOTION_FACTOR;

        if (this.isTouchingWater()) {
            for (int i = 0; i < 4; ++i) {
                this.world.addParticle(ParticleTypes.BUBBLE, this.getX() - vec3.x * 0.25D, this.getY() - vec3.y * 0.25D, this.getZ() - vec3.z * 0.25D, vec3.x, vec3.y, vec3.z);
            }
            motionFactor = MOTION_FACTOR_WATER;
        }
        this.setVelocity(vec3.add(this.accelerationX, this.accelerationY, this.accelerationZ).multiply(motionFactor));
        this.setPos(this.getX(), this.getY(), this.getZ());
    }

    public void onImpact(HitResult result) {
        if (this.world.isClient()) {
            return;
        }

        if (result instanceof EntityHitResult entityHitResult) {
            Entity entity = entityHitResult.getEntity();

            entity.damage(DamageSource.magic(this, this.shootingEntity), DAMAGE_AMOUNT);

            LightningEntity lightningBolt = new LightningEntity(EntityType.LIGHTNING_BOLT, this.world);
            lightningBolt.setPos(entity.getX(), entity.getY(), entity.getZ());

            this.world.spawnEntity(lightningBolt);
        } else if (result.getType() == HitResult.Type.BLOCK) {
            this.world.playSound(null, new BlockPos(result.getPos()), SoundRegistry.DARK_BOLT_HIT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        }

        this.discard();
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        Vec3d vec3 = this.getVelocity();
        nbt.put("direction", this.toNbtList(vec3.x, vec3.y, vec3.z));
        nbt.put("power", this.toNbtList(this.accelerationX, this.accelerationY, this.accelerationZ));
        nbt.putInt("life", this.ticksAlive);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.contains("power", 9)) {
            NbtList list = nbt.getList("power", 6);

            if (list.size() == 3) {
                this.accelerationX = list.getDouble(0);
                this.accelerationY = list.getDouble(1);
                this.accelerationZ = list.getDouble(2);
            }
        }

        this.ticksAlive = nbt.getInt("life");
        if (nbt.contains("direction", 9) && nbt.getList("direction", 6).size() == 3) {
            NbtList list = nbt.getList("direction", 6);
            this.setVelocity(list.getDouble(0), list.getDouble(1), list.getDouble(2));
        } else {
            this.discard();
        }
    }

    @Override
    public boolean collides() {
        return true;
    }

    @Override
    public float getTargetingMargin() {
        return 1.0F;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        }
        this.scheduleVelocityUpdate();

        if (source.getAttacker() != null) {
            Vec3d vec3 = source.getAttacker().getRotationVector();

            this.setVelocity(vec3);
            this.accelerationX = vec3.x * 0.1D;
            this.accelerationY = vec3.y * 0.1D;
            this.accelerationZ = vec3.z * 0.1D;

            if (source.getAttacker() instanceof LivingEntity livingEntity) {
                this.shootingEntity = livingEntity;
            }

            return true;
        }
        return false;
    }

    @Override
    public float getBrightnessAtEyes() {
        return 1.0F;
    }

}
