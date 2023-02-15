package ua.ave.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import ua.ave.data.HeroSet;

import java.util.*;

public class PickCommand implements TabCompleter, CommandExecutor {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        HeroSet[] values = HeroSet.values();
        List<String> picks = new ArrayList<>();
        for (HeroSet value : values) {
            picks.add(value.name().toLowerCase());
        }
        return picks;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        ((Player) sender).performCommand("effect clear");
        String selectedSet = args[0].toUpperCase();
        HeroSet heroSet;
        try {
            heroSet = HeroSet.valueOf(selectedSet);
        } catch (IllegalArgumentException e) {
            return false;
        }
        Player player = (Player) sender;
        PlayerInventory inventory = player.getInventory();
        inventory.setContents(heroSet.inventory);
        inventory.setArmorContents(heroSet.armor);
        return true;
    }
}
