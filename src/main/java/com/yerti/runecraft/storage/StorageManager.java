package com.yerti.runecraft.storage;

import com.yerti.runecraft.managers.PlayerSkillManager;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface StorageManager {

    /**
     * Initializes any needed files or databases
     */
    void setup();
    /**
     * Saves a player's skills to the designated storage
     * @param skills
     */
    void savePlayer(Player skills);

    /**
     * Cleans/Removes a player from the storage
     * @param player
     */
    void removePlayer(Player player);

    /**
     * Retrieves skills and information from a UUID
     * @param id
     * @return
     */
    PlayerSkillManager retrieveManager(UUID id);





}
