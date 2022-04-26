package com.slomaxonical.forbidden_arcanus.common.inventory;

import net.minecraft.util.StringIdentifiable;
public enum InputType implements StringIdentifiable {
    AUREAL("aureal"),
    SOULS("souls"),
    BLOOD("blood"),
    EXPERIENCE("experience");

    private final String name;

    InputType(String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return this.name;
    }
}
