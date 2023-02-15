package ua.ave.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ua.ave.data.Constants;
import ua.ave.eventHandlers.ShardSpawnEvent;

import static ua.ave.data.Constants.*;

public class StartGameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Constants.kills.clear();
        if (shardSpawnEvent == null) {
            shardSpawnEvent = new ShardSpawnEvent();
        }
        isGameRunning = true;
        sender.getServer().broadcastMessage("Кілли очищено");
        sender.getServer().dispatchCommand(sender, "minecraft:kill @e[type=item]");
        sender.getServer().broadcastMessage("Шарди очищено");
        shardSpawnEvent.runTaskTimer(plugin, 0, 30 * 20);
        sender.getServer().broadcastMessage("Шарди спавняться");
        sender.getServer().getOnlinePlayers().forEach(player -> {
            player.sendTitle(ChatColor.RED + "BRAWL", "", 1, 40, 1);
        });
        return true;
    }
}
