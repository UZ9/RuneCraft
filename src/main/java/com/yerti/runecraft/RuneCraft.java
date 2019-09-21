package com.yerti.runecraft;

import com.yerti.runecraft.listeners.OnBlockBreakEvent;
import com.yerti.runecraft.managers.Skills;
import com.yerti.runecraft.storage.MySQLSkillStorage;
import com.yerti.runecraft.storage.StorageManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RuneCraft extends JavaPlugin {

    private static RuneCraft instance;
    private boolean debug;
    private StorageManager storageManager;


    public void onEnable() {
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
    }

    public void onDisable() {

    }

    //Most likely will be removed after new command update to core
    private void registerCommands() {

    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new OnBlockBreakEvent(), this);
    }

    private void loadConfig() {
        debug = getConfig().getBoolean("debug-mode");
    }

    public static RuneCraft getInstance() {
        return instance;
    }

    public StorageManager getStorageManager() {
        return storageManager;
    }

    public boolean debugMode() {
        return debug;
    }
}
