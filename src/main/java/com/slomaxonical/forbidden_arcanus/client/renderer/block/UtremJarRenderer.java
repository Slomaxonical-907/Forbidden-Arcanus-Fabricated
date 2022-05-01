package com.slomaxonical.forbidden_arcanus.client.renderer.block;

import com.slomaxonical.forbidden_arcanus.common.blockEntity.UtremJarBlockEntity;
import dev.architectury.fluid.FluidStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Matrix3f;
import net.minecraft.util.math.Matrix4f;

@Environment(EnvType.CLIENT)
public class UtremJarRenderer implements BlockEntityRenderer<UtremJarBlockEntity> {
    public UtremJarRenderer(BlockEntityRendererFactory.Context context) {}
//transfer API....
    @Override
    public void render(UtremJarBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
//        FluidStack fluidStack = blockEntity.getTank().getFluid();
//        int capacity = blockEntity.getTank().getCapacity();
//
//        if (!fluidStack.isEmpty() && capacity > 0) {
//            Matrix4f matrix = matrices.peek().getPositionMatrix();
//            Matrix3f normal = matrices.peek().getNormalMatrix();
//
//            RenderUtils.renderFluid(blockEntity.getTank(), fluidStack, vertexConsumers, matrix, normal, new Box(3.5 / 16.0F, 0.5 / 16.0F, 3.5 / 16.0F, 12.5 / 16.0F, 12.5 / 16.0F, 12.5 / 16.0F), fluidStack.getFluid().getAttributes().getColor(blockEntity.getWorld(), blockEntity.getPos()), light, overlay);
//        }
        matrices.push();
    }
}
