package com.yerti.runecraft.listeners;

import com.yerti.runecraft.player.RunePlayer;
import com.yerti.runecraft.RuneCraft;
import com.yerti.runecraft.storage.StorageManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Level;

public class PlayerEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (RuneCraft.getInstance().debugMode()) Bukkit.getLogger().log(Level.INFO,  "Adding player to database..");

        //Create profile if it doesn't exist
        RuneCraft.getInstance().getStorageManager().savePlayer(event.getPlayer());





    }

}
