package com.slomaxonical.forbidden_arcanus.common.item;

import com.google.common.collect.ImmutableMap;
import com.slomaxonical.forbidden_arcanus.core.helper.FAUtils;
import com.slomaxonical.forbidden_arcanus.core.registries.EnchantmentRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.FluidFillable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Map;

public class EdelwoodBucketItem extends BucketItem implements CapacityBucket{

    public static final Map<Item, Item> ITEM_TO_BUCKET = new ImmutableMap.Builder<Item, Item>()
            .put(Items.WATER_BUCKET, ItemRegistry.EDELWOOD_WATER_BUCKET)
            .put(Items.LAVA_BUCKET, ItemRegistry.EDELWOOD_LAVA_BUCKET)
            .put(Items.MILK_BUCKET, ItemRegistry.EDELWOOD_MILK_BUCKET)
            .put(Items.POWDER_SNOW_BUCKET, ItemRegistry.EDELWOOD_POWDER_SNOW_BUCKET)
            .build();
    private static final double BURN_CHANCE = 0.005;

    private final int capacity;
    public final Fluid fluid;

    public EdelwoodBucketItem(Fluid fluid, Settings properties) {
        this(fluid, 0, properties);
    }

    public EdelwoodBucketItem( Fluid fluid, int capacity, Settings properties) {
        super(fluid, properties);
        this.capacity = capacity;
        this.fluid = fluid;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (this.shouldBurn(stack, world, entity)) {
            if (entity instanceof PlayerEntity player) {
                player.getInventory().setStack(slot, new ItemStack(Items.CHARCOAL));
            }
            BlockPos pos = entity.getBlockPos();

            if (stack.isOf(ItemRegistry.EDELWOOD_LAVA_BUCKET)) {
                world.setBlockState(pos, this.fluid.getDefaultState().getBlockState());
            } else if (stack.getItem() instanceof EdelwoodMobBucketItem mobBucket) {
                mobBucket.spawn((ServerWorld) world, stack, pos);
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }
    private boolean shouldBurn(ItemStack stack, World world, Entity entity) {
        if (world.isClient() || !this.canBurn(stack) || EnchantmentHelper.getLevel(EnchantmentRegistry.PERMAFROST, stack) != 0) {
            return false;
        }

        if (entity instanceof PlayerEntity player && player.getAbilities().creativeMode) {
            return false;
        }

        return world.getRandom().nextDouble() < BURN_CHANCE;
    }
    protected boolean canBurn(ItemStack stack) {
        return this.fluid.isIn(FluidTags.LAVA);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        BlockHitResult hitResult = raycast(world, player, this.fluid == Fluids.EMPTY ? RaycastContext.FluidHandling.SOURCE_ONLY : RaycastContext.FluidHandling.NONE);
//        TypedActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(player, world, stack, hitResult);
//
//        if (ret != null) {
//            return ret;
//        }

        if (hitResult.getType() != HitResult.Type.BLOCK) {
            return TypedActionResult.pass(stack);
        }

        BlockPos pos = hitResult.getBlockPos();
        Direction direction = hitResult.getSide();
        BlockPos relativePos = pos.offset(direction);

        if (!world.canPlayerModifyAt(player, pos) || !player.canPlaceOn(relativePos, direction, stack)) {
            return TypedActionResult.fail(stack);

        }

        BlockState state = world.getBlockState(pos);

        boolean flag = this.fluid == world.getBlockState(relativePos).getFluidState().getFluid();
        boolean isEmptyFluid = this.fluid.matchesType(Fluids.EMPTY);

        if (isEmptyFluid || (flag && !this.isFull(stack))) {
            if (!isEmptyFluid) {
                state = world.getBlockState(relativePos);
                pos = relativePos;
            }

            if (state.getBlock() instanceof FluidDrainable bucketPickup) {
                ItemStack filledBucket = bucketPickup.tryDrainFluid(world, pos, state);

                if (filledBucket.isEmpty() || !ITEM_TO_BUCKET.containsKey(filledBucket.getItem())) {
                    return this.cancelFluidPickup(pos, state, world, stack);
                }

                ItemStack bucket = new ItemStack(ITEM_TO_BUCKET.get(filledBucket.getItem()));
                filledBucket = stack.isOf(bucket.getItem()) ? stack.copy() : FAUtils.transferEnchantments(stack, bucket);


                if (!isEmptyFluid && !this.tryFill(filledBucket).getFirst()) {
                    return this.cancelFluidPickup(pos, state, world, stack);
                }

                filledBucket = ItemUsage.exchangeStack(stack, player, filledBucket);

                player.incrementStat(Stats.USED.getOrCreateStat(this));
                bucketPickup.getBucketFillSound().ifPresent((event) -> player.playSound(event, 1.0F, 1.0F));
                world.emitGameEvent(player, GameEvent.FLUID_PICKUP, pos);

                if (!world.isClient()) {
                    Criteria.FILLED_BUCKET.trigger((ServerPlayerEntity) player, filledBucket);
                }

                return TypedActionResult.success(filledBucket, world.isClient());
            }
        } else {
            pos = this.canBlockContainFluid(world, pos, state) ? pos : relativePos;

            if (this.placeFluid(player, world, pos, hitResult)) {
                this.onEmptied(player, world, stack, pos);

                if (player instanceof ServerPlayerEntity) {
                    Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity) player, pos, stack);
                }

                player.incrementStat(Stats.USED.getOrCreateStat(this));

                return TypedActionResult.success(this.drainBucket(stack, player), world.isClient());
            }
        }
        return TypedActionResult.fail(stack);
    }
    private TypedActionResult<ItemStack> cancelFluidPickup(BlockPos pos, BlockState state, World world, ItemStack stack) {
        world.setBlockState(pos, state, 11);

        return TypedActionResult.fail(stack);
    }
    private ItemStack drainBucket(ItemStack stack, PlayerEntity player) {
        if (player.getAbilities().creativeMode) {
            return stack;
        }
        if (this instanceof EdelwoodMobBucketItem item) {
            stack = this.transferFullness(stack, item.getFluidBucket());
        }

        stack = ((CapacityBucket) stack.getItem()).tryDrain(stack);

        return stack;
    }
    private boolean canBlockContainFluid(World world, BlockPos pos, BlockState state) {
        return state.getBlock() instanceof FluidFillable liquidBlockContainer && liquidBlockContainer.canFillWithFluid(world, pos, state, this.fluid);
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public ItemStack getEmptyBucket() {
        return new ItemStack(ItemRegistry.EDELWOOD_BUCKET);
    }

}
