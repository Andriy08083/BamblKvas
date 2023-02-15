package ua.ave.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static ua.ave.data.Constants.isGameRunning;
import static ua.ave.data.Constants.shardSpawnEvent;

public class StopGameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.getServer().broadcastMessage("Гру зупинено");
        isGameRunning = false;
        shardSpawnEvent.cancel();
        shardSpawnEvent = null;
        return true;
    }
}
