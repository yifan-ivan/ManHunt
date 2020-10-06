package com.wangyifan.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CompassController {
    ManHunt plugin;
    public CompassController(ManHunt plugin) {
        this.plugin = plugin;
    }
    public Player getNearestPlayer(Player player) {
        double minDistance = 0.0f;
        Player ret = null;
        Location location = player.getLocation();
        for (Player playerI : plugin.getServer().getOnlinePlayers()) {
            if (playerI == player) continue;
            if (playerI.getWorld() != player.getWorld()) continue;
            Location locationI = playerI.getLocation();
            double dist = location.distance(locationI);
            if (ret == null || dist < minDistance) {
                ret = playerI;
                minDistance = dist;
            }
        }
        return ret;
    }

    public void redirectCompassToNearestPlayer(Player player) {
        Player target = getNearestPlayer(player);
        if (target != null) {
            player.setCompassTarget(target.getLocation());
        }

    }

    public void redirectCompassToStronghold(Player player) {
        Player target = getNearestPlayer(player);
        if (target != null) {
            player.setCompassTarget(new Location(player.getWorld(), 0, 0, 0));
        }

    }
}
