package com.yerti.runecraft.listeners;

import com.yerti.runecraft.RuneCraft;
import com.yerti.runecraft.core.player.RunePlayer;
import com.yerti.runecraft.skills.SkillType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreakEvent implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        RunePlayer player = RunePlayer.getPlayer(event.getPlayer());



    }

}
