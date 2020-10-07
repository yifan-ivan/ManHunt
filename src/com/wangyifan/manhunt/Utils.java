package com.wangyifan.manhunt;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class Utils {
    public static void sendHelpMessage(Player player) {
        player.sendMessage("§cWorking...");
    }
    public static void removeAllPotionEffect(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }
}
