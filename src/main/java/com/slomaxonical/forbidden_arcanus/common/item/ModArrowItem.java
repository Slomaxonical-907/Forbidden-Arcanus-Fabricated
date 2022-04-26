package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.common.entity.projectile.BoomArrowEntity;
import com.slomaxonical.forbidden_arcanus.common.entity.projectile.DracoArcanusArrowEntity;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModArrowItem extends ArrowItem {
    public ModArrowItem(Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        if (this == ItemRegistry.BOOM_ARROW) {
            return new BoomArrowEntity(world, shooter);
        } else if (this == ItemRegistry.DRACO_ARCANUS_ARROW) {
            return new DracoArcanusArrowEntity(world, shooter);
        }
        return new ArrowEntity(world, shooter);
    }
}
