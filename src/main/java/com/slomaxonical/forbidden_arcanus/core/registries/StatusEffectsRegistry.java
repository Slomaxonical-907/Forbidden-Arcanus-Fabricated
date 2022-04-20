package com.slomaxonical.forbidden_arcanus.core.registries;

import com.slomaxonical.forbidden_arcanus.common.effect.DarkenedEffect;
import com.slomaxonical.forbidden_arcanus.common.effect.SpectralEyeEffect;
import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.registry.Registry;

public class StatusEffectsRegistry implements AutoRegistryContainer<StatusEffect> {
    public static final StatusEffect SPECTRAL_VISION = new SpectralEyeEffect(StatusEffectCategory.BENEFICIAL, 745784);
    public static final StatusEffect DARKENED = new DarkenedEffect(StatusEffectCategory.HARMFUL,74578).addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", (double)-0.15F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

    @Override
    public Registry<StatusEffect> getRegistry() {
        return Registry.STATUS_EFFECT;
    }

    @Override
    public Class<StatusEffect> getTargetFieldType() {
        return StatusEffect.class;
    }
}
