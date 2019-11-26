package com.yerti.runecraft.commands;

import com.yerti.core.prototype.command.CustomCommand;
import com.yerti.runecraft.managers.ChatManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RuneCraftCommand {

    @CustomCommand(name = "runecraft", permission = "", aliases = "rc", description = "Base CustomCommand for runecraft", usage = "/runecraft")
    public void set(CommandSender sender,  org.bukkit.command.Command command, String[] args) {

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
