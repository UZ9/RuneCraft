package com.yerti.runecraft.commands;

import com.yerti.core.command.CustomCommand;
import com.yerti.core.command.SubCommand;
import com.yerti.runecraft.managers.ChatManager;
import com.yerti.runecraft.player.RunePlayer;
import com.yerti.runecraft.skills.SkillType;
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


        RunePlayer runePlayer = RunePlayer.getPlayer(player);

        ChatManager.info(player, player.getName() + " has " + ChatColor.YELLOW + runePlayer.getLevelManager().getLevelsXp().get(SkillType.MINING) + ChatColor.GRAY + " levels in mining.");
    }

    @SubCommand(parent = "runecraft", name = "wack2", permission = "", usage = "/runecraft wack", description = "Does stuff")
    public void wackCommand2(CommandSender sender, Command command, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("Only players are allowed to use this command!");
            return;
        }

        Player player = (Player) sender;

        ChatManager.info(player, "You have used the wack command. Epic!");
    }

}
