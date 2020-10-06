package com.wangyifan.manhunt;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManHunt implements CommandExecutor {
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
                        StringBuilder s1 = new StringBuilder("§cHunters: ");
                        if (Global.hunterList.isEmpty()) {
                            s1.append("None");
                        } else {
                            for (Player player1 : Global.hunterList) {
                                s1.append(player1.getName() + ", ");
                            }
                            s1.delete(s1.length()-3, s1.length()-1);
                        }
                        player.sendMessage(s1.toString());
                        s1 = new StringBuilder("§bRunners: ");
                        if (Global.runnerList.isEmpty()) {
                            s1.append("None");
                        } else {
                            for (Player player1 : Global.runnerList) {
                                s1.append(player1.getName() + ", ");
                            }
                            s1.delete(s1.length()-3, s1.length()-1);
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
                        break;
                }
        }

        return true;
    }
}
