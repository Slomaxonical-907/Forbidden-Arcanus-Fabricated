package com.slomaxonical.forbidden_arcanus.common.block.util;

import net.minecraft.util.StringIdentifiable;

import javax.annotation.Nonnull;

public enum PillarType implements StringIdentifiable {
    SINGLE("single"),
    TOP("top"),
    MIDDLE("middle"),
    BOTTOM("bottom");

    private final String name;

    PillarType(String name) {
        this.name = name;
    }

    public static PillarType getTypeForConnections(boolean connectUp, boolean connectDown) {
        if (connectUp && connectDown) {
            return PillarType.MIDDLE;
        } else if (connectUp) {
            return PillarType.BOTTOM;
        } else if (connectDown) {
            return PillarType.TOP;
        }

        return PillarType.SINGLE;
    }

    @Nonnull
    @Override
    public String asString() {
        return this.name;
    }
}