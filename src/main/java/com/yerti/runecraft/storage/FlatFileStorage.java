package com.yerti.runecraft.storage;

import com.yerti.runecraft.core.player.RunePlayer;
import com.yerti.runecraft.managers.PlayerSkillManager;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.UUID;

//TODO: Finish FlatFile
public class FlatFileStorage implements StorageManager {

    private File playerStorage;

    public FlatFileStorage(Plugin plugin) {

        playerStorage = new File(plugin.getDataFolder() + "/RuneCraft/");


    }


    @Override
    public void setup() {

    }

    @Override
    public void savePlayer(PlayerSkillManager skills) {






    }

    @Override
    public void removePlayer(PlayerSkillManager player) {

    }

    @Override
    public PlayerSkillManager retrieveManager(UUID id) {
        return null;
    }

    @Override
    public void createPlayer(PlayerSkillManager player) {

    }
}
