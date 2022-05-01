package com.slomaxonical.forbidden_arcanus.client.model;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import net.minecraft.client.model.*;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class MagicCircleModel {

    public static final EntityModelLayer OUTER_RING_LAYER = new EntityModelLayer(new Identifier(ForbiddenArcanus.MOD_ID, "magic_circle"), "outer_ring");
    public static final EntityModelLayer INNER_RING_LAYER = new EntityModelLayer(new Identifier(ForbiddenArcanus.MOD_ID, "magic_circle"), "inner_ring");

    private final ModelPart outerRing;
    private final ModelPart innerRing;

    public MagicCircleModel(BlockEntityRendererFactory.Context context) {
        this.outerRing = context.getLayerModelPart(OUTER_RING_LAYER);
        this.innerRing = context.getLayerModelPart(INNER_RING_LAYER);
    }

    public static TexturedModelData createLayer() {
        ModelData meshDefinition = new ModelData();
        meshDefinition.getRoot().addChild("outer_ring", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, 0.0F, -5.0F, 10.0F, 0.1F, 10.0F), ModelTransform.NONE);
        return TexturedModelData.of(meshDefinition, 10, 10);
    }

    public ModelPart getOuterRing() {
        return outerRing;
    }

    public ModelPart getInnerRing() {
        return innerRing;
    }
}
