package ua.ave.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import ua.ave.data.Team;
import ua.ave.helpers.TeamHelper;

import java.util.ArrayList;
import java.util.List;

public class TeamCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        String selectedTeam = args[0].toUpperCase();
        Team team;
        try {
            team = Team.valueOf(selectedTeam);
        } catch (IllegalArgumentException e) {
            return false;
        }
        Player player = (Player) sender;
        TeamHelper.removePlayerFromTeam(player);
        TeamHelper.addPlayerToTeam(player, team);
        player.sendMessage(String.format("Ти вибрав команду %s", team));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        Team[] values = Team.values();
        List<String> teams = new ArrayList<>();
        for (Team value : values) {
            teams.add(value.name().toLowerCase());
        }
        return teams;
    }
}
