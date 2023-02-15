package ua.ave.data;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import ua.ave.eventHandlers.TeamWinCountDownEvent;
import ua.ave.helpers.TeamHelper;

import static ua.ave.data.Constants.*;

public class TeamScoreBoard extends BukkitRunnable {
    public Scoreboard scoreboard;

    int blueTeamShardAmount;
    int redTeamShardAmount;
    int secondsToWin = 15;

    public TeamScoreBoard() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        scoreboard = manager.getNewScoreboard();

        scoreboard.registerNewTeam(Team.RED.name());
        scoreboard.registerNewTeam(Team.BLUE.name());

        scoreboard.registerNewObjective("amethyst", "dummy", "Shards");
        scoreboard.registerNewObjective("health", "dummy", ChatColor.GREEN + "HP");
        scoreboard.registerNewObjective("playerAmethyst", "dummy", ChatColor.GREEN + "Shards");
        scoreboard.getObjective("amethyst").setDisplaySlot(DisplaySlot.SIDEBAR);
        scoreboard.getObjective("health").setDisplaySlot(DisplaySlot.PLAYER_LIST);
        scoreboard.getObjective("playerAmethyst").setDisplaySlot(DisplaySlot.BELOW_NAME);
        scoreboard.getTeam(Team.BLUE.name()).setColor(ChatColor.BLUE);
        scoreboard.getTeam(Team.RED.name()).setColor(ChatColor.RED);

        for (org.bukkit.scoreboard.Team team : scoreboard.getTeams()) {
            team.setAllowFriendlyFire(false);
            team.setCanSeeFriendlyInvisibles(true);
        }

    }

    public void addPlayer(Player player, Team team) {
        if (!scoreboard.getTeam(team.name()).hasEntry(player.getName())) {
            scoreboard.getTeam(team.name()).addEntry(player.getName());
        }
    }

    public void removePlayer(Player player) {
        scoreboard.getTeams().forEach(team -> {
            team.removeEntry(player.getName());
        });
    }

    public void updateScoreboardForAllPlayers() {
        blueTeamShardAmount = TeamHelper.calculateAmountOfShardsInTeam(teamBlue);
        redTeamShardAmount = TeamHelper.calculateAmountOfShardsInTeam(teamRed);
        scoreboard.getObjective("amethyst").getScore(String.format("%sBlue:%s", ChatColor.BLUE, ChatColor.RESET)).setScore(blueTeamShardAmount);
        scoreboard.getObjective("amethyst").getScore(String.format("%sRed:%s", ChatColor.RED, ChatColor.RESET)).setScore(redTeamShardAmount);
        plugin.getServer().getOnlinePlayers().forEach(onlinePlayer -> {
            scoreboard.getObjective("health").getScore(onlinePlayer.getName()).setScore((int) onlinePlayer.getHealth());
            scoreboard.getObjective("playerAmethyst").getScore(onlinePlayer.getName()).setScore(TeamHelper.calculateAmountOfShardsInPlayer(onlinePlayer));
        });

        plugin.getServer().getOnlinePlayers().forEach(onlinePlayer -> {
            if (teamBlue.contains(onlinePlayer.getName()) || teamRed.contains(onlinePlayer.getName())) {
                onlinePlayer.setScoreboard(scoreboard);
            }
        });
    }

    @Override
    public void run() {
        updateScoreboardForAllPlayers();
        if (isGameRunning) {
            if (blueTeamShardAmount >= 10 || redTeamShardAmount >= 10) {
                if (teamWinCountDownEvent == null) {
                    teamWinCountDownEvent = new TeamWinCountDownEvent();
                    teamWinCountDownEvent.runTaskTimer(plugin, 0, 20);
                }
            } else {
                if (teamWinCountDownEvent != null) {
                    teamWinCountDownEvent.cancel();
                    teamWinCountDownEvent = null;
                }
            }
        } else {
            if (teamWinCountDownEvent != null) {
                teamWinCountDownEvent.cancel();
                teamWinCountDownEvent = null;
            }
        }
    }
}
