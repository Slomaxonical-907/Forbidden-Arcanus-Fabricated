package com.slomaxonical.forbidden_arcanus.core.systems.block;

import com.slomaxonical.forbidden_arcanus.core.systems.blockEntity.SimpleBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class SimpleBlock<T extends BlockEntity> extends Block implements BlockEntityProvider {
    protected BlockEntityType<T> blockEntityType = null;
    protected BlockEntityTicker<T> ticker = null;
    public SimpleBlock(Settings settings) {
        super(settings);
    }

    public SimpleBlock<T> setTile(BlockEntityType<T> type) {
        this.blockEntityType = type;
        this.ticker = (l, p, s, t) -> ((SimpleBlockEntity)t).tick();
        return this;
    }
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return hasTileEntity(state) ? blockEntityType.instantiate(pos, state) : null;
    }
    public boolean hasTileEntity(BlockState state) {
        return this.blockEntityType != null;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return (BlockEntityTicker<T>) ticker;
    }
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (hasTileEntity(state)) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof SimpleBlockEntity simpleBlockEntity) {
                simpleBlockEntity.onPlace(placer, itemStack);
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }
    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state){
        if (hasTileEntity(state)) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof SimpleBlockEntity simpleBlockEntity) {
                ItemStack stack = simpleBlockEntity.onClone(world, pos, state);
                if (!stack.isEmpty())
                {
                    return stack;
                }
            }
        }
        return super.getPickStack(world, pos, state);
    }
    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        this.spawnBreakParticles(world, player, pos, state);
        if (state.isIn(BlockTags.GUARDED_BY_PIGLINS)) {
            PiglinBrain.onGuardedBlockInteracted(player, false);
        }
        world.emitGameEvent((Entity)player, GameEvent.BLOCK_DESTROY, pos);
    }
//    @Override
//    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
//        onBlockBroken(state, world, pos);
//        super.onDestroyedByExplosion(world, pos, explosion);
//    }
//    public void onBlockBroken(BlockState state, BlockView level, BlockPos pos) {
//        if (hasTileEntity(state)) {
//            BlockEntity blockEntity = level.getBlockEntity(pos);
//            if (blockEntity instanceof SimpleBlockEntity simpleBlockEntity) {
//                simpleBlockEntity.onBreak();
//            }
//        }
//    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (hasTileEntity(state)) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof SimpleBlockEntity simpleBlockEntity) {
                return simpleBlockEntity.onUse(player, hand);
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
