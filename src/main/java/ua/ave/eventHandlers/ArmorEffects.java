package ua.ave.eventHandlers;

import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import ua.ave.data.HeroSet;
import ua.ave.helpers.HeroHelper;

public class ArmorEffects extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            HeroSet heroSet = HeroHelper.getHeroSet(player);
            if (heroSet == null) {
                // player.performCommand("effect clear");
                return;
            }
            if (heroSet.effects != null) {
                for (PotionEffect effect : heroSet.effects) {
                    player.addPotionEffect(effect);
                }
            }
        });
    }

   /* @Override
    public void run() {
        Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            // Lycan
            if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getType().equals(Material.WITHER_SKELETON_SKULL)) {
                player.addPotionEffect(PotionEffectType.SPEED.createEffect(10 * 20, 1));
            }
            // Axe
            if (player.getInventory().getLeggings() != null && player.getInventory().getLeggings().getType().equals(Material.CHAINMAIL_LEGGINGS)) {
                player.addPotionEffect(PotionEffectType.REGENERATION.createEffect(10 * 20, 0));
            }
            // Wraith King
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType().equals(Material.CHAINMAIL_CHESTPLATE)) {
                player.addPotionEffect(PotionEffectType.SPEED.createEffect(10 * 20, 1));
            }
            // Mortis
            if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getType().equals(Material.GOLDEN_BOOTS)) {
                player.addPotionEffect(PotionEffectType.REGENERATION.createEffect(10 * 20, 0));
            }
        });
    }*/

}
