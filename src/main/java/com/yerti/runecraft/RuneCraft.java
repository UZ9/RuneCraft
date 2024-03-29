package com.yerti.runecraft;

import com.yerti.runecraft.core.YertiPlugin;
import com.yerti.runecraft.commands.RuneCraftCommand;
import com.yerti.runecraft.listeners.OnBlockBreakEvent;
import com.yerti.runecraft.listeners.PlayerEvent;
import com.yerti.runecraft.managers.ChatManager;
import com.yerti.runecraft.player.RunePlayer;
import com.yerti.runecraft.runnables.PlayerSavingTask;
import com.yerti.runecraft.storage.MySQLSkillStorage;
import com.yerti.runecraft.storage.StorageManager;
import com.yerti.runecraft.utils.StackChanger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RuneCraft extends YertiPlugin {

    public static final String playerDataKey = "RuneCraft Profile";
    private List<RunePlayer> playerData = new ArrayList<>();

    private static RuneCraft instance;
    private boolean debug;
    private StorageManager storageManager;
    private ChatManager manager;


    @Override
    public void onEnable() {
        //Load Java Custom Command Annotations
        load(RuneCraftCommand.class);

        instance = this;

        //Register Commands
        registerCommands();

        //Register events
        registerEvents();

        //Load Config
        loadConfig();

        //Until flatfile is implemented, use database
        storageManager = new MySQLSkillStorage(this);

        storageManager.setup();

        for (Player player : Bukkit.getOnlinePlayers()) {
            playerData.add(new RunePlayer(player));
            storageManager.loadPlayer(player);

        }

        startRunnables();

        new StackChanger();
        new PlayerEvent();




    }

    @Override
    public void onDisable() {

    }

    //Most likely will be removed after new command update to core
    private void registerCommands() {

    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new OnBlockBreakEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEvent(), this);
    }

    private void loadConfig() {
        debug = getConfig().getBoolean("debug-mode");
    }

    private void startRunnables() {
        //5 minute save to mysql
        Bukkit.getScheduler().runTaskTimer(this, new PlayerSavingTask(),  20L * 60L, 20L * 60L * 5L);
    }

    public static RuneCraft getInstance() {
        return instance;
    }

    public StorageManager getStorageManager() {
        return storageManager;
    }

    public List<RunePlayer> getPlayers() {
        return playerData;
    }

    public boolean debugMode() {
        return debug;
    }
}
