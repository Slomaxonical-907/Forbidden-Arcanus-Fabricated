package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.common.block.ArcaneCrystalObeliskBlock;
import com.slomaxonical.forbidden_arcanus.common.block.util.FABlockProperties;
import com.slomaxonical.forbidden_arcanus.common.block.util.ObeliskPart;
import com.slomaxonical.forbidden_arcanus.common.entity.CrimsonLightningBoltEntity;
import com.slomaxonical.forbidden_arcanus.core.config.ItemConfig;
import com.slomaxonical.forbidden_arcanus.core.registries.EntityRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.common.util.ItemStackUtils;

import static com.slomaxonical.forbidden_arcanus.common.block.util.ModBlockPatterns.*;

public class MundabiturDustItem extends Item {
    public MundabiturDustItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        PlayerEntity player = context.getPlayer();

        if (this.tryTransformBlock(state, world, pos, player)) {
            ItemStackUtils.shrinkStack(player, context.getStack());

            return ActionResult.success(world.isClient());
        }

        return super.useOnBlock(context);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
        if (target instanceof CreeperEntity && ItemConfig.MUNDABITUR_DUST_CHARGE_CREEPER.get()) {
            DataTracker dataManager = target.getDataTracker();

            if (!dataManager.get(CreeperEntity.CHARGED)) {
                dataManager.set(CreeperEntity.CHARGED, true);

                ItemStackUtils.shrinkStack(player, stack);

                return ActionResult.success(player.getEntityWorld().isClient());
            }
        }
        return ActionResult.PASS;
    }
    private boolean tryTransformBlock(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (state.isOf(Blocks.SMITHING_TABLE)) {
            BlockPattern.Result patternHelper = HEPHAESTUS_PATTERN.searchAround(world, pos);

            if (patternHelper == null || patternHelper.getUp() != Direction.UP) {
                return false;
            }

            world.syncWorldEvent(player, 2001, pos, Block.getRawIdFromState(world.getBlockState(pos)));
            world.setBlockState(pos, BlockRegistry.HEPHAESTUS_FORGE.getDefaultState().with(FABlockProperties.ACTIVATED, true));

            CrimsonLightningBoltEntity entity = new CrimsonLightningBoltEntity(EntityRegistry.CRIMSON_LIGHTNING_BOLT, world);
            entity.setPos(pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D);
            entity.setCosmetic(true);

            world.spawnEntity(entity);

            return true;
        } else if (state.isOf(BlockRegistry.ARCANE_CRYSTAL_BLOCK) || state.isOf(BlockRegistry.ARCANE_POLISHED_DARKSTONE)) {
            BlockPattern.Result patternHelper = ARCANE_CRYSTAL_OBELISK_PATTERN.searchAround(world, pos);

            if (patternHelper == null || patternHelper.getUp() != Direction.UP) {
                return false;
            }

            for (int i = 0; i < ARCANE_CRYSTAL_OBELISK_PATTERN.getWidth(); i++) {
                for (int j = 0; j < ARCANE_CRYSTAL_OBELISK_PATTERN.getHeight(); j++) {
                    CachedBlockPosition cachedBlockInfo = patternHelper.translate(i, j, 0);
                    world.setBlockState(cachedBlockInfo.getBlockPos(), Blocks.AIR.getDefaultState(), 2);
                    world.syncWorldEvent(2001, cachedBlockInfo.getBlockPos(), Block.getRawIdFromState(cachedBlockInfo.getBlockState()));
                }
            }

            BlockState obelisk = BlockRegistry.ARCANE_CRYSTAL_OBELISK.getDefaultState();
            BlockPos topPos = patternHelper.getFrontTopLeft();

            world.setBlockState(topPos.down(2), obelisk.with(ArcaneCrystalObeliskBlock.PART, ObeliskPart.LOWER).with(FABlockProperties.RITUAL, ((ArcaneCrystalObeliskBlock) obelisk.getBlock()).isArcaneChiseledPolishedDarkstoneBelow(world, topPos.down(2))), 2);
            world.setBlockState(topPos.down(1), obelisk.with(ArcaneCrystalObeliskBlock.PART, ObeliskPart.MIDDLE), 2);
            world.setBlockState(topPos, obelisk.with(ArcaneCrystalObeliskBlock.PART, ObeliskPart.UPPER), 2);

            return true;
        }

        return false;
    }
}
