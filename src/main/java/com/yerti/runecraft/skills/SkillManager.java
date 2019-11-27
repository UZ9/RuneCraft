package com.yerti.runecraft.skills;

import com.yerti.runecraft.player.RunePlayer;

public abstract class SkillManager {

    private SkillType type;
    private RunePlayer player;

    public SkillManager(RunePlayer player, SkillType type) {
        this.type = type;
        this.player = player;
    }

    public void addXp(int amount) {
        player.getLevelManager().addXp(type, amount);
    }

}
