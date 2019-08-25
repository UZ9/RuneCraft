package com.yerti.runecraft;

import com.yerti.runecraft.managers.Skills;
import com.yerti.runecraft.storage.MySQLSkillStorage;
import com.yerti.runecraft.storage.StorageManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RuneCraft extends JavaPlugin {

    private static RuneCraft instance;

    //Stores the skills of each player
    Map<UUID, Skills> skillManagers;

    public void onEnable() {
        instance = this;

        //Register Commands
        registerCommands();

        //Register events
        registerEvents();

        //Load Config
        loadConfig();

        //Setup skills
        skillManagers = new HashMap<>();

        //Until flatfile is implemented, use database
        StorageManager storageManager = new MySQLSkillStorage(this);

        storageManager.setup();
    }

    public void onDisable() {

    }

    private void registerCommands() {

    }

    private void registerEvents() {

    }

    private void loadConfig() {

    }

    public static RuneCraft getInstance() {
        return instance;
    }

}
