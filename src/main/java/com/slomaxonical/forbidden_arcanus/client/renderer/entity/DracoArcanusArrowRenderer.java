package com.slomaxonical.forbidden_arcanus.client.renderer.entity;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import com.slomaxonical.forbidden_arcanus.common.entity.projectile.DracoArcanusArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class DracoArcanusArrowRenderer extends ProjectileEntityRenderer<DracoArcanusArrowEntity> {

    private static final Identifier LOCATION = new Identifier(ForbiddenArcanus.MOD_ID, "textures/entity/projectiles/draco_arcanus_arrow.png");

    public DracoArcanusArrowRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(DracoArcanusArrowEntity entity) {
        return LOCATION;
    }
}
