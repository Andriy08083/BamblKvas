package ua.ave.data;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import ua.ave.helpers.GuaranteedRandom;

public enum HeroSet {
    TECHIES(
            new ItemStack[]{
                    new ItemStackWithEnchantsWrapper(Material.NETHERITE_BOOTS, new EnchantmentsWrapper(Enchantment.PROTECTION_EXPLOSIONS, 4)),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_LEGGINGS, Color.WHITE, new EnchantmentsWrapper(Enchantment.PROTECTION_EXPLOSIONS, 4)),
                    new ItemStackWithEnchantsWrapper(Material.IRON_CHESTPLATE, new EnchantmentsWrapper(Enchantment.PROTECTION_EXPLOSIONS, 4)),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_HELMET, Color.WHITE, new EnchantmentsWrapper(Enchantment.PROTECTION_EXPLOSIONS, 4)),
            }, new ItemStack[]{
            new ItemStack(Material.STRING, 20),
            new ItemStack(Material.SNOWBALL, 10),
    }, null),
    FACELESSVOID(
            new ItemStack[]{
                    new ItemStack(Material.DIAMOND_BOOTS),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_LEGGINGS, Color.PURPLE),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_CHESTPLATE, Color.PURPLE),
                    new ItemStack(Material.DIAMOND_HELMET),
            }, new ItemStack[]{
            new ItemStackWithEnchantsWrapper(Material.DIAMOND_HOE, new EnchantmentsWrapper(Enchantment.DAMAGE_ALL, 5)),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.LINGERING_POTION, 3), PotionEffectType.NIGHT_VISION, 5, 0),
    }, null, new GuaranteedRandom(8)),
    ZEUS(
            new ItemStack[]{
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_BOOTS, Color.BLUE),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_LEGGINGS, Color.BLUE),
                    new ItemStack(Material.DIAMOND_CHESTPLATE),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_HELMET, Color.BLUE),
            }, new ItemStack[]{
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.TRIDENT), new EnchantmentsWrapper(Enchantment.CHANNELING, 3), new EnchantmentsWrapper(Enchantment.LOYALTY, 5)),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.TRIDENT), new EnchantmentsWrapper(Enchantment.CHANNELING, 3), new EnchantmentsWrapper(Enchantment.LOYALTY, 5)),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.TRIDENT), new EnchantmentsWrapper(Enchantment.CHANNELING, 3), new EnchantmentsWrapper(Enchantment.LOYALTY, 5)),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.TRIDENT), new EnchantmentsWrapper(Enchantment.CHANNELING, 3), new EnchantmentsWrapper(Enchantment.LOYALTY, 5)),
    }, null),
    SLARK(
            new ItemStack[]{
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_BOOTS, Color.fromRGB(58, 179, 218)),
                    new ItemStack(Material.NETHERITE_LEGGINGS),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_CHESTPLATE, Color.fromRGB(58, 179, 218)),
                    new ItemStack(Material.IRON_HELMET),
            }, new ItemStack[]{
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.TRIDENT), new EnchantmentsWrapper(Enchantment.RIPTIDE, 3)),
            new ItemStack(Material.WATER_BUCKET),
    }, null),
    RIKI(
            new ItemStack[]{
                    null,
                    null,
                    null,
                    null,
            }, new ItemStack[]{
            new ItemStack(Material.NETHERITE_SWORD),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.POTION, 7), (PotionType.INVISIBILITY), true, true),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.LINGERING_POTION, 4), PotionType.WEAKNESS, true, true),
    }, null),
    LYCAN(
            new ItemStack[]{
                    new ItemStack(Material.NETHERITE_BOOTS),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_LEGGINGS, Color.BLACK),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_CHESTPLATE, Color.fromRGB(104, 37, 35)),
                    new ItemStack(Material.WITHER_SKELETON_SKULL),
            }, new ItemStack[]{
            new ItemStackWithEnchantsWrapper(Material.GREEN_DYE, new EnchantmentsWrapper(Enchantment.DAMAGE_ALL, 5)),
            new ItemStack(Material.BLAZE_ROD),
    }, new PotionEffect[]{
            PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 1),
    }),
    PHANTOMASSASSIN(
            new ItemStack[]{
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_BOOTS, Color.fromRGB(22, 156, 156)),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_LEGGINGS, Color.fromRGB(22, 156, 156)),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_CHESTPLATE, Color.fromRGB(22, 156, 156)),
                    new ItemStack(Material.NETHERITE_HELMET),
            }, new ItemStack[]{
            new ItemStack(Material.STONE_SWORD),
    }, null, new GuaranteedRandom(4), new GuaranteedRandom(7)),
    ABADDON(
            new ItemStack[]{
                    new ItemStack(Material.DIAMOND_BOOTS),
                    new ItemStackWithEnchantsWrapper(Material.CHAINMAIL_LEGGINGS, Color.BLUE),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_CHESTPLATE, Color.BLUE),
                    new ItemStack(Material.DIAMOND_HELMET),
            }, new ItemStack[]{
            new ItemStack(Material.IRON_SWORD),
            new ItemStack(Material.BLAZE_ROD),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.POTION, 4), PotionEffectType.WATER_BREATHING, 8, 0),
    }, null),
    TIDEHUNTER(
            new ItemStack[]{
                    new ItemStack(Material.DIAMOND_BOOTS),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_LEGGINGS, Color.BLUE),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_CHESTPLATE, Color.BLUE),
                    new ItemStack(Material.NETHERITE_HELMET),
            }, new ItemStack[]{
            new ItemStack(Material.IRON_SWORD),
            new ItemStack(Material.BLAZE_ROD),
            new ItemStack(Material.STICK),
    }, null),
    WRAITHKING(
            new ItemStack[]{
                    new ItemStackWithEnchantsWrapper(Material.NETHERITE_BOOTS, new EnchantmentsWrapper(Enchantment.THORNS, 3)),
                    new ItemStackWithEnchantsWrapper(Material.NETHERITE_LEGGINGS, new EnchantmentsWrapper(Enchantment.THORNS, 3)),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_CHESTPLATE, Color.GREEN, new EnchantmentsWrapper(Enchantment.THORNS, 3)),
                    new ItemStackWithEnchantsWrapper(Material.SKELETON_SKULL, new EnchantmentsWrapper(Enchantment.THORNS, 3)),
            }, new ItemStack[]{
            new ItemStackWithEnchantsWrapper(Material.IRON_SWORD),
            new ItemStack(Material.TOTEM_OF_UNDYING),
            new ItemStack(Material.TOTEM_OF_UNDYING),
            new ItemStack(Material.TOTEM_OF_UNDYING),
    }, new PotionEffect[]{
            PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 0),
    }, new GuaranteedRandom(3)),
    EDGAR(
            new ItemStack[]{
                    new ItemStackWithEnchantsWrapper(Material.GOLDEN_BOOTS, new EnchantmentsWrapper(Enchantment.PROTECTION_FALL, 4)),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_LEGGINGS, Color.BLACK, new EnchantmentsWrapper(Enchantment.PROTECTION_FALL, 4)),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_CHESTPLATE, Color.BLACK, new EnchantmentsWrapper(Enchantment.PROTECTION_FALL, 4)),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_HELMET, Color.BLUE, new EnchantmentsWrapper(Enchantment.PROTECTION_FALL, 4)),
            }, new ItemStack[]{
            new ItemStackWithEnchantsWrapper(Material.DIAMOND_SHOVEL, new EnchantmentsWrapper(Enchantment.DAMAGE_ALL, 5)),
            new ItemStack(Material.ENDER_PEARL, 1),
    }, new PotionEffect[]{
            PotionEffectType.REGENERATION.createEffect(Integer.MAX_VALUE, 0),
    }),
    PUDGE(
            new ItemStack[]{
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_BOOTS, Color.fromRGB(187, 111, 110)),
                    new ItemStack(Material.NETHERITE_LEGGINGS),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_CHESTPLATE, Color.fromRGB(187, 111, 110)),
                    new ItemStack(Material.ZOMBIE_HEAD),
            }, new ItemStack[]{
            new ItemStack(Material.STONE_AXE),
            new ItemStack(Material.FISHING_ROD),
    }, null),
    UKRDRAGON(
            new ItemStack[]{
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_BOOTS, new EnchantmentsWrapper(Enchantment.PROTECTION_FALL, 4)),
                    null,
                    new ItemStack(Material.ELYTRA),
                    new ItemStack(Material.DRAGON_HEAD),
            }, new ItemStack[]{
            new ItemStack(Material.NETHERITE_AXE),
            new ItemStack(Material.FIREWORK_ROCKET, 32),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.LINGERING_POTION, 3), PotionType.INSTANT_HEAL, false, false),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.LINGERING_POTION, 3), PotionEffectType.SLOW, 5, 3),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.LINGERING_POTION, 12), PotionType.INSTANT_DAMAGE, false, false),
    }, null),
    VENOMANCER(
            new ItemStack[]{
                    new ItemStack(Material.CHAINMAIL_BOOTS),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_LEGGINGS, Color.GREEN),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_CHESTPLATE, Color.GREEN),
                    new ItemStack(Material.TURTLE_HELMET),
            }, new ItemStack[]{
            new ItemStack(Material.BOW),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.TIPPED_ARROW, 64), PotionEffectType.POISON, 5, 0),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.TIPPED_ARROW, 64), PotionEffectType.POISON, 5, 0),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.TIPPED_ARROW, 64), PotionEffectType.POISON, 5, 0),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.SPLASH_POTION, 3), PotionEffectType.POISON, 20, 1),
    }, null),
    SHADOW_FIEND(
            new ItemStack[]{
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_BOOTS, Color.ORANGE),
                    new ItemStack(Material.NETHERITE_LEGGINGS),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_CHESTPLATE, Color.ORANGE),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_HELMET, Color.BLACK),
            }, new ItemStack[]{
            new ItemStack(Material.BLAZE_ROD),
            new ItemStack(Material.GREEN_DYE),
            new ItemStack(Material.YELLOW_DYE),
            new ItemStack(Material.RED_DYE),
            //new ItemStack(Material.FIRE_CHARGE, 32),

    }, null),
    SHELLY(
            new ItemStack[]{
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_BOOTS, Color.PURPLE),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_LEGGINGS, Color.PURPLE),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_CHESTPLATE, Color.PURPLE),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_HELMET, Color.PURPLE),
            }, new ItemStack[]{
            new ItemStackWithEnchantsWrapper(Material.CROSSBOW, new EnchantmentsWrapper(Enchantment.MULTISHOT, 3)),
            new ItemStack(Material.ARROW, 64),
            new ItemStack(Material.ARROW, 64),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.FIREWORK_ROCKET, 3),
                    FireworkEffect.builder().withColor(Color.BLUE).with(FireworkEffect.Type.BALL).build(),
                    FireworkEffect.builder().withColor(Color.BLUE).with(FireworkEffect.Type.BALL).build(),
                    FireworkEffect.builder().withColor(Color.BLUE).with(FireworkEffect.Type.BALL).build(),
                    FireworkEffect.builder().withColor(Color.BLUE).with(FireworkEffect.Type.BALL).build(),
                    FireworkEffect.builder().withColor(Color.BLUE).with(FireworkEffect.Type.BALL).build())
    }, null),
    BROK(
            new ItemStack[]{
                    new ItemStack(Material.NETHERITE_BOOTS),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_LEGGINGS, Color.BLACK),
                    new ItemStackWithEnchantsWrapper(Material.LEATHER_CHESTPLATE, Color.BLACK),
                    new ItemStack(Material.PLAYER_HEAD),
            }, new ItemStack[]{
            new ItemStack(Material.CROSSBOW),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.FIREWORK_ROCKET, 64),
                    FireworkEffect.builder().withColor(Color.RED).with(FireworkEffect.Type.BALL).build(),
                    FireworkEffect.builder().withColor(Color.ORANGE).with(FireworkEffect.Type.BALL).build(),
                    FireworkEffect.builder().withColor(Color.ORANGE).with(FireworkEffect.Type.BALL).build(),
                    FireworkEffect.builder().withColor(Color.RED).with(FireworkEffect.Type.BALL).build()),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.FIREWORK_ROCKET, 64),
                    FireworkEffect.builder().withColor(Color.RED).with(FireworkEffect.Type.BALL).build(),
                    FireworkEffect.builder().withColor(Color.ORANGE).with(FireworkEffect.Type.BALL).build(),
                    FireworkEffect.builder().withColor(Color.ORANGE).with(FireworkEffect.Type.BALL).build(),
                    FireworkEffect.builder().withColor(Color.RED).with(FireworkEffect.Type.BALL).build()),
            new ItemStackWithEnchantsWrapper(new ItemStack(Material.FIREWORK_ROCKET, 4),
                    FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL_LARGE).build(),
                    FireworkEffect.builder().withColor(Color.BLUE).with(FireworkEffect.Type.BALL_LARGE).build(),
                    FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL_LARGE).build(),
                    FireworkEffect.builder().withColor(Color.BLUE).with(FireworkEffect.Type.BALL_LARGE).build(),
                    FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL_LARGE).build(),
                    FireworkEffect.builder().withColor(Color.BLUE).with(FireworkEffect.Type.BALL_LARGE).build(),
                    FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL_LARGE).build(),
                    FireworkEffect.builder().withColor(Color.BLUE).with(FireworkEffect.Type.BALL_LARGE).build(),
                    FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL_LARGE).build(),
                    FireworkEffect.builder().withColor(Color.BLUE).with(FireworkEffect.Type.BALL_LARGE).build())
    }, null),
    ;


    HeroSet(ItemStack[] armorSlots, ItemStack[] inventorySlots, PotionEffect[] effects) {
        try {
            armor = new ItemStack[armorSlots.length];
            armorMaterials = new Material[armorSlots.length];

            inventory = new ItemStack[inventorySlots.length];
            this.effects = effects;
            for (int i = 0; i < armor.length; i++) {
                if (armorSlots[i] == null) {
                    armor[i] = null;
                    continue;
                }
                armor[i] = armorSlots[i];
            }

            for (int i = 0; i < armorMaterials.length; i++) {
                if (armorSlots[i] == null) {
                    armorMaterials[i] = null;
                    continue;
                }
                armorMaterials[i] = armorSlots[i].getType();
            }

            for (int i = 0; i < inventory.length; i++) {
                inventory[i] = new ItemStack(inventorySlots[i]);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    HeroSet(ItemStack[] armorSlots, ItemStack[] inventorySlots, PotionEffect[] effects, GuaranteedRandom... randoms) {
        this(armorSlots, inventorySlots, effects);
        this.guaranteedRandoms = randoms;
    }


    public ItemStack[] armor;
    public ItemStack[] inventory;
    public Material[] armorMaterials;
    public PotionEffect[] effects;
    public GuaranteedRandom[] guaranteedRandoms;
}

class ItemStackWithEnchantsWrapper extends ItemStack {

    public ItemStackWithEnchantsWrapper(Material itemMaterial, EnchantmentsWrapper... enchantments) {
        super(itemMaterial);
        for (EnchantmentsWrapper enchantmentWrapper : enchantments) {
            addUnsafeEnchantment(enchantmentWrapper.enchantment, enchantmentWrapper.strength);
        }
    }

    public ItemStackWithEnchantsWrapper(ItemStack itemStack, EnchantmentsWrapper... enchantments) {
        super(itemStack);
        for (EnchantmentsWrapper enchantmentWrapper : enchantments) {
            addUnsafeEnchantment(enchantmentWrapper.enchantment, enchantmentWrapper.strength);
        }
    }

    public ItemStackWithEnchantsWrapper(Material itemMaterial, Color color, EnchantmentsWrapper... enchantments) {
        super(itemMaterial);
        if (getItemMeta() instanceof LeatherArmorMeta) {
            for (EnchantmentsWrapper enchantmentWrapper : enchantments) {
                addUnsafeEnchantment(enchantmentWrapper.enchantment, enchantmentWrapper.strength);
            }
            LeatherArmorMeta itemMeta = (LeatherArmorMeta) getItemMeta();
            itemMeta.setColor(color);
            setItemMeta(itemMeta);
        }
    }

    public ItemStackWithEnchantsWrapper(Material itemMaterial, Color color) {
        super(itemMaterial);
        if (getItemMeta() instanceof LeatherArmorMeta) {
            LeatherArmorMeta itemMeta = (LeatherArmorMeta) getItemMeta();
            itemMeta.setColor(color);
            setItemMeta(itemMeta);
        }
    }

    public ItemStackWithEnchantsWrapper(ItemStack itemMaterial, PotionType potionEffect, boolean extend, boolean upgrade) {
        super(itemMaterial);
        if (getItemMeta() instanceof PotionMeta) {
            PotionMeta itemMeta = (PotionMeta) getItemMeta();
            PotionData data;
            if (potionEffect.isExtendable() && extend && potionEffect.isUpgradeable() && upgrade) {
                data = new PotionData(potionEffect, true, true);
                itemMeta.setBasePotionData(data);
                return;
            } else if (potionEffect.isExtendable() && extend) {
                data = new PotionData(potionEffect, true, false);
                itemMeta.setBasePotionData(data);
            } else if (potionEffect.isExtendable() && upgrade) {
                data = new PotionData(potionEffect, true, false);
                itemMeta.setBasePotionData(data);
            } else {
                data = new PotionData(potionEffect, false, false);
                itemMeta.setBasePotionData(data);
            }
            setItemMeta(itemMeta);
        }
    }

    public ItemStackWithEnchantsWrapper(ItemStack itemMaterial, PotionEffectType potionEffectType, int seconds, int amplifier) {
        super(itemMaterial);
        if (getItemMeta() instanceof PotionMeta) {
            PotionMeta itemMeta = (PotionMeta) getItemMeta();
            itemMeta.addCustomEffect(new PotionEffect(potionEffectType, seconds * 20, amplifier), true);
            setItemMeta(itemMeta);
        }
    }

    public ItemStackWithEnchantsWrapper(ItemStack itemMaterial, FireworkEffect... fireworkEffects) {
        super(itemMaterial);
        if (getItemMeta() instanceof FireworkMeta) {
            FireworkMeta itemMeta = (FireworkMeta) getItemMeta();
            itemMeta.addEffects(fireworkEffects);
            setItemMeta(itemMeta);
        }
    }

}

class EnchantmentsWrapper {
    public final Enchantment enchantment;
    public final int strength;

    public EnchantmentsWrapper(Enchantment enchantment, int strength) {
        this.enchantment = enchantment;
        this.strength = strength;
    }
}