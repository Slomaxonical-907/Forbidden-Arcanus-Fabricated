package com.slomaxonical.forbidden_arcanus.common.item;

import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EdelwoodMobBucketItem extends EdelwoodBucketItem {
    private final  EntityType<?> entityType;
    private final  SoundEvent emptySound;

    public EdelwoodMobBucketItem(EntityType<?> entityType, Fluid fluid, Settings properties) {
        this(entityType, fluid, null, properties);
    }

    public EdelwoodMobBucketItem(EntityType<?> entityType, Fluid fluid, SoundEvent emptySound, Settings properties) {
        super(fluid, properties);
        this.entityType = entityType;
        this.emptySound = emptySound;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        BlockHitResult hitResult = raycast(world, player, this.fluid == Fluids.EMPTY ? RaycastContext.FluidHandling.SOURCE_ONLY : RaycastContext.FluidHandling.NONE);
//        InteractionResultHolder<ItemStack> ret = ForgeEventFactory.onBucketUse(player, world, stack, hitResult);
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

        if (this.fluid == Fluids.EMPTY) {
            this.onEmptied(player, world, stack, pos);

            return TypedActionResult.success(this.getEmptyBucket(), world.isClient());
        }

        return super.use(world, player, hand);
    }

    @Override
    public void onEmptied(@org.jetbrains.annotations.Nullable PlayerEntity player, World world, ItemStack stack, BlockPos pos) {
        if (world instanceof ServerWorld serverLevel) {
            this.spawn(serverLevel, stack, pos);
            world.emitGameEvent(player, GameEvent.ENTITY_PLACE, pos);
        }
    }

    @Override
    protected void playEmptyingSound(@org.jetbrains.annotations.Nullable PlayerEntity player, WorldAccess world, BlockPos pos) {
        if (this.emptySound != null) {
            world.playSound(player, pos, this.emptySound, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        }
    }

    public void spawn(ServerWorld world, ItemStack stack, BlockPos pos) {
        Entity entity = this.entityType.spawnFromItemStack(world, stack, null, pos, SpawnReason.BUCKET, true, false);

        if (entity instanceof Bucketable bucketable) {
            bucketable.copyDataFromNbt(stack.getOrCreateNbt());
            bucketable.setFromBucket(true);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (this.entityType == EntityType.TROPICAL_FISH) {
            NbtCompound tag = stack.getNbt();

            if (tag != null && tag.contains("BucketVariantTag", 3)) {
                int i = tag.getInt("BucketVariantTag");
                Formatting[] chatFormatting = new Formatting[]{Formatting.ITALIC, Formatting.GRAY};
                String s = "color.minecraft." + TropicalFishEntity.getBaseDyeColor(i);
                String s1 = "color.minecraft." + TropicalFishEntity.getPatternDyeColor(i);

                for(int j = 0; j < TropicalFishEntity.COMMON_VARIANTS.length; ++j) {
                    if (i == TropicalFishEntity.COMMON_VARIANTS[j]) {
                        tooltip.add((new TranslatableText(TropicalFishEntity.getToolTipForVariant(j))).formatted(chatFormatting));
                        return;
                    }
                }

                tooltip.add((new TranslatableText(TropicalFishEntity.getTranslationKey(i))).formatted(chatFormatting));
                MutableText component = new TranslatableText(s);
                if (!s.equals(s1)) {
                    component.append(", ").append(new TranslatableText(s1));
                }

                component.formatted(chatFormatting);
                tooltip.add(component);
            }
        }
    }

    public ItemStack getFluidBucket() {
        if (this.fluid == Fluids.WATER) {
            return new ItemStack(ItemRegistry.EDELWOOD_WATER_BUCKET);
        } else {
            return new ItemStack(ItemRegistry.EDELWOOD_LAVA_BUCKET);
        }
    }

    @Override
    protected boolean canBurn(ItemStack stack) {
        return stack.isOf(ItemRegistry.EDELWOOD_MAGMA_CUBE_BUCKET);
    }
    //todo:figure out the crafting with these(custom type?) also for the dagger
}
