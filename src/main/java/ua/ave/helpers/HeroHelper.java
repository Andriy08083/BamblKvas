package ua.ave.helpers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ua.ave.data.HeroSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ua.ave.data.Constants.plugin;

public class HeroHelper {

    public static HeroSet getHeroSet(Player player) {
        HeroSet[] heroes = HeroSet.values();
        List<Material> armorContents = new ArrayList<>();
        ItemStack[] playerSlots = player.getInventory().getArmorContents();

        for (ItemStack armorContent : playerSlots) {
            if (armorContent == null) {
                armorContents.add(null);
                continue;
            }
            armorContents.add(armorContent.getType());
        }

        for (HeroSet hero : heroes) {
            List<Material> setSlotsArray = Arrays.asList(hero.armorMaterials);
            if (armorContents.equals(setSlotsArray)) {
                return hero;
            }
        }
        return null;
    }

    public static boolean isPlayerWearingHeroSet(Player player, HeroSet heroSet) {
        HeroSet playerHeroSet = getHeroSet(player);
        return Objects.equals(playerHeroSet, heroSet);
    }

    public static Player getPlayerWithHeroSet(HeroSet heroSet) {
        Player player = null;
        for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
            if (getHeroSet(onlinePlayer) == null) continue;
            if (getHeroSet(onlinePlayer).equals(heroSet)) {
                player = onlinePlayer;
            }
        }
        return player;
    }
}
