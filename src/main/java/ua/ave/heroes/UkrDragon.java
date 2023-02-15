package ua.ave.heroes;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ua.ave.data.HeroSet;
import ua.ave.helpers.HeroHelper;

public class UkrDragon implements Listener {
    @EventHandler
    public void onPotionEffectApplied(EntityPotionEffectEvent event) {
        Entity player = event.getEntity();
        if (player instanceof Player && event.getAction().equals(EntityPotionEffectEvent.Action.ADDED)) {
            if (event.getNewEffect() != null) {
                PotionEffect potion = event.getNewEffect();
                if (potion.getType().equals(PotionEffectType.HARM) || potion.getType().equals(PotionEffectType.SLOW)) {
                    if (HeroHelper.isPlayerWearingHeroSet((Player) player, HeroSet.UKRDRAGON)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
