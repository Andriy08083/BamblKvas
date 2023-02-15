package ua.ave.eventHandlers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;

public class ShardLightningEvent extends BukkitRunnable {

    World world = Bukkit.getWorld("world");

    @Override
    public void run() {
        for (Entity entity : world.getEntities()) {
            if (!(entity instanceof Item)) continue;
            Item item = ((Item) entity);
            if (!item.getItemStack().getType().equals(Material.AMETHYST_SHARD)) continue;
            if (item.getTicksLived() != 0 && item.getTicksLived() % 600 == 0) {
                item.getWorld().strikeLightningEffect(item.getLocation());
            }

        }
    }
}
