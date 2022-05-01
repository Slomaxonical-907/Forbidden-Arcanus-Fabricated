package com.slomaxonical.forbidden_arcanus.client;

import net.minecraft.client.render.Shader;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.resource.ResourceFactory;

import java.io.IOException;

public class ExtendedShader extends Shader {
    public ExtendedShader(ResourceFactory factory, String name, VertexFormat format) throws IOException {
        super(factory, name, format);
    }
}
