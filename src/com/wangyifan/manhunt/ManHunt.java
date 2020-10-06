package com.wangyifan.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ManHunt extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("ManHunt plugin enabled!\n");
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginCommand("hunt").setExecutor(new CommandManHunt());
    }

    @Override
    public void onDisable() {
        getLogger().info("ManHunt plugin disabled!");
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
    }
}
