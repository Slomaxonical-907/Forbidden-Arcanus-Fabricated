package com.slomaxonical.forbidden_arcanus.client;

import com.slomaxonical.forbidden_arcanus.ForbiddenArcanus;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.function.Function;

public class FARenderLayers extends RenderLayer {

    public static final RenderPhase.Shader RENDERTYPE_ENTITY_FULLBRIGHT_CUTOUT_SHADER = new RenderPhase.Shader(FAShaders::getRendertypeEntityFullbrightCutout);
    public static final RenderPhase.Shader RENDERTYPE_ENTITY_FULLBRIGHT_TRANSLUCENT_SHADER = new RenderPhase.Shader(FAShaders::getRendertypeEntityFullbrightCutout);

    public FARenderLayers(String p_173178_, VertexFormat p_173179_, VertexFormat.DrawMode p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
        super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
    }

    private static final Function<Identifier, RenderLayer> ENTITY_FULLBRIGHT_CUTOUT = Util.memoize(id -> {
        RenderPhase.Texture textureStateShard = new RenderPhase.Texture(id, false, false);
        return RenderLayer.of(ForbiddenArcanus.MOD_ID + ":entity_fullbright_cutout", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 256, false, true, RenderLayer.MultiPhaseParameters.builder().shader(RENDERTYPE_ENTITY_FULLBRIGHT_CUTOUT_SHADER).texture(textureStateShard).transparency(NO_TRANSPARENCY).writeMaskState(COLOR_MASK).build(false));
    });

    private static final Function<Identifier, RenderLayer> ENTITY_FULLBRIGHT_TRANSLUCENT = Util.memoize(id -> {
        RenderPhase.Texture textureStateShard = new RenderPhase.Texture(id, false, false);
        return RenderLayer.of(ForbiddenArcanus.MOD_ID + ":entity_fullbright_translucent", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 256, false, true, RenderLayer.MultiPhaseParameters.builder().shader(RENDERTYPE_ENTITY_FULLBRIGHT_TRANSLUCENT_SHADER).texture(textureStateShard).transparency(TRANSLUCENT_TRANSPARENCY).writeMaskState(COLOR_MASK).build(false));
    });

    public static RenderLayer entityFullbrightCutout(Identifier id) {
        return ENTITY_FULLBRIGHT_CUTOUT.apply(id);
    }

    public static RenderLayer entityFullbrightTranslucent(Identifier id) {
        return ENTITY_FULLBRIGHT_TRANSLUCENT.apply(id);
    }
}
