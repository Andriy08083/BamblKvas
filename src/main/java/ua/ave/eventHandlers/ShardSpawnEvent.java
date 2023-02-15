package ua.ave.eventHandlers;

import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import ua.ave.helpers.WorldHelper;

public class ShardSpawnEvent extends BukkitRunnable {

    World world = Bukkit.getWorld("world");
    Location location = new Location(world, 330, -60, -492);

    @Override
    public void run() {
        WorldHelper.summonItemInLocation(new ItemStack(Material.AMETHYST_SHARD, 1), location);
        Bukkit.getServer().broadcastMessage(String.format("%sЗаспавнився шард", ChatColor.RED));
    }
}
