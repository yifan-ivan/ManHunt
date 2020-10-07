package com.wangyifan.manhunt.Events;

import com.wangyifan.manhunt.CompassController;
import com.wangyifan.manhunt.Config;
import com.wangyifan.manhunt.Global;
import com.wangyifan.manhunt.ManHunt;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;

import java.util.Collection;

public class EventsHandler implements Listener {
    ManHunt plugin;

    public EventsHandler(ManHunt plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!Global.spectatorList.contains(player)) {
            Global.spectatorList.add(player);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Global.spectatorList.remove(player);
        Global.hunterList.remove(player);
        Global.runnerList.remove(player);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (Global.runnerList.contains(player)) {
            Global.runnerList.remove(player);
            Global.spectatorList.add(player);
            player.setGameMode(GameMode.SPECTATOR);
            if (Global.runnerList.isEmpty()) {
                // Game Over : Hunters win
                plugin.getServer().broadcastMessage("§aGame Over! Hunters win!");
                Global.game = false;
            } else {
                plugin.getServer().broadcastMessage("§c" + player.getName() + " eliminated! " + Global.runnerList.size() + " runners left!");
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (Global.hunterList.contains(player) && Global.freezeHunters) event.setCancelled(true);
        if (Global.runnerList.contains(player) && Global.freezeRunners) event.setCancelled(true);

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (Global.hunterList.contains(player) && Global.freezeHunters) event.setCancelled(true);
        if (Global.runnerList.contains(player) && Global.freezeRunners) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (Global.hunterList.contains(player) && Global.freezeHunters) event.setCancelled(true);
        if (Global.runnerList.contains(player) && Global.freezeRunners) event.setCancelled(true);
    }


    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getType() == Material.COMPASS) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        if (event.getRecipe().getResult().getType() == Material.COMPASS) {
            event.setCancelled(true);
            event.getWhoClicked().closeInventory();
            event.getWhoClicked().sendMessage("§cYou are not allowed to craft compasses!");
        }
    }

    @EventHandler
    public void onPlayerHeldItem(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getPlayer().getInventory().getItem(event.getNewSlot());
        if (itemStack == null) {
            return;
        }
        CompassController compassController = new CompassController(plugin);
        if (itemStack.getType() == Material.COMPASS) {
            if (Global.hunterList.contains(player)) {
                Player target = compassController.redirectCompassToNearestRunner(player);
                if (target == null) {
                    player.sendMessage("§cNo player to tracking!");
                } else {
                    player.sendMessage("§aCompass is now pointing to " + target.getName());
                }
            }
            if (Global.runnerList.contains(player)) {
                player.sendMessage("§aCompass is now pointing to the stronghold");
            }
        }
    }
}
