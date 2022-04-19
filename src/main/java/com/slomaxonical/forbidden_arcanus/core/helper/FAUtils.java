package com.slomaxonical.forbidden_arcanus.core.helper;

import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

public class FAUtils {
    public static Team createTeam(Scoreboard scoreboard, String name, Formatting color) {
        if (scoreboard.getTeamNames().contains(name)) {
            return scoreboard.getPlayerTeam(name);
        } else {
            Team team = scoreboard.addTeam(name);
            team.setDisplayName(new LiteralText(name));
            team.setColor(color);
            return team;
        }
    }

    public static void removeTeam(Scoreboard scoreboard, Team team) {
        if (scoreboard.getTeamNames().contains(team.getName())) {
            scoreboard.removeTeam(team);
        }
    }
}
