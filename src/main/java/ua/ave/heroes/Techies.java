package ua.ave.heroes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import ua.ave.data.HeroSet;
import ua.ave.helpers.HeroHelper;
import ua.ave.helpers.TeamHelper;

import static ua.ave.data.Constants.plugin;

public class Techies implements Listener {

    @EventHandler
    public void onMineStep(PlayerMoveEvent event) {
        if (HeroHelper.isPlayerWearingHeroSet(event.getPlayer(), HeroSet.TECHIES)) return;
        Player techies = HeroHelper.getPlayerWithHeroSet(HeroSet.TECHIES);
        if (techies == null) return;
        if (TeamHelper.isPlayersInSameTeam(event.getPlayer(), techies)) return;
        // event.getPlayer().getServer().broadcastMessage(String.format("%s наступив на %s, relative down: %s", event.getPlayer(), event.getTo() == null ? "null" : event.getTo().getBlock(), event.getTo() == null ? "null" : event.getTo().getBlock().getRelative(BlockFace.DOWN)));
        if (event.getTo() != null && event.getTo().getBlock().getType() == Material.TRIPWIRE) {

            Player techiesPlayer = null;
            for (Player player : event.getPlayer().getServer().getOnlinePlayers()) {
                if (HeroHelper.isPlayerWearingHeroSet(event.getPlayer(), HeroSet.TECHIES)) {
                    techiesPlayer = player;
                }
            }
            Player finalTechiesPlayer = techiesPlayer;
            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getPlayer().getWorld().createExplosion(event.getTo().getBlock().getLocation(), 2, false, false, finalTechiesPlayer);
                }
            }.runTaskLater(plugin, 10);
            event.getPlayer().getWorld().getBlockAt(event.getTo().getBlock().getLocation()).setType(Material.AIR);
        }
    }

    @EventHandler
    public void onBlastOff(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Snowball) {
            Projectile snowBall = event.getEntity();
            if (event.getEntity().getShooter() instanceof Player) {
                if (HeroHelper.isPlayerWearingHeroSet(((Player) event.getEntity().getShooter()), HeroSet.TECHIES)) {
                    Player shooter = (Player) event.getEntity().getShooter();
                    if (snowBall.getLocation().distance(shooter.getLocation()) <= 7) {
                        shooter.teleport(snowBall.getLocation());
                        shooter.getWorld().createExplosion(snowBall.getLocation(), 2, false, false);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDetonation(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if (HeroHelper.isPlayerWearingHeroSet(player, HeroSet.TECHIES)) return;
        Player techiesPlayer = HeroHelper.getPlayerWithHeroSet(HeroSet.TECHIES);
        if (techiesPlayer == null) return;
        if (!event.getCause().equals(DamageCause.BLOCK_EXPLOSION)) return;
        if (!TeamHelper.isPlayersInSameTeam(techiesPlayer, player)) return;
        event.setCancelled(true);
    }
}
