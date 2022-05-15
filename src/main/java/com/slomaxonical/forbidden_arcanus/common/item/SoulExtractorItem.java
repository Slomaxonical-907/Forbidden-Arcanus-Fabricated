package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.core.registries.ParticleRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class SoulExtractorItem extends Item {
    private static final int USE_DURATION = 35;

    public SoulExtractorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();

        if (player == null || player.isSneaking()) {
            return ActionResult.PASS;
        }

        if (this.isValidBlock(world, pos, player)) {
            player.setCurrentHand(context.getHand());

            return ActionResult.success(world.isClient());
        }
        return super.useOnBlock(context);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity livingEntity) {
        if (!(livingEntity instanceof PlayerEntity player) || player.isSneaking()) {
            return stack;
        }

        BlockPos pos = raycast(world, player, RaycastContext.FluidHandling.SOURCE_ONLY).getBlockPos();

        if (!this.isValidBlock(world, pos, player)) {
            return stack;
        }

        Random random = player.getRandom();

        stack.damage(1, player, (playerEntity) -> playerEntity.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        player.incrementStat(Stats.USED.getOrCreateStat(this));

        for (int i = 0; i < 4; i++) {
            world.addParticle(ParticleRegistry.SOUL, pos.getX() + random.nextFloat(), pos.getY() + 1, pos.getZ() + random.nextFloat(), 1, 1, 1);
        }

        if (!world.isClient()) {
            world.setBlockState(pos, BlockRegistry.SOULLESS_SAND.getDefaultState());
            world.spawnEntity(new ItemEntity(world, pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D, new ItemStack(ItemRegistry.SOUL)));
        }

        return stack;
    }

    private boolean isValidBlock(World world, BlockPos pos, PlayerEntity player) {
        return world.canPlayerModifyAt(player, pos) && world.getBlockState(pos).isOf(Blocks.SOUL_SAND);
    }

    @Override
    public void usageTick(World world, LivingEntity livingEntity, ItemStack stack, int remainingUseTicks) {
        if (!(livingEntity instanceof PlayerEntity player) || player.isSneaking()) {
            livingEntity.stopUsingItem();

            return;
        }

        World level = player.getEntityWorld();
        BlockPos pos = raycast(level, player, RaycastContext.FluidHandling.SOURCE_ONLY).getBlockPos();

        if (this.isValidBlock(level, pos, player) && player.getRandom().nextInt(6) == 1) {
            level.syncWorldEvent(player, 2001, pos, Block.getRawIdFromState(level.getBlockState(pos)));
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return USE_DURATION;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.getAbilities().creativeMode;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("tooltip." + ForbiddenArcanus.ID + ".soul_extractor").formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
