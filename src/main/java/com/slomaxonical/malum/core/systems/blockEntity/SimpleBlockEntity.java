package com.slomaxonical.malum.core.systems.blockEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;


public class SimpleBlockEntity extends BlockEntity {
    public SimpleBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public void onBreak() {
//        invalidateCaps(); whatis diss
    }
    public void onPlace(LivingEntity placer, ItemStack stack) {}
    public ItemStack onClone(BlockView level, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }
    public ActionResult onUse(PlayerEntity player, Hand hand) {
        return ActionResult.PASS;
    }
    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return new NbtCompound();
    }
    @Override
    public void readNbt(NbtCompound nbt) {
    }
    @Override
    protected void writeNbt(NbtCompound nbt) {
    }
    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this); // (this.worldPosition, 3, this.getUpdateTag());
    }
//    @Override
//    public void onDataPacket(Connection net, BlockEntityUpdateS2CPacket pkt) {
//        super.onDataPacket(net, pkt);
//        readNbt(pkt.getNbt());
//    }
    public void tick(){}
}
