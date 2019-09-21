package com.yerti.runecraft.managers;

import com.yerti.runecraft.RuneCraft;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatManager {

    private Player player;
    String prefix;

    public ChatManager(Player player) {
        this.player = player;
        prefix = ChatColor.translateAlternateColorCodes('&', RuneCraft.getInstance().getConfig().getString("message-prefix"));
    }

    public void success(String message) {
        player.sendMessage(prefix + " " + ChatColor.DARK_GRAY + "\u00BB " + ChatColor.GREEN + message);
    }

    public void error(String message) {
        player.sendMessage(prefix + " " + ChatColor.DARK_GRAY + "\u00BB " + ChatColor.RED + message);
    }

    public void warning(String message) {
        player.sendMessage(prefix + " " + ChatColor.DARK_GRAY + "\u00BB " + ChatColor.YELLOW + message);
    }

    public void info(String message) {
        player.sendMessage(prefix + " " + ChatColor.DARK_GRAY + "\u00BB " + ChatColor.GRAY + message);
    }




}
