package com.yerti.runecraft.listeners;

import com.yerti.core.items.CustomItemStack;
import com.yerti.core.titles.ActionBar;
import com.yerti.core.titles.Title;
import com.yerti.core.utils.Utilities;
import com.yerti.runecraft.RuneCraft;
import com.yerti.runecraft.managers.ChatManager;
import com.yerti.runecraft.player.RunePlayer;
import com.yerti.runecraft.skills.SkillType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class PlayerEvent implements Listener {

    public PlayerEvent() {
        startInteractionRunnable();
    }

    private Map<UUID, Integer> interactionCooldown = new HashMap<>();

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

        RuneCraft.getInstance().getStorageManager().savePlayer(event.getPlayer());
        RuneCraft.getInstance().getPlayers().remove(new RunePlayer(event.getPlayer()));

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        RuneCraft.getInstance().getPlayers().add(new RunePlayer(event.getPlayer()));
        RuneCraft.getInstance().getStorageManager().savePlayer(event.getPlayer());


    }

    @EventHandler
    public void onPlayerInteraact(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;

        Player player = event.getPlayer();

        if (player.getItemInHand().getType().equals(Material.DIAMOND_PICKAXE)) {
            if (event.getClickedBlock().getType().equals(Material.IRON_ORE)) {
                if (!interactionCooldown.containsKey(player.getUniqueId())) {
                    event.getClickedBlock().setType(Material.STONE);
                    interactionCooldown.put(player.getUniqueId(), 5);
                    Bukkit.getScheduler().runTaskLater(RuneCraft.getInstance(), () -> event.getClickedBlock().setType(Material.IRON_ORE), 100L);

                    RunePlayer rPlayer = RunePlayer.getPlayer(event.getPlayer());
                    rPlayer.getManager(SkillType.MINING).addXp(1);
                    new ActionBar(ChatColor.GRAY + "You swing your mighty pickaxe, granting you " + ChatColor.YELLOW + "1" + ChatColor.GRAY +  " xp.").sendToPlayer(player);

                    player.getInventory().addItem(new CustomItemStack(Material.IRON_ORE, 1).lore(Utilities.convertToInvisibleString(new Random().nextInt(100000) + "")));

                } else {

                    //new ActionBar(ChatColor.RED + "You need to wait to do that operation again.").sendToPlayer(player);
                }
            }
        }
    }

    public void startInteractionRunnable() {
        Bukkit.getScheduler().runTaskTimer(RuneCraft.getInstance(), () -> {
            for (Map.Entry<UUID, Integer> entry : interactionCooldown.entrySet()) {
                interactionCooldown.put(entry.getKey(), entry.getValue() - 1);
                if (interactionCooldown.get(entry.getKey()) == 0) interactionCooldown.remove(entry.getKey());
            }
        }, 20L, 20L);
    }

}
