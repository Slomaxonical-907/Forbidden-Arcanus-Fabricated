package com.slomaxonical.forbidden_arcanus.client.renderer.block;

import com.slomaxonical.forbidden_arcanus.common.block.util.FABlockProperties;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.PedestalBlockEntity;
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
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class PedestalRenderer implements BlockEntityRenderer<PedestalBlockEntity> {
    public PedestalRenderer(BlockEntityRendererFactory.Context context) {}
    @Override
    public void render(PedestalBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemStack stack = blockEntity.getStack();
        World world = MinecraftClient.getInstance().world;
        if (!stack.isEmpty()) {
            matrices.push();

            matrices.translate(0.5D, blockEntity.getItemHeight() / 100.0F, 0.5D);
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion((world.getTime() + tickDelta) * 3));

            matrices.scale(0.5F, 0.5F, 0.5F);

            MinecraftClient.getInstance().getItemRenderer().renderItem(stack,  ModelTransformation.Mode.FIXED, light, overlay, matrices, vertexConsumers, 0);

            matrices.pop();
        }
    }

}
