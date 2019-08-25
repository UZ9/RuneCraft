package com.yerti.runecraft.core.player;

import com.yerti.runecraft.managers.LevelManager;
import com.yerti.runecraft.skills.Skill;
import org.bukkit.entity.Player;

import java.util.*;

public class RunePlayer {

    private Player player;
    private LevelManager levelManager;

    private final Map<UUID, LevelManager> skills = new HashMap<>();

    public RunePlayer(Player player, LevelManager levelManager) {
        this.player = player;
        this.levelManager = levelManager;
    }

    public UUID getUniqueID() {
        return player.getUniqueId();
    }



}
