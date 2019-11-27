package com.yerti.runecraft.runnables;

import com.yerti.runecraft.RuneCraft;
import com.yerti.runecraft.player.RunePlayer;

public class PlayerSavingTask implements Runnable {
    @Override
    public void run() {
        for (RunePlayer player : RuneCraft.getInstance().getPlayers()) {
            RuneCraft.getInstance().getStorageManager().savePlayer(player.getPlayer());
        }
    }
}
