package com.slomaxonical.forbidden_arcanus.client.renderer.block;

import com.slomaxonical.forbidden_arcanus.client.model.MagicCircleModel;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.forge.HephaestusForgeBlockEntity;
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

import javax.annotation.Nonnull;
@Environment(EnvType.CLIENT)
public class HephaestusForgeRenderer implements BlockEntityRenderer<HephaestusForgeBlockEntity> {

    private final MagicCircleModel magicCircleModel;

    public HephaestusForgeRenderer(BlockEntityRendererFactory.Context context) {
        this.magicCircleModel = new MagicCircleModel(context);
    }

    @Override
    public void render(HephaestusForgeBlockEntity blockEntity, float partialTicks, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
//        blockEntity.getMagicCircle().render(matrices, partialTicks, vertexConsumers, light, this.magicCircleModel);
//
//        ItemStack stack = blockEntity.getStack(4);
//
//        if (!stack.isEmpty()) {
            matrices.push();
//
//            matrices.translate(0.5D, 1.3D, 0.5D);
//            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion((blockEntity.getDisplayCounter() + partialTicks) / 20));
//
//            matrices.scale(0.5F, 0.5F, 0.5F);
//
//            MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.FIXED, light, overlay, matrices, vertexConsumers, 0);
//
//            matrices.pop();
//        }
    }

//    @Override
//    public boolean rendersOutsideBoundingBox(@Nonnull HephaestusForgeBlockEntity blockEntity) {
//        return blockEntity.getRitualManager().isRitualActive();
//    }
}
