package com.yerti.runecraft;

import com.yerti.runecraft.core.prototype.YertiPlugin;
import com.yerti.runecraft.listeners.OnBlockBreakEvent;
import com.yerti.runecraft.managers.ChatManager;
import com.yerti.runecraft.storage.MySQLSkillStorage;
import com.yerti.runecraft.storage.StorageManager;
import org.bukkit.Bukkit;

public class RuneCraft extends YertiPlugin {

    public static final String playerDataKey = "RuneCraft Profile";

    private static RuneCraft instance;
    private boolean debug;
    private StorageManager storageManager;
    private ChatManager manager;


    @Override
    public void onEnable() {
        //Load Java Command Annotations
        load();

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

    @Override
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
