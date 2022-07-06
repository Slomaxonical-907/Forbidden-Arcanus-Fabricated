package com.slomaxonical.forbidden_arcanus.common.networking;

import com.slomaxonical.forbidden_arcanus.common.blockEntity.PedestalBlockEntity;
import com.slomaxonical.forbidden_arcanus.core.helper.FAHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ForbiddenS2CPacketReceiver {

    @Environment(EnvType.CLIENT)
    public static void registerS2CReceivers() {
        ClientPlayNetworking.registerGlobalReceiver(ForbiddenS2CPackets.UPDATE_PEDESTAL_ID,(client, handler, buf, responseSender) -> {
            World world = client.world;
            BlockPos pos = buf.readBlockPos();
            ItemStack stack = buf.readItemStack();
            int height = buf.readInt();

            client.execute(() -> {
                if (world != null && world.getBlockEntity(pos) instanceof PedestalBlockEntity blockEntity) {
                    blockEntity.setStack(stack);
                    blockEntity.setItemHeight(height);
                }
            });
        });
    }
}
