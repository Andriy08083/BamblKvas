package ua.ave.helpers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ua.ave.data.Constants;

import static ua.ave.data.Constants.phrases;

public class KillHelper {

    public static void addKill(Player player) {
        if (Constants.kills.containsKey(player.getName())) {
            int kills = Constants.kills.get(player.getName()) + 1;
            Constants.kills.put(player.getName(), kills);
            if (kills >= phrases.length) {
                player.sendTitle(ChatColor.RED + player.getName() + " " + phrases[phrases.length - 1], "", 1, 70, 1);
            } else {
                player.sendTitle(ChatColor.RED + player.getName() + " " + phrases[kills - 1], "", 1, 70, 1);
            }
            return;
        }
        Constants.kills.put(player.getName(), 1);
        player.getServer().getOnlinePlayers().forEach((onlinePlayer) -> onlinePlayer.sendTitle(ChatColor.RED + player.getName() + " " + phrases[0], "", 1, 20, 1));
    }

    public static void removeKills(Player player) {
        Constants.kills.remove(player.getName());
    }
}
