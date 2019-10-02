package com.yerti.runecraft.commands;

import com.yerti.runecraft.RuneCraft;
import com.yerti.runecraft.core.prototype.command.Command;
import com.yerti.runecraft.managers.ChatManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RuneCraftCommand {

    @Command(name = "runecraft", permission = "", aliases = "rc", description = "Base Command for runecraft", usage = "/runecraft")
    public void set(CommandSender sender,  Command command, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("Only players are allowed to use this command!");
            return;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            ChatManager manager = new ChatManager(player);
            manager.info("RuneCraft Plugin, developed by Yerti.");
        }



    }

}
