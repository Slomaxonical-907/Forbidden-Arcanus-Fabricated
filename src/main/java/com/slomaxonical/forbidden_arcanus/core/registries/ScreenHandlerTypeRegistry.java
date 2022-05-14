package com.slomaxonical.forbidden_arcanus.core.registries;

import com.slomaxonical.forbidden_arcanus.common.inventory.HephaestusForgeMenu;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.registry.Registry;

public class ScreenHandlerTypeRegistry {
    public static final ScreenHandlerType<HephaestusForgeMenu> HEPHAESTUS_FORGE = register("hephaestus_forge", HephaestusForgeMenu::new);

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String name, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registry.SCREEN_HANDLER ,name, new ScreenHandlerType<>(factory));
    }

    public static void init() {
    }
}
