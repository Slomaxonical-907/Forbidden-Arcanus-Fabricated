package com.slomaxonical.forbidden_arcanus.common.blockEntity.forge;


import com.slomaxonical.forbidden_arcanus.client.FARenderLayers;
import com.slomaxonical.forbidden_arcanus.client.model.MagicCircleModel;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.forge.ritual.Ritual;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.forge.ritual.RitualManager;
import com.slomaxonical.forbidden_arcanus.core.registries.ParticleRegistry;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Random;


public class MagicCircle {

    private final RitualManager ritualManager;

    private int rotation;

    public MagicCircle(RitualManager ritualManager) {
        this.ritualManager = ritualManager;
    }

    public void tick() {
        if (this.ritualManager.isRitualActive()) {
            this.rotation++;
        } else if (this.rotation != 0) {
            this.rotation = 0;
        }
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public void render(MatrixStack poseStack, float partialTicks, VertexConsumerProvider buffer, int packedLight, MagicCircleModel model) {
        if (this.ritualManager.isRitualActive()) {
            Ritual ritual = this.ritualManager.getActiveRitual();

            poseStack.push();

            float ticks = this.rotation + partialTicks;

            poseStack.translate(0.5D, 0.0D, 0.5D);

            float size = 1 + Math.min(ticks, 100) / 100.0F * 7.5F;
            poseStack.scale(size, 1.0F, size);

            float alpha = ticks > ritual.getTime() * 0.9F ? Math.max((ritual.getTime() - ticks), 0) / (ritual.getTime() * 0.1F) : 1.0F;

            poseStack.multiply(Vec3f.NEGATIVE_Y.getDegreesQuaternion(ticks));
            model.getOuterRing().render(poseStack, buffer.getBuffer(FARenderLayers.entityFullbrightTranslucent(ritual.getOuterTexture())), packedLight, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, alpha);

            poseStack.multiply(Vec3f.NEGATIVE_Y.getDegreesQuaternion(-ticks * 2));
            model.getInnerRing().render(poseStack, buffer.getBuffer(FARenderLayers.entityFullbrightTranslucent(ritual.getInnerTexture())), packedLight, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, alpha);

            poseStack.pop();

            if (ticks > ritual.getTime() * 0.9F) {
                World level = Objects.requireNonNull(this.ritualManager.getBlockEntity().getWorld());
                BlockPos pos = this.ritualManager.getBlockEntity().getPos();
                Random rand = level.getRandom();

                double posX = pos.getX() + 0.25D + rand.nextFloat() + rand.nextInt(4);
                double posZ = pos.getZ() + 0.25D + rand.nextFloat() + rand.nextInt(4);
                double ySpeed = ((double) rand.nextFloat() - 0.4D) * 0.125D;

                level.addParticle(ParticleRegistry.AUREAL_MOTE, posX - 2.0D, pos.getY() + 0.1F, posZ - 2.0D, 0, ySpeed, 0);
            }
        }
    }
}
