package com.yerti.runecraft.player;

import com.yerti.core.utils.SerializationUtils;
import com.yerti.runecraft.RuneCraft;
import com.yerti.runecraft.managers.PlayerSkillManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
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

    public RunePlayer(Player player) {
        this.player = player;
        RuneCraft.getInstance().getStorageManager().savePlayer(player);
        this.levelManager = RuneCraft.getInstance().getStorageManager().retrieveManager(player.getUniqueId());
    }

    public UUID getUniqueID() {
        return player.getUniqueId();
    }

    public PlayerSkillManager getLevelManager() {
        return levelManager;
    }

    public static RunePlayer getPlayer(Player p) {
        if (p.getMetadata(RuneCraft.playerDataKey) == null || p.getMetadata(RuneCraft.playerDataKey).isEmpty()) {
            PlayerSkillManager manager = new PlayerSkillManager();
            try {
                Bukkit.broadcastMessage("Doesn't exist");
                p.setMetadata(RuneCraft.playerDataKey, new FixedMetadataValue(RuneCraft.getInstance(), SerializationUtils.toString(manager)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return new RunePlayer(p, manager);
        }

        try {
            Bukkit.broadcastMessage(SerializationUtils.fromString(p.getMetadata(RuneCraft.playerDataKey).get(0).asString()).toString());
            return new RunePlayer(p, (PlayerSkillManager) SerializationUtils.fromString(p.getMetadata(RuneCraft.playerDataKey).get(0).asString()));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        RuneCraft.getInstance().getStorageManager().savePlayer(event.getPlayer());
        RuneCraft.getInstance().getPlayers().remove(new RunePlayer(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        RuneCraft.getInstance().getStorageManager().savePlayer(event.getPlayer());
        RuneCraft.getInstance().getPlayers().add(new RunePlayer(event.getPlayer()));
    }





}
