package com.slomaxonical.forbidden_arcanus.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ZombieArmItem extends Item {
    private static final double ATTACK_DAMAGE = 4.0D;
    private static final double ATTACK_SPEED = -2.3F;

    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public ZombieArmItem(Settings settings) {
        super(settings);
        this.attributeModifiers = ImmutableMultimap.<EntityAttribute, EntityAttributeModifier>builder()
                .put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", ATTACK_DAMAGE, EntityAttributeModifier.Operation.ADDITION))
                .put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", ATTACK_SPEED, EntityAttributeModifier.Operation.ADDITION))
                .build();
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.getHealth() != 0.0F) {
            return true;
        }

        World level = attacker.getEntityWorld();
        MobEntity entity = null;

        if (target.getType() == EntityType.HORSE) {
            entity = ((MobEntity) target).convertTo(EntityType.ZOMBIE_HORSE, false);

        } else if (target instanceof VillagerEntity villager) {
            entity = villager.convertTo(EntityType.ZOMBIE_VILLAGER, false);

            if (entity != null) {
                ((ZombieVillagerEntity) entity).setVillagerData(villager.getVillagerData());
                ((ZombieVillagerEntity) entity).setGossipData(villager.getGossip().serialize(NbtOps.INSTANCE).getValue());
                ((ZombieVillagerEntity) entity).setOfferData(villager.getOffers().toNbt());
                ((ZombieVillagerEntity) entity).setXp(villager.getExperience());
            }
        }

        if (level instanceof ServerWorld serverWorld && entity != null) {
            entity.initialize(serverWorld, level.getLocalDifficulty(entity.getBlockPos()), SpawnReason.CONVERSION, null, null);
        }

        return true;
    }
}
