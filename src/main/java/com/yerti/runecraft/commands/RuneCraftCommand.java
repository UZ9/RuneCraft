package com.yerti.runecraft.commands;

import com.yerti.core.command.CustomCommand;
import com.yerti.core.command.SubCommand;
import com.yerti.core.utils.CenterFontUtil;
import com.yerti.runecraft.managers.ChatManager;
import com.yerti.runecraft.player.RunePlayer;
import com.yerti.runecraft.skills.SkillType;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RuneCraftCommand {

    @CustomCommand(name = "runecraft", permission = "", aliases = "rc", description = "Base rCusfesftomCommand for runecraft", usage = "/runecraft")
    public void runecraftCommand(CommandSender sender, org.bukkit.command.Command command, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("Only players are allowed to use this command!");
            return;
        }

        Player player = (Player) sender;

        ChatManager.info(player, "RuneCraft Plugin, developed by Yerti.");


    }

    @SubCommand(parent = "runecraft", name = "wack", permission = "", usage = "/runecraft wack", description = "Does stuff")
    public void wackCommand(CommandSender sender, Command command, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("Only players are allowed to use this command!");
            return;
        }

        Player player = (Player) sender;

    }

    @SubCommand(parent = "runecraft", name = "stats", permission = "", usage = "/runecraft stats", description = "Shows the stats for the player.")
    public void statsCommand(CommandSender sender, Command command, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("Only players are allowed to use this command!");
            return;
        }

        RunePlayer player = RunePlayer.getPlayer((Player) sender);
        Player p = null;
        if (player != null) {
            p = player.getPlayer();
        } else {
            ChatManager.error((Player) sender, "An error with your player seemed to have occured. Please try relogging.");
            return;
        }

        CenterFontUtil.sendCenteredMessage(p, "&a&m-------------------------------------------------");
        CenterFontUtil.sendCenteredMessage(p, "&f&lStats");
        CenterFontUtil.sendCenteredMessage(p, "");

        for (SkillType skillType : player.getLevelManager().getLevelsXp().keySet()) {
            CenterFontUtil.sendCenteredMessage(p, "&e" + StringUtils.capitalize(skillType.toString().toLowerCase()) + ": &6" + player.getLevelManager().getXp(skillType) + "xp");
        }
        CenterFontUtil.sendCenteredMessage(p, "&a&m-------------------------------------------------");

    }

}
