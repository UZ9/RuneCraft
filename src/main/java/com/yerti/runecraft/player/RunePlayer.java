package com.yerti.runecraft.player;

import com.yerti.runecraft.RuneCraft;
import com.yerti.runecraft.managers.PlayerSkillManager;
import com.yerti.runecraft.skills.SkillManager;
import com.yerti.runecraft.skills.SkillType;
import org.bukkit.entity.Player;

import java.util.*;

public class RunePlayer {

    private Player player;
    private PlayerSkillManager levelManager;
    private Map<SkillType, SkillManager> skills = new HashMap<>();

    public RunePlayer(Player player, PlayerSkillManager levelManager) {
        this.player = player;
        this.levelManager = levelManager;

        //Put all managers in
        for (SkillType type : SkillType.values()) {
            try {
                skills.put(type, type.getManager().getConstructor(RunePlayer.class).newInstance(this));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public RunePlayer(Player player) {
        this.player = player;

        this.levelManager = RuneCraft.getInstance().getStorageManager().retrieveManager(player.getUniqueId());
        //RuneCraft.getInstance().getStorageManager().savePlayer(player);
        for (SkillType type : SkillType.values()) {
            try {
                skills.put(type, type.getManager().getConstructor(RunePlayer.class).newInstance(this));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public UUID getUniqueID() {
        return player.getUniqueId();
    }

    public PlayerSkillManager getLevelManager() {
        return levelManager;
    }

    public SkillManager getManager(SkillType type) {
        SkillManager manager = skills.get(type);
        if (manager == null) throw new NullPointerException();
        return manager;
    }

    public void setLevelManager(PlayerSkillManager levelManager) {
        this.levelManager = levelManager;
    }

    public static RunePlayer getPlayer(Player p) {

        Optional<RunePlayer> optional = RuneCraft.getInstance().getPlayers().stream().filter(targetPlayer -> targetPlayer.getPlayer().equals(p)).findFirst();

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NullPointerException();
        }

        /*for (RunePlayer player : RuneCraft.getInstance().getPlayers()) {
            if (player.getPlayer().equals(p)) return player;
        }

        return null;*/

    }



    public Player getPlayer() {
        return player;
    }
}
