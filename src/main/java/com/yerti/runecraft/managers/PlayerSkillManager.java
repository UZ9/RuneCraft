package com.yerti.runecraft.managers;

import com.yerti.runecraft.core.player.RunePlayer;
import com.yerti.runecraft.skills.SkillType;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PlayerSkillManager implements Serializable {

    private Player player;

    private Map<SkillType, Integer> levels = new HashMap<>();
    private Map<SkillType, Double> levelsXp = new HashMap<>();



    /**
     * Player Manager that stores all of the skills for a player
     * @param player
     */
    public PlayerSkillManager(Player player) {
        this.player = player;

        for (SkillType type : SkillType.values()) {
            levels.put(type, 0);
            levelsXp.put(type, 0.);
        }
    }




    /**
     * Gets the level map for changing/
     * @return levels list
     */
    public Map<SkillType, Integer> getLevels() {
        return levels;
    }

    /**
     * Gets the xp map for changing/
     * @return levelsXp list
     */
    public Map<SkillType, Double> getLevelsXp() {
        return levelsXp;
    }

    /**
     * Gets the player the SkillManager belongs to
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }


    /**
     * Gets the experience of a skill
     * @param name
     * @return XP Amount
     */
    public double getXp(String name) {
        return levelsXp.get(SkillType.valueOf(name.toUpperCase()));
    }


    /**
     * Sets the experience of a skill
     * @param skill
     */
    public void setXp(SkillType skill, double xp) {
        levelsXp.put(skill, xp);
        levels.put(skill, calculateLevel(xp));
    }

    /**
     * Adds experience to a skill
     * @param skill
     */
    public void addXp(SkillType skill, double xp) {
        levelsXp.put(skill, levelsXp.get(skill) + 1);
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
