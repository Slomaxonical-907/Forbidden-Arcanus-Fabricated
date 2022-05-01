package com.slomaxonical.forbidden_arcanus.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.Shader;

import javax.annotation.Nullable;
import java.io.IOException;

@Environment(EnvType.CLIENT)
public class FAShaders {

    @Nullable
    private static Shader rendertypeEntityFullbrightCutout;

    @Nullable
    private static Shader rendertypeEntityFullbrightTranslucent;

//i think this will need a mixin?
//    public static void registerShaders(RegisterShadersEvent event) {
//        try {
//            ResourceManager resourceManager = event.getResourceManager();
//            event.registerShader(new ShaderInstance(resourceManager, new ResourceLocation(ForbiddenArcanus.MOD_ID, "rendertype_entity_fullbright_cutout"), DefaultVertexFormat.NEW_ENTITY), shaderInstance -> rendertypeEntityFullbrightCutout = shaderInstance);
//            event.registerShader(new ShaderInstance(resourceManager, new ResourceLocation(ForbiddenArcanus.MOD_ID, "rendertype_entity_fullbright_translucent"), DefaultVertexFormat.NEW_ENTITY), shaderInstance -> rendertypeEntityFullbrightTranslucent = shaderInstance);
//        } catch (IOException e) {
//            throw new RuntimeException("Could not reload F&A's shaders!", e);
//        }
//    }

    @Nullable
    public static Shader getRendertypeEntityFullbrightCutout() {
        return rendertypeEntityFullbrightCutout;
    }

    @Nullable
    public static Shader getRendertypeEntityFullbrightTranslucent() {
        return rendertypeEntityFullbrightCutout;
    }
}
