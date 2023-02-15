package ua.ave.helpers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WorldHelper {

    public static void summonItemInLocation(ItemStack item, Location location) {
        location.getWorld().dropItem(location, item);
    }

    public static void sendTitleToEveryone(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
            onlinePlayer.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        }
    }
}
