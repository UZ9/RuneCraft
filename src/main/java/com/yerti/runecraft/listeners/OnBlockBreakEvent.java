package com.yerti.runecraft.listeners;

import com.yerti.runecraft.RuneCraft;
import com.yerti.runecraft.managers.ChatManager;
import com.yerti.runecraft.player.RunePlayer;
import com.yerti.runecraft.skills.SkillType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreakEvent implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();

        if (player == null) return;
        Material hand = player.getItemInHand().getType();
        if (!hand.equals(Material.DIAMOND_PICKAXE) && !hand.equals(Material.GOLD_PICKAXE) && !hand.equals(Material.STONE_PICKAXE) && !hand.equals(Material.IRON_PICKAXE) && !hand.equals(Material.WOOD_PICKAXE)) return;

        //TODO: Add a giant list of all the xp rates.
        if (event.getBlock().getType().equals(Material.STONE)) {
            RunePlayer rPlayer = RunePlayer.getPlayer(event.getPlayer());
            rPlayer.getManager(SkillType.MINING).addXp(1);
            ChatManager.info(player, "You swing your mighty pickaxe, granting you &e1 &7xp.");
        }


    }

}
