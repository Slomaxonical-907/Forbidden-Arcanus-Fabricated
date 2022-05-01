package com.slomaxonical.forbidden_arcanus.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class FullbrightBakedModel extends DelegateBakedModel {

    private static final LoadingCache<CacheKey, List<BakedQuad>> CACHE = CacheBuilder.newBuilder().build(new CacheLoader<>() {
        @Nonnull
        @Override
        public List<BakedQuad> load(@Nonnull CacheKey key) {
            return transformQuads(key.base().getQuads(key.state(), key.side(), key.random()), key.textures());
        }
    });

    private final Set<Identifier> textures;
    private final boolean doCaching;

    public FullbrightBakedModel(BakedModel base, Identifier... textures) {
        this(base, true, textures);
    }

    public FullbrightBakedModel(BakedModel base, boolean doCaching, Identifier... textures) {
        super(base);
        this.textures = new HashSet<>(Arrays.asList(textures));
        this.doCaching = doCaching;
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand) {
        BakedModel base = this.getBase();

        if (state == null) {
            return base.getQuads(null, side, rand);
        }

        if (!this.doCaching) {
            return transformQuads(base.getQuads(state, side, rand), this.textures);
        }

        return CACHE.getUnchecked(new CacheKey(base, this.textures, rand, state, side));
    }

    private static List<BakedQuad> transformQuads(List<BakedQuad> oldQuads, Set<Identifier> textures) {
        List<BakedQuad> quads = new ArrayList<>(oldQuads);

        for (int i = 0; i < quads.size(); i++) {
            BakedQuad quad = quads.get(i);

            if (textures.contains(quad.getSprite().getId())) {
                quads.set(i, transformQuad(quad));
            }
        }

        return quads;
    }

    private static BakedQuad transformQuad(BakedQuad quad) {
        int[] vertexData = quad.getVertexData().clone();
        int step = vertexData.length / 4;

        // Set lighting to fullbright on all vertices
        vertexData[6] = 0x00F000F0;
        vertexData[6 + step] = 0x00F000F0;
        vertexData[6 + 2 * step] = 0x00F000F0;
        vertexData[6 + 3 * step] = 0x00F000F0;

        return new BakedQuad(vertexData, quad.getColorIndex(), quad.getFace(), quad.getSprite(), quad.hasShade());
    }

    public static void invalidateCache() {
        CACHE.invalidateAll();
    }

    private record CacheKey(BakedModel base, Set<Identifier> textures, Random random, BlockState state, Direction side) {

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            CacheKey cacheKey = (CacheKey) o;

            if (cacheKey.side != side) {
                return false;
            }

            return state.equals(cacheKey.state);
        }

        @Override
        public int hashCode() {
            return state.hashCode() + (31 * (side != null ? side.hashCode() : 0));
        }
    }
}