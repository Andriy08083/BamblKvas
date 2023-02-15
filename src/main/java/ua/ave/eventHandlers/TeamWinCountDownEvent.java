package ua.ave.eventHandlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ua.ave.helpers.TeamHelper;

import static ua.ave.data.Constants.*;

public class TeamWinCountDownEvent extends BukkitRunnable {

    int secondsToWin = 15;

    @Override
    public void run() {
        int blueTeamShardAmount = TeamHelper.calculateAmountOfShardsInTeam(teamBlue);
        int redTeamShardAmount = TeamHelper.calculateAmountOfShardsInTeam(teamRed);
        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
            onlinePlayer.sendTitle(ChatColor.RED + String.valueOf(secondsToWin), "", 1, 20, 1);
        }
        if (secondsToWin == 0) {
            if (redTeamShardAmount > blueTeamShardAmount) {
                for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                    onlinePlayer.sendTitle(ChatColor.RED + "Team red won", "", 1, 70, 1);
                }
            } else if (redTeamShardAmount < blueTeamShardAmount) {
                for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                    onlinePlayer.sendTitle(ChatColor.BLUE + "Team blue won", "", 1, 70, 1);
                }
            } else {
                for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
                    onlinePlayer.sendTitle(ChatColor.GREEN + "Draw", "", 1, 70, 1);
                }
            }
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "stopgame");
            teamWinCountDownEvent = null;
            cancel();
        }
        secondsToWin--;
    }
}
