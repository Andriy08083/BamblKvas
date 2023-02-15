package ua.ave.heroes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ua.ave.data.HeroSet;
import ua.ave.helpers.HeroHelper;

import java.util.Random;

public class PhantomAssassin implements Listener {

    @EventHandler
    public void onPhantomAssassinDealDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Entity player = event.getDamager();
            Entity damagedPlayer = event.getEntity();
            if (damagedPlayer instanceof Player && HeroHelper.isPlayerWearingHeroSet((Player) damagedPlayer, HeroSet.PHANTOMASSASSIN)) {
                if (HeroSet.PHANTOMASSASSIN.guaranteedRandoms[0].nextInt() == 0) {
                    event.setCancelled(true);
                    ((Player) player).sendTitle(" ", ChatColor.RED + "Промах", 1, 70, 1);
                    return;
                }
            }
            if (player instanceof Player) {
                if (HeroHelper.isPlayerWearingHeroSet((Player) player, HeroSet.PHANTOMASSASSIN)) {
                    if (HeroSet.PHANTOMASSASSIN.guaranteedRandoms[1].nextInt() == 0) {
                        event.setDamage(event.getDamage() * 3);
                        ((Player) player).sendTitle(" ", ChatColor.RED + "Критична шкода - " + event.getFinalDamage(), 1, 70, 1);
                    }
                }
            }
        }
    }
}
