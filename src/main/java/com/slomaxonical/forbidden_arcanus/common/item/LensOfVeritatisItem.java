package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.core.registries.ParticleRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import com.slomaxonical.forbidden_arcanus.mixin.SendToPlayerIfNearbyInvoker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.Random;

public class LensOfVeritatisItem extends Item {

    public static final String LENS_SLOT = "LensSlot";

    public static final int PARTICLE_RANGE = 20;

    public LensOfVeritatisItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof ServerPlayerEntity player && world instanceof ServerWorld serverWorld) {
////            NbtCompound persistentData = player.getPerssssisssstentData();
////
////            int slot = persistentData.getInt(LENS_SLOT);
////
////            if (slot != slotId && player.getInventory().getStack(slot).isOf(ItemRegistry.LENS_OF_VERITATIS)) {
////                return;
////            }
            if (!player.getInventory().getMainHandStack().isOf(ItemRegistry.LENS_OF_VERITATIS)) {
                return;
            }
//
            Random random = world.getRandom();
//
            world.getNonSpectatingEntities(LivingEntity.class, new Box(player.getBlockPos()).expand(PARTICLE_RANGE)).stream()
                    .filter(livingEntity -> livingEntity instanceof PigEntity)
                    .forEach(livingEntity -> {
                        int j = random.nextInt(2) * 2 - 1;
                        int k = random.nextInt(2) * 2 - 1;
                        double posX = livingEntity.getX() + random.nextFloat() * j;
                        double posY = livingEntity.getY() + random.nextFloat();
                        double posZ = livingEntity.getZ() + random.nextFloat() * k;
                        float ySpeed = (random.nextFloat() - 0.4F) * 0.125F;

                        ParticleS2CPacket packet = new ParticleS2CPacket(ParticleRegistry.AUREAL_MOTE, false, posX, posY, posZ, 0.0F, ySpeed, 0.0F, 1.0F, 0);
                        ((SendToPlayerIfNearbyInvoker)serverWorld).invokeSendToPlayerIfNearby(player, false, posX, posY, posZ, packet);
                    });
//
////            persistentData.putInt(LENS_SLOT, slotId);
        }
        super.inventoryTick(stack, world, entity, slotId, isSelected);
    }
}
