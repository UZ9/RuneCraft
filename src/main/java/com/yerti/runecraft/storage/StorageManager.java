package com.yerti.runecraft.storage;

import com.yerti.runecraft.core.player.RunePlayer;
import com.yerti.runecraft.managers.PlayerSkillManager;

import java.sql.SQLException;

public interface StorageManager {

    /**
     * Initializes any needed files or databases
     */
    void setup();
    /**
     * Saves a player's skills to the designated storage
     * @param skills
     */
    void savePlayer(PlayerSkillManager skills);

    /**
     * Cleans/Removes a player from the storage
     * @param player
     */
    void removePlayer(RunePlayer player);


    /**
     * Creates a player if it does not exist
     * @param player
     */
    void createPlayer(RunePlayer player);




}
