package com.slomaxonical.forbidden_arcanus.common.effect;

import com.slomaxonical.forbidden_arcanus.core.helper.FAHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;

import java.util.List;

/**
 * Spectral Eye Effect <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.common.effect.SpectralEyeEffect
 *
 * @author stal111
 * @version 1.17.1 - 2.0.0
 */
public class SpectralEyeEffect extends StatusEffect {

    private static final int EFFECT_RADIUS = 60;

    public SpectralEyeEffect(StatusEffectCategory effectCategory, int color) {
        super(effectCategory, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        int i = entity.getStatusEffect(this).getDuration();
        if (!entity.getEntityWorld().isClient()) {
            Scoreboard scoreboard = entity.getEntityWorld().getServer().getScoreboard();
            Team teamPassiveOrNeutral = FAHelper.createTeam(scoreboard, "PassiveOrNeutral", Formatting.GREEN);
            Team teamHostile = FAHelper.createTeam(scoreboard, "Hostile",Formatting.RED);
            Team teamWater = FAHelper.createTeam(scoreboard, "Water", Formatting.BLUE);

            double k = entity.getX();
            double l = entity.getY();
            double i1 = entity.getZ();

            Box aabb = new Box(k, l, i1, (k + 1), (l + 1), (i1 + 1)).expand(EFFECT_RADIUS).stretch(0.0D, entity.world.getTopY(), 0.0D);
            List<LivingEntity> list = entity.world.getNonSpectatingEntities(LivingEntity.class, aabb);

            for(LivingEntity livingEntity : list) {
                if (livingEntity instanceof AnimalEntity || livingEntity instanceof AmbientEntity) {
                    scoreboard.addPlayerToTeam(livingEntity.getEntityName(), teamPassiveOrNeutral);
                } else if (livingEntity instanceof HostileEntity) {
                    scoreboard.addPlayerToTeam(livingEntity.getEntityName(), teamHostile);
                } else if (!(livingEntity instanceof PlayerEntity)) {
                    scoreboard.addPlayerToTeam(livingEntity.getEntityName(), teamWater);
                }
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 5, 0, true, true, false));
            }

            if (i <= 5) {
                FAHelper.removeTeam(scoreboard, teamPassiveOrNeutral);
                FAHelper.removeTeam(scoreboard, teamHostile);
                FAHelper.removeTeam(scoreboard, teamWater);
            }
        }
    }
}
