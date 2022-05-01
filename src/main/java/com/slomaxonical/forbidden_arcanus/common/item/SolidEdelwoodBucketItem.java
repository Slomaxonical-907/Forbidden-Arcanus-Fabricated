package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.common.item.util.CapacityBucket;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidDrainable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PowderSnowBucketItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.valhelsia.valhelsia_core.common.util.ItemStackUtils;

public class SolidEdelwoodBucketItem extends PowderSnowBucketItem implements CapacityBucket {
    public SolidEdelwoodBucketItem(Block block, SoundEvent placeSound, Settings settings) {
        super(block,placeSound,settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        ItemStack stack = context.getStack().copy();
        BlockState state = world.getBlockState(pos);

        if (state.isOf(this.getBlock()) && state.getBlock() instanceof FluidDrainable bucketPickup && !this.isFull(stack)) {
            if (player != null) {
                player.setStackInHand(context.getHand(), this.tryFill(stack).getSecond());

                bucketPickup.tryDrainFluid(world, pos, state);
                bucketPickup.getBucketFillSound().ifPresent((event) -> player.playSound(event, 1.0F, 1.0F));

                if (!world.isClient()) {
                    Criteria.FILLED_BUCKET.trigger((ServerPlayerEntity) player, stack);
                }
            }
            world.emitGameEvent(player, GameEvent.FLUID_PICKUP, pos);

            return ActionResult.success(world.isClient());
        }

        ActionResult result = super.useOnBlock(context);

        if (result.isAccepted() && player != null && !player.isCreative()) {
            player.setStackInHand(context.getHand(), this.tryDrain(stack));
        }

        return result;
    }

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public ItemStack getEmptyBucket() {
        return null;
    }

    @Override
    public boolean hasRecipeRemainder() {
        return true;
    }
//    public ItemStack getRemainderItem(ItemStack stack) {
//        return ItemStackUtils.transferEnchantments(stack, new ItemStack(ModItems.EDELWOOD_BUCKET.get()));
//    }
}
