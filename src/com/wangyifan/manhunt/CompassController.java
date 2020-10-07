package com.wangyifan.manhunt;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

public class CompassController {
    ManHunt plugin;
    public CompassController(ManHunt plugin) {
        this.plugin = plugin;
    }
    public Player getNearestRunner(Player player) {
        double minDistance = 0.0f;
        Player ret = null;
        Location location = player.getLocation();
        for (Player playerI : Global.runnerList) {
            if (playerI == player) continue;
            if (playerI.getWorld() != player.getWorld()) continue;
            if (playerI.getWorld().getName().toLowerCase().contains("nether")) continue;
            Location locationI = playerI.getLocation();
            double dist = location.distance(locationI);
            if (ret == null || dist < minDistance) {
                ret = playerI;
                minDistance = dist;
            }
        }
        return ret;
    }

    public Player redirectCompassToNearestRunner(Player player) {
        Player target = getNearestRunner(player);
        if (target != null) {
            player.setCompassTarget(target.getLocation());
        }
        return target;
    }

    public void redirectCompassToStronghold(Player player) {
        player.setCompassTarget(new Location(player.getWorld(), 0, 0, 0));
    }

    public void redirectCompass(Player player, Location location) {
        player.setCompassTarget(location);
    }
}
