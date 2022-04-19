package com.slomaxonical.forbidden_arcanus.mixin;

import net.minecraft.entity.Bucketable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nonnull;

/**
 * Slime Mixin <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.core.mixin.SlimeMixin
 *
 * @author stal111
 * @version 1.18.1 - 2.0.1
 * @since 2022-01-16
 */
@Mixin(SlimeEntity.class)
public abstract class SlimeMixin extends MobEntity implements Bucketable {

    @Shadow public abstract int getSize();

    @Shadow protected abstract void setSize(int pSize, boolean pResetHealth);

    private static final TrackedData<Boolean> FROM_BUCKET = DataTracker.registerData(SlimeEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected SlimeMixin(EntityType<? extends MobEntity> entityType, World level) {
        super(entityType, level);
    }

    @Inject(at = @At(value = "RETURN"), method = "initDataTracker")
    private void forbiddenArcanus_defineSynchedData(CallbackInfo ci) {
        this.getDataTracker().startTracking(FROM_BUCKET, false);
    }

    @Inject(at = @At(value = "RETURN"), method = "writeCustomDataToNbt")
    private void forbiddenArcanus_addAdditionalSaveData(NbtCompound tag, CallbackInfo ci) {
        tag.putBoolean("FromBucket", this.isFromBucket());
    }

    @Inject(at = @At(value = "RETURN"), method = "readCustomDataFromNbt")
    private void forbiddenArcanus_readAdditionalSaveData(NbtCompound tag, CallbackInfo ci) {
        this.setFromBucket(tag.getBoolean("FromBucket"));
    }

    @Override
    public boolean cannotDespawn() {
        return super.cannotDespawn() || this.isFromBucket();
    }

    @Override
    public boolean isFromBucket() {
        return this.getDataTracker().get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean fromBucket) {
        this.getDataTracker().set(FROM_BUCKET, fromBucket);
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceToClosestPlayer) {
        return !this.isFromBucket() && !this.hasCustomName();
    }

    @Override
    public void copyDataToStack(@Nonnull ItemStack stack) {
        Bucketable.copyDataToStack(this, stack);

        stack.getOrCreateNbt().putInt("Size", this.getSize());
    }

    @Override
    public void copyDataFromNbt(@Nonnull NbtCompound nbt) {
        Bucketable.copyDataFromNbt(this, nbt);

        this.setSize(nbt.getInt("Size"), false);
    }

    @Nonnull
    @Override
    public ItemStack getBucketItem() {
        return null;
    }

    @Nonnull
    @Override
    public SoundEvent getBucketedSound() {
        return null;
    }
}
