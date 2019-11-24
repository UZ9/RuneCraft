package com.yerti.runecraft.core.player;

import com.yerti.runecraft.RuneCraft;
import com.yerti.runecraft.core.utils.SerializationUtils;
import com.yerti.runecraft.managers.PlayerSkillManager;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.IOException;
import java.util.UUID;

public class RunePlayer {

    private Player player;
    private PlayerSkillManager levelManager;

    public RunePlayer(Player player, PlayerSkillManager levelManager) {
        this.player = player;
        this.levelManager = levelManager;
    }

    public UUID getUniqueID() {
        return player.getUniqueId();
    }

    public PlayerSkillManager getLevelManager() {
        return levelManager;
    }

    public static RunePlayer getPlayer(Player p) {
        if (p.getMetadata(RuneCraft.playerDataKey) == null) {
            try {
                p.setMetadata(RuneCraft.playerDataKey, new FixedMetadataValue(RuneCraft.getInstance(), SerializationUtils.toString(new PlayerSkillManager(p))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            return new RunePlayer(p, (PlayerSkillManager) SerializationUtils.fromString(p.getMetadata(RuneCraft.playerDataKey).get(0).asString()));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }





}
