package com.yerti.runecraft.core.player;

import com.yerti.runecraft.managers.LevelManager;
import com.yerti.runecraft.skills.Skill;
import org.bukkit.entity.Player;

import java.util.*;

public class RunePlayer {

    private Player player;

    private static Map<UUID, LevelManager> skills = new HashMap<>();

    public RunePlayer(Player player, LevelManager levelManager) {
        this.player = player;
    }

    public UUID getUniqueID() {
        return player.getUniqueId();
    }

    public Map<UUID, LevelManager> getKills() {
        return skills;
    }



}
