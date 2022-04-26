package com.slomaxonical.forbidden_arcanus.mixin;

import com.mojang.datafixers.util.Pair;
import com.slomaxonical.forbidden_arcanus.client.renderer.block.ObsidianSkullRenderer;
import com.slomaxonical.forbidden_arcanus.core.helper.FATags;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.render.entity.model.SkullEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeadFeatureRenderer.class)
public class HeadFeatureRendererMixin<T extends LivingEntity, M extends EntityModel<T> & ModelWithHead> {

    private EntityModelLoader modelSet;
    private Pair<SkullEntityModel, SkullEntityModel> models;

    @Inject(method = "<init>(Lnet/minecraft/client/render/entity/feature/FeatureRendererContext;Lnet/minecraft/client/render/entity/model/EntityModelLoader;FFF)V", at = @At(value = "TAIL"))
    private void forbiddenArcanus_init(FeatureRendererContext<T, M> renderLayerParent, EntityModelLoader modelSet, float scaleX, float scaleY, float scaleZ, CallbackInfo ci) {
        this.modelSet = modelSet;
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/feature/HeadFeatureRenderer;translate(Lnet/minecraft/client/util/math/MatrixStack;Z)V", shift = At.Shift.BEFORE), method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V", cancellable = true)
    private void forbiddenArcanus_renderHeads(MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        ItemStack stack = entity.getEquippedStack(EquipmentSlot.HEAD);

        if (this.models == null) {
            this.models = ObsidianSkullRenderer.createModels(modelSet);
        }

        if (stack.getItem() instanceof BlockItem blockItem && stack.isIn(FATags.Items.OBSIDIAN_SKULLS)) {
            poseStack.scale(1.1875F, -1.1875F, -1.1875F);

            if (entity instanceof VillagerEntity || entity instanceof ZombieVillagerEntity) {
                poseStack.translate(0.0D, 0.0625D, 0.0D);
            }

            poseStack.translate(-0.5D, 0.0D, -0.5D);
            ObsidianSkullRenderer.render(null, 180.0F, poseStack, bufferSource, packedLight, this.models, blockItem.getBlock());

            poseStack.pop();
            ci.cancel();
        }
    }
}
