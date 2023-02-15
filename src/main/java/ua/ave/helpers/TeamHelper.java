package ua.ave.helpers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ua.ave.data.Team;

import java.util.Arrays;
import java.util.List;

import static ua.ave.data.Constants.*;

public class TeamHelper {

    public static void addPlayerToTeam(Player player, Team team) {
        removePlayerFromTeam(player);
        switch (team) {
            case RED:
                teamRed.add(player.getName());
                teamScoreBoard.addPlayer(player, team);
                break;
            case BLUE:
                teamBlue.add(player.getName());
                teamScoreBoard.addPlayer(player, team);
                break;
        }
    }

    public static void removePlayerFromTeam(Player player) {
        teamRed.remove(player.getName());
        teamBlue.remove(player.getName());
        teamScoreBoard.removePlayer(player);
    }

    public static boolean isPlayersInSameTeam(Player playerOne, Player playerTwo) {
        return teamRed.containsAll(Arrays.asList(playerOne.getName(), playerTwo.getName())) || teamBlue.containsAll(Arrays.asList(playerOne.getName(), playerTwo.getName()));
    }

    public static int calculateAmountOfShardsInTeam(List<String> team) {
        int shards = 0;
        for (String playerName : team) {
            Player player = Bukkit.getPlayer(playerName);
            if (player == null) continue;
            for (ItemStack item : player.getInventory().getContents()) {
                if (item == null) continue;
                if (item.getType().equals(Material.AMETHYST_SHARD)) {
                    shards += item.getAmount();
                }
            }
        }
        return shards;
    }

    public static int calculateAmountOfShardsInPlayer(Player player) {
        int shards = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item == null) continue;
            if (item.getType().equals(Material.AMETHYST_SHARD)) {
                shards += item.getAmount();
            }
        }
        return shards;
    }
}
