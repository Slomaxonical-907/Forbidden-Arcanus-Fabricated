package com.slomaxonical.forbidden_arcanus.common.blockEntity;

import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.valhelsia.valhelsia_core.common.network.NetworkHandler;

public class PedestalBlockEntity extends BlockEntity {

    private static final int DEFAULT_ITEM_HEIGHT = 120;

    private ItemStack stack = ItemStack.EMPTY;

    private final float hoverStart;
    private int ticksExisted;
    private int itemHeight = DEFAULT_ITEM_HEIGHT;

    public PedestalBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.PEDESTAL, pos, state);
        this.hoverStart = (float) (Math.random() * Math.PI * 2.0D);

    }
    public static void clientTick(World world, BlockPos pos, BlockState state, PedestalBlockEntity blockEntity) {
        blockEntity.ticksExisted++;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    public void setStackAndSync(ItemStack stack, World world, BlockPos pos) {
        this.stack = stack;
//
//        if (!world.isClient()) {
//            NetworkHandler.sentToTrackingChunk(world.getChunk(pos), new UpdatePedestalPacket(pos, stack, this.itemHeight));
//        }
    }

    public ItemStack getStack() {
        return this.stack;
    }

    public boolean hasStack() {
        return !this.stack.isEmpty();
    }

    public void clearStack(World world, BlockPos pos) {
        this.setItemHeight(DEFAULT_ITEM_HEIGHT);

//        this.setStackAndSync(ItemStack.EMPTY, world, pos);
    }

    public float getItemHover(float partialTicks) {
        return (this.ticksExisted + partialTicks) / 20.0F + this.hoverStart;
    }

    public int getItemHeight() {
        return this.itemHeight;
    }

    public void setItemHeight(int itemHeight) {
        this.itemHeight = itemHeight;
    }
}
