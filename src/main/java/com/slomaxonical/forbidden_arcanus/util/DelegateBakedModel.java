package com.slomaxonical.forbidden_arcanus.util;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.math.Direction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class DelegateBakedModel implements BakedModel {

    private final BakedModel base;

    public DelegateBakedModel(BakedModel base) {
        this.base = base;
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand) {
        return this.base.getQuads(state, side, rand);
    }

    @Override
    public boolean useAmbientOcclusion() {
        return this.base.useAmbientOcclusion();
    }

    @Override
    public boolean hasDepth() {
        return this.base.hasDepth();
    }

    @Override
    public boolean isSideLit() {
        return this.base.isSideLit();
    }

    @Override
    public boolean isBuiltin() {
        return this.base.isBuiltin();
    }

    @Override
    public Sprite getParticleSprite() {
        return this.base.getParticleSprite();
    }

    @Override
    public ModelOverrideList getOverrides() {
        return this.base.getOverrides();
    }

    @Override
    public ModelTransformation getTransformation() {
        return this.base.getTransformation();
    }

    public BakedModel getBase() {
        return this.base;
    }
}