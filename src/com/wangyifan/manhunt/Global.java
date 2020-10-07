package com.wangyifan.manhunt;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Global {
    public static List<Player> spectatorList = new ArrayList<>();
    public static List<Player> hunterList = new ArrayList<>();
    public static List<Player> runnerList = new ArrayList<>();
    public static Boolean game = false;
    public static Boolean freezeRunners = false;
    public static Boolean freezeHunters = false;

}
