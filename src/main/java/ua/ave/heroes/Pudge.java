package ua.ave.heroes;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.potion.PotionEffectType;

public class Pudge implements Listener {

    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {
        Location fisher = e.getPlayer().getLocation();
        Entity player = e.getCaught();
        if (player == null) return;
        if (player instanceof Player) {
            player.setVelocity(fisher.getDirection().multiply(-2).setY((double) .2));
            ((Player) player).sendTitle(ChatColor.RED + "СЮДА БИСТРАААА!!!", "", 1, 40, 1);
            ((Player) player).addPotionEffect(PotionEffectType.BLINDNESS.createEffect(2 * 20, 255));
            ((Player) player).addPotionEffect(PotionEffectType.BLINDNESS.createEffect(2 * 20, 255));
        }
    }
}
