package com.yerti.runecraft.listeners;

import com.yerti.core.player.RunePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreakEvent implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        RunePlayer player = RunePlayer.getPlayer(event.getPlayer());



    }

}
