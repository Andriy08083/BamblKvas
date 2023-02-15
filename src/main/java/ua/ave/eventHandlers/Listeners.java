package ua.ave.eventHandlers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import ua.ave.data.HeroSet;
import ua.ave.helpers.HeroHelper;
import ua.ave.helpers.KillHelper;
import ua.ave.helpers.TeamHelper;
import ua.ave.helpers.WorldHelper;

import static ua.ave.data.Constants.isGamePaused;

public class Listeners implements Listener {

    @EventHandler
    public void onAmethystPickup(EntityPickupItemEvent event) {
        LivingEntity entity = event.getEntity();
        if (!event.getItem().getItemStack().getType().equals(Material.AMETHYST_SHARD)) return;
        if (entity instanceof Player) {
            int shards = 0;
            for (ItemStack item : ((Player) entity).getInventory().getContents()) {
                if (item == null) continue;
                if (item.getType().equals(Material.AMETHYST_SHARD)) {
                    shards += item.getAmount();
                }
            }
            shards += event.getItem().getItemStack().getAmount();
            event.getEntity().getServer().broadcastMessage("У " + (entity.getName().equals("Tokio_Zui") ? "власника " : "гравця ") + entity.getName() + " " + shards + " шардів");
        }
    }

    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getEntity();
            arrow.remove();
        }
    }

    @EventHandler
    public void onAmethystKill(EntityCombustByBlockEvent event) {
        Entity ent = event.getEntity();
        if (!(event.getEntity() instanceof Item)) return;
        if (ent.getLastDamageCause() == null) return;
        Item item = (Item) event.getEntity();
        if (!item.getItemStack().getType().equals(Material.AMETHYST_SHARD)) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onAmethystKill(EntityCombustByEntityEvent event) {
        Entity ent = event.getEntity();
        if (!(event.getEntity() instanceof Item)) return;
        if (ent.getLastDamageCause() == null) return;
        Item item = (Item) event.getEntity();
        if (!item.getItemStack().getType().equals(Material.AMETHYST_SHARD)) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onAmethystKill(EntityDamageByBlockEvent event) {
        Entity ent = event.getEntity();
        if (!(event.getEntity() instanceof Item)) return;
        if (ent.getLastDamageCause() == null) return;
        Item item = (Item) event.getEntity();
        if (!item.getItemStack().getType().equals(Material.AMETHYST_SHARD)) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onAmethystKill(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Item)) return;
        if (!(event.getDamager() instanceof LightningStrike)) return;
        Item item = (Item) event.getEntity();
        if (!item.getItemStack().getType().equals(Material.AMETHYST_SHARD)) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onAmethystKill(EntityDamageEvent event) {
        Entity ent = event.getEntity();
        if (!(event.getEntity() instanceof Item)) return;
        if (ent.getLastDamageCause() == null) return;
        Item item = (Item) event.getEntity();
        if (!item.getItemStack().getType().equals(Material.AMETHYST_SHARD)) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onAmethystKill(EntityCombustEvent event) {
        Entity ent = event.getEntity();
        if (!(event.getEntity() instanceof Item)) return;
        if (ent.getLastDamageCause() == null) return;
        Item item = (Item) event.getEntity();
        if (!item.getItemStack().getType().equals(Material.AMETHYST_SHARD)) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onHeroKill(PlayerDeathEvent event) {
        Player killed = event.getEntity();
        int shards = 0;
        for (ItemStack item : killed.getInventory().getContents()) {
            if (item == null) continue;
            if (item.getType().equals(Material.AMETHYST_SHARD)) {
                shards += item.getAmount();
            }
        }
        if (shards != 0) {
            killed.getInventory().remove(Material.AMETHYST_SHARD);
            event.getEntity().getWorld().dropItem(killed.getLocation(), new ItemStack(Material.AMETHYST_SHARD, shards));
            killed.getServer().getOnlinePlayers().forEach(player -> {
                player.sendTitle(" ", String.format("%s втратив свої шарди", killed.getName()), 1, 70, 1);
            });
        }
        Player killer = event.getEntity().getKiller();
        if (killer != null) {
            String killerName = killer.getName();
            KillHelper.addKill(killer);
            event.setDeathMessage(ChatColor.RED + String.format("%s всосав у %s", killed.getName(), killerName));
        }
        KillHelper.removeKills(killed);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntityType() != EntityType.PLAYER) return;
        event.setFoodLevel(20);
        event.setCancelled(true);
    }

    @EventHandler
    public void unbreakableItems(PlayerItemDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamageDeal(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(event.getDamager() instanceof Player)) return;
        if (TeamHelper.isPlayersInSameTeam((Player) event.getEntity(), (Player) event.getDamager())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLightningDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (event.getDamager() instanceof LightningStrike) {
            Player zeusPlayer = HeroHelper.getPlayerWithHeroSet(HeroSet.ZEUS);
            if (zeusPlayer != null) {
                if (TeamHelper.isPlayersInSameTeam((Player) event.getEntity(), zeusPlayer)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamageDeal(ProjectileHitEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player)) return;
        if (!(event.getHitEntity() instanceof Player)) return;
        if (TeamHelper.isPlayersInSameTeam((Player) event.getHitEntity(), (Player) event.getEntity().getShooter())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onGamePause(PlayerMoveEvent event) {
        if (isGamePaused) {
            event.getPlayer().sendMessage(ChatColor.RED + "Гра на паузі");
            event.setCancelled(true);
        }
    }

/*    @EventHandler(priority = EventPriority.HIGHEST)
    public void onGamePause(PlayerInteractEvent event) {
        if (isGamePaused) {
            event.getPlayer().sendMessage(ChatColor.RED + "Гра на паузі");
            event.setCancelled(true);
        }
    }*/
}
