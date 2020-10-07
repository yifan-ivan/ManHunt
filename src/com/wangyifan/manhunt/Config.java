package com.wangyifan.manhunt;

public class Config {
    public static boolean compassTrackingRunners = true;
    public static boolean compassTrackingStronghold = false;
    public static int runnerAheadSecond = 10;
    public static int runnerSpeed = 0;
    public static int hunterSpeed = 0;
    public static int runnerResistance = 0;
    public static int hunterResistance = 0;

    public Config(ManHunt plugin) {
        plugin.reloadConfig();
        plugin.getConfig().options().copyDefaults();
        plugin.saveConfig();
        compassTrackingRunners = (boolean) plugin.getConfig().get("compassTrackingRunners");
        compassTrackingStronghold = (boolean) plugin.getConfig().get("compassTrackingStronghold");
        runnerAheadSecond = (int) plugin.getConfig().get("runnerAheadSecond");
        runnerSpeed = (int) plugin.getConfig().get("runnerSpeed");
        hunterSpeed = (int) plugin.getConfig().get("hunterSpeed");
        runnerResistance = (int) plugin.getConfig().get("runnerResistance");
        hunterResistance = (int) plugin.getConfig().get("hunterResistance");
    }
}
