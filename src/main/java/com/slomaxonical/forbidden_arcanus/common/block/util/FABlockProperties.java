package com.slomaxonical.forbidden_arcanus.common.block.util;

import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.EnumProperty;

public class FABlockProperties {
    public static final BooleanProperty ACTIVATED = BooleanProperty.of("activated");
    public static final BooleanProperty SPECK = BooleanProperty.of("speck");
    public static final BooleanProperty RITUAL = BooleanProperty.of("ritual");
    public static final BooleanProperty OILY = BooleanProperty.of("oily");
    public static final BooleanProperty LEAVES = BooleanProperty.of("leaves");

    public static final IntProperty AGE_6 = IntProperty.of("age", 0, 6);

    public static final EnumProperty<PillarType> PILLAR_TYPE = EnumProperty.of("type", PillarType.class);
    public static final EnumProperty<ObeliskPart> OBELISK_PART = EnumProperty.of("part", ObeliskPart.class);
}

