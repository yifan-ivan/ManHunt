package com.wangyifan.manhunt.Events;

import com.wangyifan.manhunt.CompassController;
import com.wangyifan.manhunt.ManHunt;

public class onTick {
    public onTick(ManHunt plugin) {
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            // plugin.getServer().broadcastMessage("REEEEE");
            // new CompassController(plugin).redirectCompassToNearestPlayer();
            CompassController compassController = new CompassController(plugin);
        },1L,1L);
    }
}
