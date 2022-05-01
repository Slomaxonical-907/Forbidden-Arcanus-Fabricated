package com.slomaxonical.forbidden_arcanus.client.renderer.block;

import com.mojang.datafixers.util.Pair;
import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.common.block.ObsidianSkullBlock;
import com.slomaxonical.forbidden_arcanus.common.block.ObsidianWallSkullBlock;
import com.slomaxonical.forbidden_arcanus.common.blockEntity.ObsidianSkullBlockEntity;
import com.slomaxonical.forbidden_arcanus.core.registries.block.BlockRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.SkullEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Obsidian Skull Renderer <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.client.renderer.block.ObsidianSkullRenderer
 *
 * @author stal111
 * @version 1.17.1 - 2.0.0
 * @since 2021-02-11
 */
@Environment(EnvType.CLIENT)
public class ObsidianSkullRenderer implements BlockEntityRenderer<ObsidianSkullBlockEntity> {

    public static final EntityModelLayer OBSIDIAN_SKULL_LAYER = new EntityModelLayer(new Identifier(ForbiddenArcanus.MOD_ID, "obsidian_skull"), "main");
    public static final EntityModelLayer ETERNAL_OBSIDIAN_SKULL_LAYER = new EntityModelLayer(new Identifier(ForbiddenArcanus.MOD_ID, "eternal_obsidian_skull"), "main");

    private static final Identifier TEXTURE = new Identifier(ForbiddenArcanus.MOD_ID, "textures/block/obsidian_skull.png");
    private static final Identifier ETERNAL_TEXTURE = new Identifier(ForbiddenArcanus.MOD_ID, "textures/block/eternal_obsidian_skull.png");

    private final Pair<SkullEntityModel, SkullEntityModel> models;

    public static Pair<SkullEntityModel, SkullEntityModel> createModels(EntityModelLoader modelSet) {
        return Pair.of(new SkullEntityModel(modelSet.getModelPart(OBSIDIAN_SKULL_LAYER)), new SkullEntityModel(modelSet.getModelPart(ETERNAL_OBSIDIAN_SKULL_LAYER)));
    }

    public ObsidianSkullRenderer(BlockEntityRendererFactory.Context context) {
        this.models = createModels(context.getLayerRenderDispatcher());
    }

    private static ModelData createBaseMesh() {
        ModelData ModelData = new ModelData();
        ModelPartData partDefinition = ModelData.getRoot();
        partDefinition.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.NONE);
        return ModelData;
    }

    public static TexturedModelData createObsidianSkullLayer() {
        return TexturedModelData.of(createBaseMesh(), 32, 16);
    }

    public static TexturedModelData createEternalObsidianSkullLayer() {
        ModelData ModelData = createBaseMesh();
        ModelPartData partdefinition = ModelData.getRoot();
        partdefinition.getChild("head").addChild("layer", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.25F)), ModelTransform.NONE);
        return TexturedModelData.of(ModelData, 64, 16);
    }

    @Override
    public void render(ObsidianSkullBlockEntity blockEntity, float partialTicks, @Nonnull MatrixStack poseStack, @Nonnull VertexConsumerProvider buffer, int combinedLight, int combinedOverlay) {
        BlockState state = blockEntity.getCachedState();

        boolean flag = state.getBlock() instanceof ObsidianWallSkullBlock;

        Direction direction = flag ? state.get(ObsidianWallSkullBlock.FACING) : null;
        float rotation = flag ? (float) (2 + direction.getHorizontal()) * 4 : state.get(ObsidianSkullBlock.ROTATION);

        render(direction, 22.5F * rotation, poseStack, buffer, combinedLight, models, state.getBlock());
    }

    public static void render(@Nullable Direction direction, float rotation, MatrixStack poseStack, VertexConsumerProvider buffer, int combinedLight, Pair<SkullEntityModel, SkullEntityModel> models, Block block) {
        poseStack.push();

        boolean eternal = block == BlockRegistry.ETERNAL_OBSIDIAN_SKULL|| block == BlockRegistry.ETERNAL_OBSIDIAN_WALL_SKULL;
        SkullEntityModel model = eternal ? models.getSecond() : models.getFirst();

        if (direction == null) {
            poseStack.translate(0.5D, 0.0D, 0.5D);
        } else {
            poseStack.translate(0.5F - (float) direction.getOffsetX() * 0.25F, 0.25D, 0.5F - (float) direction.getOffsetZ() * 0.25F);
        }

        poseStack.scale(-1.0F, -1.0F, 1.0F);
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderLayer.getEntityCutoutNoCullZOffset(eternal ? ETERNAL_TEXTURE : TEXTURE));
        model.setHeadRotation(0.0F, rotation, 0.0F);
        model.render(poseStack, vertexConsumer, combinedLight, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.pop();
    }
}
