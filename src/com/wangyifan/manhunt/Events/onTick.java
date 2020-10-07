package com.wangyifan.manhunt.Events;

import com.wangyifan.manhunt.CompassController;
import com.wangyifan.manhunt.Config;
import com.wangyifan.manhunt.Global;
import com.wangyifan.manhunt.ManHunt;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class onTick {
    public onTick(ManHunt plugin) {
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            CompassController compassController = new CompassController(plugin);
            if (Config.compassTrackingRunners) {
                for (Player player : Global.hunterList) {
                    if (player.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
                        compassController.redirectCompassToNearestRunner(player);
                    }
                }
                for (Player player : Global.runnerList) {
                    if (player.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
                        compassController.redirectCompassToStronghold(player);
                    }
                }
            }
        },1L,1L);
    }
}
