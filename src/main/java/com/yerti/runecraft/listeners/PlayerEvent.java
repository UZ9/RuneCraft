package com.yerti.runecraft.listeners;

import com.yerti.runecraft.core.player.RunePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEvent {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //Create profile if it doesn't exist
        RunePlayer.getPlayer(event.getPlayer());





    }

}
