package com.slomaxonical.forbidden_arcanus.common.blockEntity;

import com.slomaxonical.forbidden_arcanus.core.helper.FAHelper;
import com.slomaxonical.forbidden_arcanus.core.helper.FATags;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlackHoleBlockEntity extends BlockEntity {
    private static final double DAMAGE_DISTANCE = 0.6D;
    private static final int PLAYER_SEARCH_DISTANCE = 6;

    private final List<ItemEntity> thrownOutItems = new ArrayList<>();

    private double stored_xp;
    public int rotation = 0;
    public int tickCounter;
    public int auraTexture = 0;
    
    public BlackHoleBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public BlackHoleBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.BLACK_HOLE, pos, state);
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, BlackHoleBlockEntity blockEntity) {
        blockEntity.rotation++;
        blockEntity.tickCounter++;

        if (blockEntity.tickCounter == 5 || blockEntity.tickCounter == 10) {
            blockEntity.auraTexture++;
        } else if (blockEntity.tickCounter == 15) {
            blockEntity.tickCounter = 0;
            blockEntity.auraTexture = 0;
        }
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, BlackHoleBlockEntity blockEntity) {
        List<Entity> entities = world.getOtherEntities(null, new Box(pos.getX() + 0.5 - 5, pos.getY() + 0.5 - 5, pos.getZ() + 0.5 - 5, pos.getX() + 0.5 + 5, pos.getY() + 0.5 + 5, pos.getZ() + 0.5 + 5));

        for (Entity entity : entities) {
            if (!entity.getType().isIn(FATags.EntityTypes.BLACK_HOLE_AFFECTED)) {
                continue;
            }

            if (entity instanceof ItemEntity itemEntity && !blockEntity.isAffectedItem(itemEntity)) {
                continue;
            }

            double distance = entity.getPos().distanceTo(FAHelper.blockPosToVector(pos, 0.5));
            double movementFactor = blockEntity.getMovementFactor(distance);

            entity.setVelocity((pos.getX() + 0.5 - entity.getX()) * movementFactor, (pos.getY() + 0.5 - entity.getY() + 1.25) * movementFactor, (pos.getZ() + 0.5 - entity.getZ()) * movementFactor);

            if (distance <= DAMAGE_DISTANCE) {
                if (entity instanceof ExperienceOrbEntity experienceOrb) {
                    blockEntity.stored_xp += experienceOrb.getExperienceAmount();

                    if (blockEntity.stored_xp >= 60) {
                        blockEntity.throwOutItemStack(world, new ItemStack(ItemRegistry.XPETRIFIED_ORB), pos);
                        blockEntity.stored_xp = 0;
                    }
                    experienceOrb.kill();
                } else {
                    entity.damage(DamageSource.MAGIC, 4);
                }
            }
        }

        blockEntity.thrownOutItems.removeIf(itemEntity -> !itemEntity.isAlive());
    }

    public boolean isAffectedItem(ItemEntity entity) {
        return !this.thrownOutItems.contains(entity) && !entity.getStack().isIn(FATags.Items.BLACK_HOLE_UNAFFECTED);
    }

    private void throwOutItemStack(World world, ItemStack stack, BlockPos pos) {
        pos = pos.add(0.5D, 0.5D, 0.5D);

        ItemEntity item = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
        PlayerEntity nearestPlayer = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), PLAYER_SEARCH_DISTANCE, false);

        if (nearestPlayer == null) {
            this.setRandomVelocity(item, world.getRandom());
        } else {
            item.addVelocity((nearestPlayer.getX() - item.getX()) * 0.09, (nearestPlayer.getY() - item.getY() + 1.25) * 0.09, (nearestPlayer.getZ() - item.getZ()) * 0.09);
        }

        this.thrownOutItems.add(item);

        world.spawnEntity(item);
    }

    private double getMovementFactor(double distance) {
        return distance <= 3 ? 0.035 : 0.02;
    }

    private void setRandomVelocity(ItemEntity itemEntity, Random random) {
        double x = random.nextDouble();
        double y = random.nextDouble();
        double z = random.nextDouble();

        itemEntity.setVelocity(random.nextBoolean() ? x : -x, random.nextBoolean() ? y : -y, random.nextBoolean() ? z : -z);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.stored_xp = nbt.getDouble("StoredXP");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putDouble("StoredXP", this.stored_xp);
    }
}
