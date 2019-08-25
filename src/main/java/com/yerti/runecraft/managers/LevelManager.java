package com.yerti.runecraft.managers;

import com.yerti.runecraft.skills.Skill;

public class LevelManager {

    private Skill skill;
    private int level = 1;
    private double xp = 0;

    /**
     *  Manages the levels and experience of each skill for a player
     * @param skill
     */
    public LevelManager(Skill skill) {
        this.skill = skill;
    }

    /**
     * Returns the skill for the LevelManager
     * @return
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * Sets the skill for the LevelManager
     */
    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
