package com.slomaxonical.forbidden_arcanus.mixin;

import com.slomaxonical.forbidden_arcanus.common.entity.DarkMatterItemEntity;
import com.slomaxonical.forbidden_arcanus.common.item.DarkMatterItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ServerWorld.class)
public class ServerWorldDarkMatterItemEntityMixin {
    @SuppressWarnings("ConstantConditions")
    @ModifyVariable(method = { "addEntity" }, at = @At("HEAD"), argsOnly = true)
    public Entity onSpawnEntity(Entity entity) {
        if (entity instanceof ItemEntity) {
            ItemEntity itemEntity = (ItemEntity) entity;
            ItemStack stack = itemEntity.getStack();
            if (stack == null) {
                return entity;
            }
            Item item = stack.getItem();
            if (item instanceof DarkMatterItem) {
                ServerWorld self = (ServerWorld) (Object) this;
                entity = ((DarkMatterItem) item).replaceItemEntity(self, itemEntity, stack);
                if (entity != itemEntity) {
                    // Item may actually want to keep the original
                    itemEntity.remove(Entity.RemovalReason.DISCARDED);
                }
            }
        }
        return entity;
    }

}
