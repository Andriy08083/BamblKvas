package ua.ave.heroes;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

public class Zeus implements Listener {

    @EventHandler
    public void onThunderboltDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof LightningStrike) {
            Entity player = event.getEntity();
            if (player instanceof Player) {
                ((Player) player).removePotionEffect(PotionEffectType.INVISIBILITY);
            }
        }
    }
}
