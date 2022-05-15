package com.slomaxonical.forbidden_arcanus.client.renderer.entity;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.common.entity.projectile.BoomArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class BoomArrowRenderer extends ProjectileEntityRenderer<BoomArrowEntity> {

    private static final Identifier LOCATION = new Identifier(ForbiddenArcanus.ID, "textures/entity/projectiles/boom_arrow.png");

    public BoomArrowRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(BoomArrowEntity entity) {
        return LOCATION;
    }
}
