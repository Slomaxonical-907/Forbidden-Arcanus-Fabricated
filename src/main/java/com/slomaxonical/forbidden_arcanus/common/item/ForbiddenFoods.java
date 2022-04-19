package com.slomaxonical.forbidden_arcanus.common.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ForbiddenFoods {
    public static final FoodComponent BAT_SOUP = new FoodComponent.Builder().hunger(7).saturationModifier (0.7F).statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 240, 0), 1.0F).build();
    public static final FoodComponent BAT_WING = new FoodComponent.Builder().hunger(3).saturationModifier (0.2F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 160, 0), 0.9F).build();
    public static final FoodComponent CHERRY_PEACH = new FoodComponent.Builder().hunger(4).saturationModifier (0.4F).build();
    public static final FoodComponent TENTACLE = new FoodComponent.Builder().hunger(2).saturationModifier (0.1F).meat().build();
    public static final FoodComponent COOKED_TENTACLE = new FoodComponent.Builder().hunger(5).saturationModifier (0.6F).meat().build();
    public static final FoodComponent STRANGE_ROOT = new FoodComponent.Builder().hunger(3).saturationModifier (0.6F).build();
}
