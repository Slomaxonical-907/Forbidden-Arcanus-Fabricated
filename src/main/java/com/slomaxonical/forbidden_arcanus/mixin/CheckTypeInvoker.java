package com.slomaxonical.forbidden_arcanus.mixin;

import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.network.Packet;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BlockWithEntity.class) public interface CheckTypeInvoker {
    @Invoker("checkType")
    public static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A>    invokeCheckType (BlockEntityType<A> givenType, BlockEntityType<E> expectedType, BlockEntityTicker<? super E> ticker){
        throw new AssertionError();
    };
}
