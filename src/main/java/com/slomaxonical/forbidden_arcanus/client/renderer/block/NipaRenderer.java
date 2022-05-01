package com.slomaxonical.forbidden_arcanus.client.renderer.block;

import com.slomaxonical.forbidden_arcanus.common.block.util.FABlockProperties;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.NipaBlockEntity;
import com.slomaxonical.forbidden_arcanus.core.registries.item.ItemRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class NipaRenderer implements BlockEntityRenderer<NipaBlockEntity> {
    public NipaRenderer(BlockEntityRendererFactory.Context context) {}
    @Override
    public void render(NipaBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (blockEntity.getCachedState().get(FABlockProperties.SPECK)) {
            matrices.push();

            matrices.translate(0.5, blockEntity.getSpeckHeight(), 0.5);
            matrices.scale(0.75F, 0.75F, 0.75F);

            matrices.multiply(MinecraftClient.getInstance().getEntityRenderDispatcher().getRotation());

            MinecraftClient.getInstance().getItemRenderer().renderItem(new ItemStack(ItemRegistry.ARCANE_CRYSTAL_DUST_SPECK), ModelTransformation.Mode.FIXED, light, overlay, matrices, vertexConsumers, 0);

            matrices.pop();
        }
    }
}
