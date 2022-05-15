package com.slomaxonical.forbidden_arcanus.client.renderer.item;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import javax.annotation.Nonnull;

/**
 * Obsidian Skull Shield Item Renderer <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.client.renderer.item.ObsidianSkullShieldItemRenderer
 *
 * @author stal111
 * @version 1.17.1 - 2.0.0
 * @since 2021-02-12
 */
public class ObsidianSkullShieldItemRenderer extends BuiltinModelItemRenderer {

    private final ShieldEntityModel model;
    private final Identifier texture = new Identifier(ForbiddenArcanus.ID, "entity/obsidian_skull_shield");

    public ObsidianSkullShieldItemRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelLoader modelSet) {
        super(dispatcher, modelSet);
        this.model = new ShieldEntityModel(modelSet.getModelPart(EntityModelLayers.SHIELD));
    }

    @Override
    public void render(@Nonnull ItemStack stack, @Nonnull ModelTransformation.Mode transformType, MatrixStack poseStack, @Nonnull VertexConsumerProvider buffer, int combinedLight, int combinedOverlay) {
        poseStack.push();
        poseStack.scale(1.0F, -1.0F, -1.0F);

        SpriteIdentifier material = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, this.texture);
        VertexConsumer vertexConsumer = material.getSprite().getTextureSpecificVertexConsumer(ItemRenderer.getDirectItemGlintConsumer(buffer, this.model.getLayer(material.getAtlasId()), true, stack.hasGlint()));

        this.model.render(poseStack, vertexConsumer, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.pop();
    }
}
