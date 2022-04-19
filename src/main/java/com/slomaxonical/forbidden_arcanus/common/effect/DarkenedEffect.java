package com.slomaxonical.forbidden_arcanus.common.effect;

import com.slomaxonical.forbidden_arcanus.core.registries.FAStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

/**
 * Darkened Effect <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.common.effect.DarkenedEffect
 *
 * @author stal111
 * @version 1.17.1 - 2.0.0
 */
public class DarkenedEffect extends StatusEffect {

    public DarkenedEffect(StatusEffectCategory effectCategory, int color) {
        super(effectCategory, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity livingEntity, int amplifier) {
        int i = livingEntity.getStatusEffect(FAStatusEffects.DARKENED).getDuration();
        if (i >= 20) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 99999, 0, false, false, false));
        } else {
            livingEntity.removeStatusEffect(StatusEffects.BLINDNESS);
        }
    }
}
