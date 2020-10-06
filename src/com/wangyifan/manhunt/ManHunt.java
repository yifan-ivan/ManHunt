package com.wangyifan.manhunt;

import com.wangyifan.manhunt.Events.EventsHandler;
import com.wangyifan.manhunt.Events.onTick;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ManHunt extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("ManHunt plugin enabled!\n");
        Bukkit.getPluginManager().registerEvents(new EventsHandler(), this);
        Bukkit.getPluginCommand("hunt").setExecutor(new CommandManHunt(this));
        new onTick(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("ManHunt plugin disabled!");
    }
}
