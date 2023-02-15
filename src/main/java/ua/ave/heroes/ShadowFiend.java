package ua.ave.heroes;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import ua.ave.data.HeroSet;
import ua.ave.helpers.HeroHelper;
import ua.ave.helpers.TeamHelper;

import static ua.ave.data.Constants.*;

public class ShadowFiend implements Listener {

    @EventHandler
    public void onRequiemOfSouls(PlayerInteractEvent event) {
        if (isGamePaused) {
            event.getPlayer().sendMessage(ChatColor.RED + "Гра на паузі");
            event.setCancelled(true);
            return;
        }
        if (!HeroHelper.isPlayerWearingHeroSet(event.getPlayer(), HeroSet.SHADOW_FIEND)) return;
        if (event.getPlayer().getCooldown(Material.BLAZE_ROD) != 0) return;
        if ((!event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !event.getAction().equals(Action.RIGHT_CLICK_AIR)) || event.getItem() == null || !event.getItem().getType().equals(Material.BLAZE_ROD))
            return;
        event.getPlayer().setCooldown(Material.BLAZE_ROD, config.shadowFiendRequiemCooldown * 20);
        Player player = event.getPlayer();
        Location location = player.getLocation().clone();
        location.setY(location.getBlockY() + 1);
        location.setPitch(0);
        for (int i = -180; i <= 180; i += 4) {
            Vector fireballVector = location.toVector().rotateAroundY(i).normalize().multiply(2);
            LargeFireball largeFireball = event.getPlayer().launchProjectile(LargeFireball.class);
            largeFireball.setInvulnerable(true);
            largeFireball.setShooter(player);
            largeFireball.setDirection(fireballVector);
        }

    }

    @EventHandler
    public void onCoilHit(PlayerInteractEvent event) {
        if (isGamePaused) {
            event.getPlayer().sendMessage(ChatColor.RED + "Гра на паузі");
            event.setCancelled(true);
            return;
        }
        if (!HeroHelper.isPlayerWearingHeroSet(event.getPlayer(), HeroSet.SHADOW_FIEND)) return;
        if ((!event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !event.getAction().equals(Action.RIGHT_CLICK_AIR)) || event.getItem() == null)
            return;
        if (!event.getItem().getType().equals(Material.GREEN_DYE) && !event.getItem().getType().equals(Material.YELLOW_DYE) && !event.getItem().getType().equals(Material.RED_DYE))
            return;
        if (event.getPlayer().getCooldown(event.getItem().getType()) != 0) return;
        Player player = event.getPlayer();
        Location loc;
        Location clonedLocation = player.getLocation().clone();
        clonedLocation.setPitch(0);
        Vector direction = clonedLocation.getDirection();

        switch (event.getItem().getType()) {
            case GREEN_DYE:
                loc = player.getLocation().add(direction.multiply(4));
                loc.setPitch(0);
                player.getWorld().spawnParticle(Particle.REDSTONE, loc, 20, new Particle.DustOptions(Color.fromRGB(255, 0, 0), 10));
                player.getWorld().createExplosion(loc, 2, false, false);
                break;
            case YELLOW_DYE:
                loc = player.getLocation().add(direction.multiply(7));
                loc.setPitch(0);
                player.getWorld().spawnParticle(Particle.REDSTONE, loc, 20, new Particle.DustOptions(Color.fromRGB(255, 0, 0), 10));
                player.getWorld().createExplosion(loc, 2, false, false);
                break;
            case RED_DYE:
                loc = player.getLocation().add(direction.multiply(10));
                loc.setPitch(0);
                player.getWorld().spawnParticle(Particle.REDSTONE, loc, 20, new Particle.DustOptions(Color.fromRGB(255, 0, 0), 10));
                player.getWorld().createExplosion(loc, 2, false, false);
                break;
        }
        event.getPlayer().setCooldown(event.getItem().getType(), config.shadowFiendCoilCooldown * 20);
    }

    @EventHandler
    public void onRequiemOfSoulsHit(ProjectileHitEvent event) {
        if ((event.getHitEntity() instanceof LargeFireball)) {
            event.setCancelled(true);
            return;
        }
        if (event.getHitEntity() == null) return;
        if (TeamHelper.isPlayersInSameTeam(((Player) event.getEntity().getShooter()), ((Player) event.getHitEntity()))) {
            event.setCancelled(true);
            return;
        }
        if (HeroHelper.isPlayerWearingHeroSet(((Player) event.getHitEntity()), HeroSet.SHADOW_FIEND)) {
            event.setCancelled(true);
            return;
        }
        // if (!(event.getHitEntity() instanceof Player)) return;
        if (event.getEntity() instanceof LargeFireball && event.getHitEntity() instanceof Player) {
            Player player = (Player) event.getHitEntity();
            player.damage(2);
            player.addPotionEffect(PotionEffectType.SLOW.createEffect(2 * 20, 255));
            player.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(2 * 20, 255));
            player.setVelocity(event.getHitEntity().getLocation().toVector());
        }
    }


    @EventHandler
    public void onDetonation(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if (HeroHelper.isPlayerWearingHeroSet(player, HeroSet.SHADOW_FIEND)) return;
        Player shadowFiendPlayer = HeroHelper.getPlayerWithHeroSet(HeroSet.SHADOW_FIEND);
        if (shadowFiendPlayer == null) return;
        if (!event.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) return;
        if (!TeamHelper.isPlayersInSameTeam(shadowFiendPlayer, player)) return;
        event.setCancelled(true);
    }
}
