package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.core.registries.SoundRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class DracoArcanusScepterItem extends Item {

    private static final int USE_DURATION = 30;
    private static final int COOLDOWN_TICKS = 10;

    public DracoArcanusScepterItem(Settings settings) {
        super(settings);
    }
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity livingEntity) {
        if (livingEntity instanceof PlayerEntity player) {
            if (!world.isClient()) {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegistry.DARK_BOLT_LAUNCH, SoundCategory.NEUTRAL, 1.0f, 1.0f);

//                EnergyBall energyBall = new EnergyBall(world, player, player.getLookAngle().x * 1, player.getLookAngle().y * 1, player.getLookAngle().z * 1);
//                energyBall.setPos(energyBall.getX(), player.getY() + player.getEyeHeight(), energyBall.getZ());
//
//                world.spawnEntity(energyBall);
            }
            player.getItemCooldownManager().set(this, COOLDOWN_TICKS);
        }
        return super.finishUsing(stack, world, livingEntity);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.setCurrentHand(hand);
        return new TypedActionResult<>(ActionResult.success(world.isClient()), player.getStackInHand(hand));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return USE_DURATION;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
}
