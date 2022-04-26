package com.slomaxonical.forbidden_arcanus.mixin;

import com.slomaxonical.forbidden_arcanus.common.entity.item.FACustomEntityItem;
import com.slomaxonical.forbidden_arcanus.common.item.DarkMatterItem;
import com.slomaxonical.forbidden_arcanus.common.item.PurifyingSoapItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ServerWorld.class)
public class ServerWorldFACustomEntityMixin {
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
            if (item instanceof FACustomEntityItem) {
                ServerWorld self = (ServerWorld) (Object) this;
                entity = ((FACustomEntityItem) item).replaceItemEntity(self, itemEntity, stack);
            }
        }
            return entity;
    }
}
