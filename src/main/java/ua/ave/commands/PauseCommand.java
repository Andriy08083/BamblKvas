package ua.ave.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ua.ave.helpers.WorldHelper;

import static ua.ave.data.Constants.*;

public class PauseCommand extends BukkitRunnable implements CommandExecutor {

    protected Player requestedPlayer;
    protected int secondsToPause = 5;

    public PauseCommand() {

    }

    public PauseCommand(Player requestedPlayer) {
        this.requestedPlayer = requestedPlayer;
    }

    @Override
    public void run() {
        if (isGamePaused) {
            cancel();
        }
        WorldHelper.sendTitleToEveryone(ChatColor.RED + String.valueOf(secondsToPause), String.format("%s requested pause", requestedPlayer.getName()), 1, 20, 1);
        if (secondsToPause == 0) {
            isGamePaused = true;
            lastPause = Bukkit.getServer().getWorld("world").getGameTime();
            cancel();
        }
        secondsToPause--;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        if (lastPause > lastPause + (60 * 20)) {
            return false;
        }
        new PauseCommand(((Player) sender)).runTaskTimer(plugin, 0, 20);
        return true;
    }
}
