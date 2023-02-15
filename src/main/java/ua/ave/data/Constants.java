package ua.ave.data;

import org.bukkit.plugin.Plugin;
import ua.ave.config.Config;
import ua.ave.eventHandlers.ShardLightningEvent;
import ua.ave.eventHandlers.ShardSpawnEvent;
import ua.ave.eventHandlers.TeamWinCountDownEvent;
import ua.ave.helpers.GuaranteedRandom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

    public static Plugin plugin = null;
    public static TeamScoreBoard teamScoreBoard = null;
    public static boolean isGameRunning = false;
    public static boolean isGamePaused = false;
    public static long lastPause = 0;
    public static TeamWinCountDownEvent teamWinCountDownEvent = null;
    public static ShardSpawnEvent shardSpawnEvent = null;
    public static ShardLightningEvent shardLightningEvent = null;
    public static Config config = new Config();

    public static String[] phrases = new String[]{
            "НАБИРАЕТ ПРЕВОСХОДСТВО",
            "DOUBLE KILL",
            "TRIPLE KILL",
            "ULTRA KILL",
            "RAMPAGE",
            "ZUILIKE",
    };

    public static Map<String, Integer> kills = new HashMap<>();
    public static List<String> teamRed = new ArrayList<>();
    public static List<String> teamBlue = new ArrayList<>();
}
