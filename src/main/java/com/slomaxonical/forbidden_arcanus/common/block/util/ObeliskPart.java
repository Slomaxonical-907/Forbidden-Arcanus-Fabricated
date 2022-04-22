package com.slomaxonical.forbidden_arcanus.common.block.util;

import net.minecraft.util.StringIdentifiable;

import javax.annotation.Nonnull;

public enum ObeliskPart implements StringIdentifiable {
    UPPER("upper"),
    MIDDLE("middle"),
    LOWER("lower");

    private final String name;

    ObeliskPart(String name) {
        this.name = name;
    }

    @Nonnull
    @Override
    public String asString() {
        return this.name;
    }
}
