package com.slomaxonical.forbidden_arcanus.client.renderer.item;

import com.mojang.datafixers.util.Pair;
import com.slomaxonical.forbidden_arcanus.client.renderer.block.ObsidianSkullRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.SkullEntityModel;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * Obsidian Skull Item Renderer <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.client.renderer.item.ObsidianSkullItemRenderer
 *
 * @author stal111
 * @version 16.2.0
 * @since 2021-02-11
 */
public class ObsidianSkullItemRenderer extends BuiltinModelItemRenderer {

    private final Pair<SkullEntityModel, SkullEntityModel> models;

    public ObsidianSkullItemRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelLoader modelSet) {
        super(dispatcher, modelSet);
        this.models = ObsidianSkullRenderer.createModels(modelSet);
    }

    @Override
    public void render(ItemStack stack, @Nonnull ModelTransformation.Mode transformType, @Nonnull MatrixStack poseStack, @Nonnull VertexConsumerProvider buffer, int combinedLight, int combinedOverlay) {
        if (stack.getItem() instanceof BlockItem blockItem) {
            ObsidianSkullRenderer.render(null, 180.0F, poseStack, buffer, combinedLight, this.models, blockItem.getBlock());
        }
    }
}
