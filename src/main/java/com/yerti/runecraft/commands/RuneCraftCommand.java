package com.yerti.runecraft.commands;

import com.yerti.core.prototype.command.CustomCommand;
import com.yerti.core.prototype.command.SubCommand;
import com.yerti.runecraft.managers.ChatManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RuneCraftCommand {

    @CustomCommand(name = "runecraft", permission = "", aliases = "rc", description = "Base CustomCommand for runecraft", usage = "/runecraft")
    public void runecraftCommand(CommandSender sender, org.bukkit.command.Command command, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("Only players are allowed to use this command!");
            return;
        }

        Player player = (Player) sender;


        ChatManager manager = new ChatManager(player);
        manager.info("RuneCraft Plugin, developed by Yerti.");


    }

    @SubCommand(parent = "runecraft", name = "wack", permission = "", usage = "/runecraft wack", description = "Does stuff")
    public void wackCommand(CommandSender sender, Command command, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("Only players are allowed to use this command!");
            return;
        }

        Player player = (Player) sender;

        ChatManager manager = new ChatManager(player);
        manager.info("You have used the wack command. Epic!");
    }

    @SubCommand(parent = "runecraft", name = "wack2", permission = "", usage = "/runecraft wack", description = "Does stuff")
    public void wackCommand2(CommandSender sender, Command command, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("Only players are allowed to use this command!");
            return;
        }

        Player player = (Player) sender;

        ChatManager manager = new ChatManager(player);
        manager.info("You have used the wack command. Epic!");
    }

}
