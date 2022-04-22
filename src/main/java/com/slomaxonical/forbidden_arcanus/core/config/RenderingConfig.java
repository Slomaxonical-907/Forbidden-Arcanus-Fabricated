package com.slomaxonical.forbidden_arcanus.core.config;

import net.valhelsia.valhelsia_core.core.config.ValhelsiaConfigSpec;

public class RenderingConfig {

    public static ValhelsiaConfigSpec.BooleanValue ORB_OF_TEMPORARY_FLIGHT_OVERLAY_RENDER;
    public static ValhelsiaConfigSpec.IntValue ORB_OF_TEMPORARY_FLIGHT_OVERLAY_X_POSITION;
    public static ValhelsiaConfigSpec.IntValue ORB_OF_TEMPORARY_FLIGHT_OVERLAY_Y_POSITION;


    public static void init(ValhelsiaConfigSpec.Builder builder) {
        builder.push("rendering");

        ORB_OF_TEMPORARY_FLIGHT_OVERLAY_RENDER = builder.comment("Should the Orb of Temporary Flight Overlay be rendered? [default: true]").define("orb_of_temporary_flight.render", true);
        ORB_OF_TEMPORARY_FLIGHT_OVERLAY_X_POSITION = builder.comment("X-Position of the Orb of Temporary Flight Overlay (if enabled) [default: 1]").defineInRange("orb_of_temporary_flight.x_position", 1, 0, 500);
        ORB_OF_TEMPORARY_FLIGHT_OVERLAY_Y_POSITION = builder.comment("Y-Position of the Orb of Temporary Flight Overlay (if enabled) [default: 1]").defineInRange("orb_of_temporary_flight.y_position", 1, 0, 500);

        builder.pop();
    }
}
