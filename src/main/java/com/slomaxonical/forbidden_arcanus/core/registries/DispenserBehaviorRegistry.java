package com.slomaxonical.forbidden_arcanus.core.registries;

import com.slomaxonical.forbidden_arcanus.common.block.dispenser.ArcaneBoneMealDispenseBehavior;
import com.slomaxonical.forbidden_arcanus.common.block.dispenser.SoulDispenseBehavior;
import com.slomaxonical.forbidden_arcanus.common.entity.projectile.BoomArrowEntity;
import com.slomaxonical.forbidden_arcanus.common.entity.projectile.DracoArcanusArrowEntity;
import com.slomaxonical.forbidden_arcanus.common.item.ObsidianSkullItem;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class DispenserBehaviorRegistry {
    public static void registerDispenseBehaviors() {
        DispenserBlock.registerBehavior(ItemRegistry.SOUL, new SoulDispenseBehavior());
        DispenserBlock.registerBehavior(ItemRegistry.OBSIDIAN_SKULL_SHIELD, ArmorItem.DISPENSER_BEHAVIOR);
        DispenserBlock.registerBehavior(ItemRegistry.OBSIDIAN_SKULL, ObsidianSkullItem.getDispenseBehavior());
        DispenserBlock.registerBehavior(ItemRegistry.ETERNAL_OBSIDIAN_SKULL, ObsidianSkullItem.getDispenseBehavior());
        DispenserBlock.registerBehavior(ItemRegistry.BOOM_ARROW, new ProjectileDispenserBehavior() {
            @Override
            protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                PersistentProjectileEntity arrow = new BoomArrowEntity(world, position.getX(), position.getY(), position.getZ());
                arrow.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;

                return arrow;
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.DRACO_ARCANUS_ARROW, new ProjectileDispenserBehavior() {
            @Override
            protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                PersistentProjectileEntity arrow = new DracoArcanusArrowEntity(world, position.getX(), position.getY(), position.getZ());
                arrow.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;

                return arrow;
            }
        });
        DispenserBlock.registerBehavior(ItemRegistry.ARCANE_BONE_MEAL, new ArcaneBoneMealDispenseBehavior());
    }
}
