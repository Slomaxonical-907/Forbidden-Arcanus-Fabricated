package com.slomaxonical.forbidden_arcanus.mixin;

import net.minecraft.network.Packet;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ServerWorld.class) public interface SendToPlayerIfNearbyInvoker {
    @Invoker("sendToPlayerIfNearby")
    public  boolean invokeSendToPlayerIfNearby(ServerPlayerEntity player, boolean force, double x, double y, double z, Packet<?> packet);
}
