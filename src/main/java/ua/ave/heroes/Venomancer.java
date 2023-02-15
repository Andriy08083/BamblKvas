package ua.ave.heroes;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ua.ave.data.HeroSet;
import ua.ave.helpers.HeroHelper;
import ua.ave.helpers.TeamHelper;

public class Venomancer implements Listener {

    @EventHandler
    public void onTurtleHelmetWorn(EntityPotionEffectEvent event) {
        Player venomancerPlayer = HeroHelper.getPlayerWithHeroSet(HeroSet.VENOMANCER);
        Entity player = event.getEntity();
        if (!(player instanceof Player)) return;
        if (venomancerPlayer == null) return;
        if (TeamHelper.isPlayersInSameTeam(venomancerPlayer, (Player) player)) {
            event.setCancelled(true);
        }
        if (event.getAction().equals(EntityPotionEffectEvent.Action.ADDED)) {
            if (event.getNewEffect() != null) {
                PotionEffect potion = event.getNewEffect();

                if (potion.getType().equals(PotionEffectType.POISON)) {
                    if (HeroHelper.isPlayerWearingHeroSet((Player) player, HeroSet.VENOMANCER)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

}
