package com.slomaxonical.forbidden_arcanus.client.renderer.entity;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.common.entity.projectile.EnergyBallEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix4f;

import javax.annotation.Nonnull;
public class EnergyBallRenderer extends EntityRenderer<EnergyBallEntity> {

    private static final Identifier LOCATION = new Identifier(ForbiddenArcanus.MOD_ID, "textures/effect/energy_ball.png");

    public EnergyBallRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(@Nonnull EnergyBallEntity entity, float entityYaw, float partialTicks, @Nonnull MatrixStack poseStack, @Nonnull VertexConsumerProvider buffer, int packedLight) {
        poseStack.push();

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderLayer.getEntityTranslucent(this.getTexture(entity)));
        Matrix4f matrix4f = poseStack.peek().getPositionMatrix();

        long t = System.currentTimeMillis() % 6;

        poseStack.multiply(this.dispatcher.getRotation());

        vertexConsumer.vertex(matrix4f, -1, -1, 0).color(255, 255, 255, 255).texture(0, 0 +  t * (1.0f / 4.0f)).overlay(OverlayTexture.DEFAULT_UV).light(packedLight).normal(0, 1, 0).next();
        vertexConsumer.vertex(matrix4f, -1, 1, 0).color(255, 255, 255, 255).texture(0, 0 +  t * (1.0f / 4.0f) + (1.0f / 4.0f)).overlay(OverlayTexture.DEFAULT_UV).light(packedLight).normal(0, 1, 0).next();
        vertexConsumer.vertex(matrix4f, 1, 1, 0).color(255, 255, 255, 255).texture(1, 0 +  t * (1.0f / 4.0f) + (1.0f / 4.0f)).overlay(OverlayTexture.DEFAULT_UV).light(packedLight).normal(0, 1, 0).next();
        vertexConsumer.vertex(matrix4f, 1, -1, 0).color(255, 255, 255, 255).texture(1, 0 +  t * (1.0f / 4.0f)).overlay(OverlayTexture.DEFAULT_UV).light(packedLight).normal(0, 1, 0).next();

        poseStack.pop();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Nonnull
    @Override
    public Identifier getTexture(@Nonnull EnergyBallEntity entity) {
        return LOCATION;
    }
}