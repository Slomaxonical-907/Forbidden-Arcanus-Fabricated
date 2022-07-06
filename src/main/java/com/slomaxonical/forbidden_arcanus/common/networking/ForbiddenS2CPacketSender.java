package com.slomaxonical.forbidden_arcanus.common.networking;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class ForbiddenS2CPacketSender {

    public static void send(ServerWorld world, BlockPos pos, ItemStack stack, int height) {
        PacketByteBuf buf = PacketByteBufs.create();

        buf.writeBlockPos(pos);
        buf.writeItemStack(stack);
        buf.writeInt(height);

        for (ServerPlayerEntity player : PlayerLookup.tracking(world,pos)) {
            ServerPlayNetworking.send(player, ForbiddenS2CPackets.UPDATE_PEDESTAL_ID, buf);
        }
    }
}
