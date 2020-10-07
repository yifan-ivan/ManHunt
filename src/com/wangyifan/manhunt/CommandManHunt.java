package com.wangyifan.manhunt;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Ambient;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class CommandManHunt implements CommandExecutor {
    ManHunt plugin;
    public CommandManHunt(ManHunt plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("§cYou are not a player! You cannot use this command!");
            return true;
        }

        Player player = (Player) commandSender;

        switch (strings.length) {
            case 0:
                Utils.sendHelpMessage(player);
                break;
            case 1:
                switch (strings[0]) {
                    case "status":
                        player.sendMessage("§6Game in progress: " + Global.game);
                        StringBuilder s1 = new StringBuilder("§cHunters: ");
                        if (Global.hunterList.isEmpty()) {
                            s1.append("None");
                        } else {
                            for (Player player1 : Global.hunterList) {
                                s1.append(player1.getName() + ", ");
                            }
                            s1.delete(s1.length()-2, s1.length()-1);
                        }
                        player.sendMessage(s1.toString());
                        s1 = new StringBuilder("§bRunners: ");
                        if (Global.runnerList.isEmpty()) {
                            s1.append("None");
                        } else {
                            for (Player player1 : Global.runnerList) {
                                s1.append(player1.getName() + ", ");
                            }
                            s1.delete(s1.length()-2, s1.length()-1);
                        }
                        player.sendMessage(s1.toString());
                        s1 = new StringBuilder("§7Spectators: ");
                        if (Global.spectatorList.isEmpty()) {
                            s1.append("None");
                        } else {
                            for (Player player1 : Global.spectatorList) {
                                s1.append(player1.getName() + ", ");
                            }
                            s1.delete(s1.length()-2, s1.length()-1);
                        }
                        player.sendMessage(s1.toString());
                        break;
                    case "start":
                        // Start the game.
                        new Config(plugin);
                        Global.game = true;
                        plugin.getServer().broadcastMessage("§6 Game Start!");
                        for (Player player1 : Global.hunterList) {
                            player1.setGameMode(GameMode.SURVIVAL);
                            player1.getInventory().clear();
                            Utils.removeAllPotionEffect(player1);
                            if (Config.compassTrackingRunners) {
                                player1.sendMessage("§aThe compass will track the nearest runner if you hold it in your main hand.");
                                player1.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                            }
                            if (Config.hunterSpeed > 0) {
                                player1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, Config.hunterSpeed - 1));
                            }
                            if (Config.hunterResistance > 0) {
                                player1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000, Config.hunterSpeed - 1));
                            }
                        }
                        for (Player player1 : Global.runnerList) {
                            player1.setGameMode(GameMode.SURVIVAL);
                            player1.getInventory().clear();
                            Utils.removeAllPotionEffect(player1);
                            if (Config.compassTrackingStronghold) {
                                player1.sendMessage("§aThe compass will track the nearest stronghold if you hold it in your main hand.");
                                player1.getInventory().setItem(8, new ItemStack(Material.COMPASS));
                            }
                            if (Config.runnerSpeed > 0) {
                                player1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, Config.runnerSpeed - 1));
                            }
                            if (Config.runnerResistance > 0) {
                                player1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000, Config.runnerResistance - 1));
                            }
                        }
                        for (Player player1 : Global.spectatorList) {
                            player1.setGameMode(GameMode.SPECTATOR);
                        }
                        break;
                }
            case 2:
                if (strings[0].equals("join")) {
                    switch (strings[1]) {
                        case "hunter":
                            Global.spectatorList.remove(player);
                            Global.runnerList.remove(player);
                            if (!Global.hunterList.contains(player)) {
                                Global.hunterList.add(player);
                            }
                            plugin.getServer().broadcastMessage("§6" + player.getName() + " §ajoined §cHunters");
                            break;
                        case "runner":
                            Global.spectatorList.remove(player);
                            Global.hunterList.remove(player);
                            if (!Global.runnerList.contains(player)) {
                                Global.runnerList.add(player);
                            }
                            plugin.getServer().broadcastMessage("§6" + player.getName() + " §ajoined §bRunners");
                            break;
                        case "spectator":
                            Global.hunterList.remove(player);
                            Global.runnerList.remove(player);
                            if (!Global.spectatorList.contains(player)) {
                                Global.spectatorList.add(player);
                            }
                            plugin.getServer().broadcastMessage("§6" + player.getName() + " §ajoined §7Spectators");
                            break;
                    }
                }
        }

        return true;
    }
}
