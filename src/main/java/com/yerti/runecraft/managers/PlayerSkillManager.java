package com.yerti.runecraft.managers;

import com.yerti.runecraft.core.player.RunePlayer;
import com.yerti.runecraft.skills.Skill;

import java.util.HashMap;
import java.util.Map;

public class PlayerSkillManager {

    private RunePlayer player;

    private Map<Skill, Integer> levels = new HashMap<>();
    private Map<Skill, Double> levelsXp = new HashMap<>();


    /**
     * Gets the level map for changing/
     * @return levels list
     */
    public Map<Skill, Integer> getLevels() {
        return levels;
    }

    /**
     * Gets the xp map for changing/
     * @return levelsXp list
     */
    public Map<Skill, Double> getLevelsXp() {
        return levelsXp;
    }

    /**
     * Gets the player the SkillManager belongs to
     * @return the player
     */
    public RunePlayer getPlayer() {
        return player;
    }

    /**
     * Player Manager that stores all of the skills for a player
     * @param player
     */
    public PlayerSkillManager(RunePlayer player) {
        this.player = player;
    }

    /**
     * Gets the experience of a skill
     * @param skill
     * @return XP Amount
     */
    public double getXp(Skill skill) {
        return levelsXp.get(skill);
    }


    /**
     * Sets the experience of a skill
     * @param skill
     */
    public void setXp(Skill skill, double xp) {
        levelsXp.put(skill, xp);
        levels.put(skill, calculateLevel(xp));
    }


    //TODO: Maybe move this into a file for the values instead

    /**
     * Recalculates the level for an xp amount
     * @param xp
     * @return Level amount
     */
    private int calculateLevel(double xp) {
        int level = 0;
        double maxXp = calculateXpForLevel(1);
        do {
            maxXp += calculateXpForLevel(level++);
        } while (maxXp < xp);

        return level;
    }

    /**
     * Calculates the experience needed to reach a certain level
     * @param level
     * @return XP Amount
     */
    private double calculateXpForLevel(int level) {
        double xpResult = 0;

        for (int x = 1; x < level; x++) {
            xpResult += Math.floor(x + 300 * Math.pow(2, x / 7.0));

        }

        return Math.floor(xpResult / 5);
    }

}
