package ua.ave.heroes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ua.ave.data.HeroSet;
import ua.ave.helpers.HeroHelper;

import java.util.Random;

public class FacelessVoid implements Listener {

/*
    @EventHandler
    public void onFacelessVoidPotionThrow(EntityPotionEffectEvent event) {
        Entity player = event.getEntity();
        if (player instanceof Player && HeroHelper.isPlayerWearingHeroSet((Player) player, HeroSet.FACELESSVOID)) {
            event.setCancelled(true);
            return;
        }

        if (player instanceof Player && event.getAction().equals(EntityPotionEffectEvent.Action.ADDED) && event.getCause().equals(EntityPotionEffectEvent.Cause.AREA_EFFECT_CLOUD)) {
            if (event.getNewEffect() != null) {
                PotionEffect potion = event.getNewEffect();
                if (potion.getType().equals(PotionEffectType.NIGHT_VISION)) {
                    ((Player) player).sendTitle(ChatColor.RED + "CHRONOSPHERE", "", 1, 80, 1);
                    ((Player) player).addPotionEffect(PotionEffectType.SLOW.createEffect(3 * 20, 255));
                    ((Player) player).addPotionEffect(PotionEffectType.WEAKNESS.createEffect(3 * 20, 255));
                    event.setCancelled(true);
                }
            }
        }
    }*/

    @EventHandler
    public void onFacelessVoidPotionThrow(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (HeroHelper.isPlayerWearingHeroSet(player, HeroSet.FACELESSVOID)) {
            return;
        }

        for (PotionEffect activePotionEffect : event.getPlayer().getActivePotionEffects()) {
            if (activePotionEffect.getType().equals(PotionEffectType.NIGHT_VISION)) {
                player.sendTitle(ChatColor.RED + "CHRONOSPHERE", "", 1, 80, 1);
                player.addPotionEffect(PotionEffectType.SLOW.createEffect(3 * 20, 255));
                player.addPotionEffect(PotionEffectType.WEAKNESS.createEffect(3 * 20, 255));
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onFacelessVoidDealDamage(EntityDamageByEntityEvent event) {
        Entity player = event.getDamager();
        if (player instanceof Player && HeroHelper.isPlayerWearingHeroSet((Player) player, HeroSet.FACELESSVOID)) {
            if (event.getEntity() instanceof Player) {
                if (HeroSet.FACELESSVOID.guaranteedRandoms[0].nextInt() == 0) {
                    event.setDamage(event.getDamage() * 1.5);
                    ((Player) event.getEntity()).sendTitle(ChatColor.RED + "ТИ В БАШІ ДАУН", "", 1, 40, 1);
                    ((Player) event.getEntity()).addPotionEffect(PotionEffectType.SLOW.createEffect(2 * 20, 255));
                    ((Player) event.getEntity()).addPotionEffect(PotionEffectType.BLINDNESS.createEffect(2 * 20, 255));
                    ((Player) event.getEntity()).addPotionEffect(PotionEffectType.WEAKNESS.createEffect(2 * 20, 255));
                }
            }
        }
    }
}
