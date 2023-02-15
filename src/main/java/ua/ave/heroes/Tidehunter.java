package ua.ave.heroes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import ua.ave.data.HeroSet;
import ua.ave.helpers.HeroHelper;
import ua.ave.helpers.TeamHelper;

import java.util.List;

import static ua.ave.data.Constants.*;

public class Tidehunter implements Listener {

    @EventHandler
    public void onRavage(PlayerInteractEvent event) {
        if (isGamePaused) {
            event.getPlayer().sendMessage(ChatColor.RED + "Гра на паузі");
            event.setCancelled(true);
            return;
        }
        Player player = event.getPlayer();
        if (!HeroHelper.isPlayerWearingHeroSet(player, HeroSet.TIDEHUNTER)) return;
        if (player.getCooldown(Material.BLAZE_ROD) != 0) return;
        if ((!event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !event.getAction().equals(Action.RIGHT_CLICK_AIR)) || event.getItem() == null || !event.getItem().getType().equals(Material.BLAZE_ROD))
            return;
        player.setCooldown(Material.BLAZE_ROD, config.tidehunterCooldown * 20);
        Location location = player.getLocation().clone();
        double delay = 0;
        for (int i = 2; i <= 12; i += 2) {
            spawnRavageCircles(player, location, i, delay += 0.5);
        }
    }

    @EventHandler
    public void onRangedDamage(PlayerInteractEvent event) {
        if (isGamePaused) {
            event.getPlayer().sendMessage(ChatColor.RED + "Гра на паузі");
            event.setCancelled(true);
            return;
        }
        Player player = event.getPlayer();
        if (!HeroHelper.isPlayerWearingHeroSet(player, HeroSet.TIDEHUNTER)) return;
        if ((!event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !event.getAction().equals(Action.RIGHT_CLICK_AIR)) || event.getItem() == null || !event.getItem().getType().equals(Material.STICK))
            return;
        if (player.getCooldown(Material.STICK) != 0) return;
        player.setCooldown(Material.STICK, config.tidehunterStickCooldown * 20);
        List<Entity> nearbyEntities = player.getNearbyEntities(5, 5, 5);
        for (Entity nearbyEntity : nearbyEntities) {
            if (!(nearbyEntity instanceof LivingEntity)) continue;
            if (nearbyEntity instanceof Player && TeamHelper.isPlayersInSameTeam(player, ((Player) nearbyEntity))) continue;
            ((LivingEntity) nearbyEntity).damage(2, player);
        }
    }

    @EventHandler
    public void onRavageDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof EvokerFangs)) return;
        Player tidehunterPlayer = HeroHelper.getPlayerWithHeroSet(HeroSet.TIDEHUNTER);
        if (tidehunterPlayer == null) return;
        if (!(event.getEntity() instanceof Player)) return;
        Player damagedPlayer = ((Player) event.getEntity());
        if (TeamHelper.isPlayersInSameTeam(tidehunterPlayer, damagedPlayer)) {
            event.setCancelled(true);
            return;
        }
        damagedPlayer.addPotionEffect(PotionEffectType.SLOW.createEffect(4 * 20, 255));
        damagedPlayer.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(4 * 20, 255));
    }

    public void spawnRavageCircles(Player ownerPlayer, final Location centerLocation, int radius, double delayInSeconds) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Location clonedCenterLocation = centerLocation.clone();
                for (int degree = 0; degree < 360; degree += 4) {
                    double radians = Math.toRadians(degree);
                    double x = Math.cos(radians) * radius;
                    double z = Math.sin(radians) * radius;
                    clonedCenterLocation = clonedCenterLocation.add(x, 1, z);
                    EvokerFangs evokerFang = (EvokerFangs) clonedCenterLocation.getWorld().spawnEntity(clonedCenterLocation.clone(), EntityType.EVOKER_FANGS);
                    evokerFang.setOwner(ownerPlayer);
                    evokerFang.setInvulnerable(true);
                    // location.getWorld().playEffect(location, Effect.SMOKE, 1);
                    clonedCenterLocation = clonedCenterLocation.subtract(x, 1, z);
                }
            }
        }.runTaskLater(plugin, (long) (delayInSeconds * 20L));
    }
}
