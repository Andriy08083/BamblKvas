package ua.ave.heroes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import ua.ave.data.HeroSet;
import ua.ave.helpers.HeroHelper;

import static ua.ave.data.Constants.*;

public class Abaddon implements Listener {

    @EventHandler    public void onHorseSpawn(PlayerInteractEvent event) {
        if (isGamePaused) {
            event.getPlayer().sendMessage(ChatColor.RED + "Гра на паузі");
            event.setCancelled(true);
            return;
        }
        Action action = event.getAction();
        Player player = event.getPlayer();
        if (!HeroHelper.isPlayerWearingHeroSet(player, HeroSet.ABADDON)) return;
        if (event.getPlayer().getCooldown(Material.BLAZE_ROD) != 0) return;
        if (action.equals(Action.RIGHT_CLICK_BLOCK) && event.getItem() != null && event.getItem().getType().equals(Material.BLAZE_ROD)) {
            event.getPlayer().setCooldown(Material.BLAZE_ROD, config.abaddonCooldown * 20);
            Horse horse = (Horse) event.getPlayer().getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
            horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
            horse.getInventory().setArmor(new ItemStack(Material.DIAMOND_HORSE_ARMOR));
            horse.setOwner(player);
            horse.addPassenger(player);
            new BukkitRunnable() {
                @Override
                public void run() {
                    horse.removePassenger(player);
                    horse.remove();
                }
            }.runTaskLater(plugin, 30 * 20);
        }
    }

    @EventHandler
    public void onBurrowedTime(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (HeroHelper.isPlayerWearingHeroSet(player, HeroSet.ABADDON) && player.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
                event.setCancelled(true);
                if (player.getHealth() + 2 < 20) {
                    player.setHealth(player.getHealth() + 2);
                }
            }
        }
    }

    @EventHandler
    public void onBurrowedTimeApply(EntityPotionEffectEvent event) {
        Entity player = event.getEntity();
        if (player instanceof Player && event.getAction().equals(EntityPotionEffectEvent.Action.ADDED)) {
            if (event.getNewEffect() != null) {
                PotionEffect potion = event.getNewEffect();
                if (potion.getType().equals(PotionEffectType.WATER_BREATHING) && HeroHelper.isPlayerWearingHeroSet((Player) player, HeroSet.ABADDON)) {
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.sendTitle("", ChatColor.RED + String.format("%s актививав Burrowed Time", player.getName()), 1, 70, 1);
                    }
                }
            }
        }
    }
}
