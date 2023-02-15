package ua.ave.heroes;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import ua.ave.data.HeroSet;
import ua.ave.helpers.HeroHelper;
import ua.ave.helpers.TeamHelper;

import static ua.ave.data.Constants.config;
import static ua.ave.data.Constants.isGamePaused;

public class Lycan implements Listener {

    @EventHandler    public void onWolfSpawn(PlayerInteractEvent event) {
        if (isGamePaused) {
            event.getPlayer().sendMessage(ChatColor.RED + "Гра на паузі");
            event.setCancelled(true);
            return;
        }
        Action action = event.getAction();
        Player player = event.getPlayer();
        if (!HeroHelper.isPlayerWearingHeroSet(player, HeroSet.LYCAN)) return;
        if (event.getPlayer().getCooldown(Material.BLAZE_ROD) != 0) return;
        if (action.equals(Action.RIGHT_CLICK_BLOCK) && event.getItem() != null && event.getItem().getType().equals(Material.BLAZE_ROD)) {
            event.getPlayer().setCooldown(Material.BLAZE_ROD, config.lycanCooldown * 20);
            Wolf wolf = (Wolf) event.getPlayer().getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
            wolf.setCollarColor(DyeColor.BROWN);
            wolf.setOwner(player);
        }
    }

    @EventHandler
    public void onWolfAttack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        if (!(event.getEntity() instanceof Wolf)) return;
        Player playerDamager = (Player) event.getDamager();
        Player lycanPlayer = HeroHelper.getPlayerWithHeroSet(HeroSet.LYCAN);
        if (lycanPlayer == null) return;
        if (!TeamHelper.isPlayersInSameTeam(playerDamager, lycanPlayer)) return;
        event.setCancelled(true);
    }
}
