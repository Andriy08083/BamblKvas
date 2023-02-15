package ua.ave.heroes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ua.ave.data.HeroSet;
import ua.ave.helpers.HeroHelper;

import java.util.Random;

public class WraithKing implements Listener {

    @EventHandler
    public void onWraithKingDealDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Entity player = event.getDamager();
            if (player instanceof Player) {
                if (HeroHelper.isPlayerWearingHeroSet(((Player) player), HeroSet.WRAITHKING)) {
                    if (HeroSet.WRAITHKING.guaranteedRandoms[0].nextInt() == 0) {
                        event.setDamage(event.getDamage() * 1.7);
                        ((Player) player).sendTitle(" ", ChatColor.RED + player.getName() + "Критична шкода - " + event.getFinalDamage(), 1, 70, 1);
                    }
                }
            }
        }
    }

}
