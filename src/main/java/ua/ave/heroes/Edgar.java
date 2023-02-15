package ua.ave.heroes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import ua.ave.data.HeroSet;
import ua.ave.helpers.HeroHelper;

public class Edgar implements Listener {

    @EventHandler
    public void onEnderPearlUse(PlayerTeleportEvent event) {
        if (!(event.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL))) return;
        if (!HeroHelper.isPlayerWearingHeroSet(event.getPlayer(), HeroSet.EDGAR)) return;
        if (event.getTo() == null) return;
        Player player = event.getPlayer();
        player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
        /*if ((player.getLocation().distance(event.getTo()) >= 9)) {
            event.setCancelled(true);
        }*/
    }

    @EventHandler
    public void onDamaging(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        if (!HeroHelper.isPlayerWearingHeroSet(player, HeroSet.EDGAR)) return;
        if (player.getHealth() + 1 < 20) {
            player.setHealth(player.getHealth() + 1);
        }
    }

}
