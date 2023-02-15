package ua.ave.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ua.ave.config.Config;
import ua.ave.data.Constants;

public class ReloadConfigCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Constants.config = Config.reloadConfig();
        return true;
    }
}
