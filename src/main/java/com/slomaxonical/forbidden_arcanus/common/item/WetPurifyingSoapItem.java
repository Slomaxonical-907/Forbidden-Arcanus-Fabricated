package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.common.entity.item.FACustomEntityItem;
import com.slomaxonical.forbidden_arcanus.common.entity.item.WetSoapItemEntity;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.common.capability.counter.SimpleCounter;
import net.valhelsia.valhelsia_core.common.util.ItemStackUtils;

public class WetPurifyingSoapItem extends Item implements FACustomEntityItem {
    private static final SimpleCounter TIMER = new SimpleCounter(new Identifier(ForbiddenArcanus.ID, "dry_timer_inv"));
    private static final double CONSUME_CHANCE = 0.65D;

    public WetPurifyingSoapItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!TIMER.isActive()) {
            TIMER.setActive(true);
            System.out.println("TimerActivated");
        }
        if (entity instanceof PlayerEntity player) {
            if (inUltraWarm(player)) {
                player.getInventory().setStack(slot, new ItemStack(ItemRegistry.PURIFYING_SOAP));
            }
                if (player.isWet()) {
                    TIMER.resetTimer();
                } else {
                    TIMER.increase();

                    if (TIMER.getValue() >= 3600) {
                        player.getInventory().setStack(slot, new ItemStack(ItemRegistry.PURIFYING_SOAP));
                    }
                }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

//        AurealHelper.getCapability(player).decreaseCorruption(20);

        if (!world.isClient()) {
            player.clearStatusEffects();

            if (world.getRandom().nextDouble() < CONSUME_CHANCE) {
                ItemStackUtils.shrinkStack(player, stack);
            }
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));

        return TypedActionResult.success(stack, world.isClient());
    }

    @Override
    public Entity replaceItemEntity(ServerWorld world, ItemEntity itemEntity, ItemStack itemStack) {
        final WetSoapItemEntity wse = new WetSoapItemEntity(world, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), itemStack);

        wse.setVelocity(itemEntity.getVelocity());
        wse.setPickupDelay(40);
        return wse;
    }
    public static boolean inUltraWarm(Entity entity) {
        return entity.getEntityWorld().getDimension().isUltrawarm();
    }
}
