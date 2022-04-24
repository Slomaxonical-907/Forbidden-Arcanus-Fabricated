package com.slomaxonical.forbidden_arcanus.client.renderer.block;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.BlackHoleBlockEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;


/**
 * Black Hole Renderer <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.client.renderer.block.BlackHoleRenderer
 *
 * @author stal111
 * @version 2.0.0
 */
public class BlackHoleRenderer implements BlockEntityRenderer<BlackHoleBlockEntity> {

    public static final EntityModelLayer BLACK_HOLE_LAYER = new EntityModelLayer(new Identifier(ForbiddenArcanus.MOD_ID, "black_hole"), "main");
    public static final EntityModelLayer BLACK_HOLE_AURA_LAYER = new EntityModelLayer(new Identifier(ForbiddenArcanus.MOD_ID, "black_hole"), "aura");

    private static final Identifier BLACK_HOLE_TEXTURE = new Identifier(ForbiddenArcanus.MOD_ID, "textures/block/black_hole.png");
    private static final Identifier[] BLACK_HOLE_AURA = {
            new Identifier(ForbiddenArcanus.MOD_ID, "textures/block/black_hole_aura_0.png"),
            new Identifier(ForbiddenArcanus.MOD_ID, "textures/block/black_hole_aura_1.png"),
            new Identifier(ForbiddenArcanus.MOD_ID, "textures/block/black_hole_aura_2.png")
    };

    private static final RenderLayer RENDER_TYPE = RenderLayer.getEntityCutoutNoCull(BLACK_HOLE_TEXTURE);
//    private static final RenderLayer[] AURA_RENDER_TYPE = {
//            FARenderTypes.entityFullbrightCutout(BLACK_HOLE_AURA[0]),
//            FARenderTypes.entityFullbrightCutout(BLACK_HOLE_AURA[1]),
//            FARenderTypes.entityFullbrightCutout(BLACK_HOLE_AURA[2])
//    };

    private static final float SIN_45 = (float) Math.sin(Math.PI / 3D);

    private final ModelPart hole;
    private final ModelPart aura;

    public BlackHoleRenderer(BlockEntityRendererFactory.Context context) {
        this.hole = context.getLayerModelPart(BLACK_HOLE_LAYER);
        this.aura = context.getLayerModelPart(BLACK_HOLE_AURA_LAYER);
    }

    public static TexturedModelData createHoleLayer() {
        ModelData meshDefinition = new ModelData();
        meshDefinition.getRoot().addChild("hole", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F), ModelTransform.NONE);
        return TexturedModelData.of(meshDefinition, 16, 16);
    }

    public static TexturedModelData createAuraLayer() {
        ModelData meshDefinition = new ModelData();
        meshDefinition.getRoot().addChild("aura", ModelPartBuilder.create().uv(0, 0).cuboid(-10.0F, 0.0F, -10.0F, 20.0F, 0.1F, 20.0F),  ModelTransform.NONE);
        return TexturedModelData.of(meshDefinition, 20, 20);
    }

    @Override
    public void render(BlackHoleBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        matrices.translate(0.5D, 0.5D, 0.5D);

        VertexConsumer vertexconsumer = vertexConsumers.getBuffer(RENDER_TYPE);

        float rotation = ((float) blockEntity.rotation + tickDelta) * 3.0F;
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(rotation));

        matrices.push();
        matrices.multiply(new Quaternion(new Vec3f(SIN_45, 0.0F, SIN_45), 60.0F, true));

        this.hole.render(matrices, vertexconsumer, light, overlay);
        matrices.pop();

//        vertexconsumer = vertexConsumers.getBuffer(AURA_RENDER_TYPE[blockEntity.auraTexture]);
//        this.aura.render(matrices, vertexconsumer, light, overlay);

        matrices.pop();
    }
}
