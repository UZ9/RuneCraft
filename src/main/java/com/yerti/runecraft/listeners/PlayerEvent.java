package com.yerti.runecraft.listeners;

import com.yerti.runecraft.RuneCraft;
import com.yerti.runecraft.player.RunePlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvent implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

        RuneCraft.getInstance().getStorageManager().savePlayer(event.getPlayer());
        RuneCraft.getInstance().getPlayers().remove(new RunePlayer(event.getPlayer()));

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        RuneCraft.getInstance().getPlayers().add(new RunePlayer(event.getPlayer()));
        RuneCraft.getInstance().getStorageManager().savePlayer(event.getPlayer());


    }

}
