package fr.kapsulon.kapcore.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack lifeToken;
    public static ItemStack diamondApple;
    public static ItemStack enchantedDiamondApple;
    public static ItemStack diamondCarrot;
    public static ItemStack lightEnderPearl;
    public static ItemStack timeEnderPearl;
    public static ItemStack cursedEnderPearl;

    public enum CustomItem {
        LIFE_TOKEN("lifeToken", lifeToken),
        DIAMOND_APPLE("diamondApple", diamondApple),
        ENCHANTED_DIAMOND_APPLE("enchantedDiamondApple", enchantedDiamondApple),
        DIAMOND_CARROT("diamondCarrot", diamondCarrot),
        LIGHT_ENDER_PEARL("lightEnderPearl", lightEnderPearl),
        TIME_ENDER_PEARL("timeEnderPearl", timeEnderPearl),
        CURSED_ENDER_PEARL("cursedEnderPearl", cursedEnderPearl);
        private final String code;
        private final ItemStack item;

        CustomItem(String code, ItemStack item) {
            this.code = code;
            this.item = item;
        }

        public static ItemStack getCustomItemByCode(String code) {
            for (CustomItem customItem : CustomItem.values()) {
                if (customItem.code.equals(code)) {
                    return customItem.item;
                }
            }
            return null;
        }

        public static String getCustomItemByItemStack(ItemStack item) {
            for (CustomItem customItem : CustomItem.values()) {
                if (customItem.item.isSimilar(item)) {
                    return customItem.code;
                }
            }
            return null;
        }

        public static boolean isCustomItem(ItemStack item) {
            for (CustomItem customItem : CustomItem.values()) {
                if (customItem.item.isSimilar(item)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void init() {
        createLifeToken();
        createDiamondApple();
        createEnchantedDiamondApple();
        createDiamondCarrot();
        createLightEnderPearl();
        createTimeEnderPearl();
        createCursedEnderPearl();

        createEchantedGoldenAppleRecipe();
        createCryingObsidianRecipe();
        createTridentRecipe();
        createSculkSensorRecipe();
        createGlowstoneDustRecipe();
        createGlowInkSacRecipe();
        createNametagRecipe();
        createLeatherRecipe();
    }

    private static void createLifeToken() {
        ItemStack item = new ItemStack(Material.CLOCK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(1);
        meta.setDisplayName("§r§bLife Token");
        List<String> lore = new ArrayList<String>();
        lore.add("§8Right Click with §7§uthis§r §8item to gain a life.");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
        item.setItemMeta(meta);
        lifeToken = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("life_token"), item);
        sr.shape("NDN",
                 "DSD",
                 "NDN");
        sr.setIngredient('N', Material.NETHERITE_INGOT);
        sr.setIngredient('D', Material.DIAMOND_BLOCK);
        sr.setIngredient('S', Material.NETHER_STAR);

        Bukkit.getServer().addRecipe(sr);
    }

    private static void createDiamondApple() {
        ItemStack item = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(1);
        meta.setDisplayName("§r§bDiamond Apple");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        diamondApple = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("diamond_apple"), item);
        sr.shape("DDD",
                "DAD",
                "DDD");
        sr.setIngredient('D', Material.DIAMOND);
        sr.setIngredient('A', Material.APPLE);

        Bukkit.getServer().addRecipe(sr);
    }

    private static void createEnchantedDiamondApple() {
        ItemStack item = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(1);
        meta.setDisplayName("§r§b§lEnchanted Diamond Apple");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        enchantedDiamondApple = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("enchanted_diamond_apple"), item);
        sr.shape("DDD",
                "DAD",
                "DDD");
        sr.setIngredient('D', Material.DIAMOND_BLOCK);
        sr.setIngredient('A', Material.APPLE);

        Bukkit.getServer().addRecipe(sr);
    }

    private static void createDiamondCarrot() {
        ItemStack item = new ItemStack(Material.CLOCK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(2);
        meta.setDisplayName("§r§bDiamond Carrot");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        diamondCarrot = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("diamond_carrot"), item);
        sr.shape(" D ", "DCD", " D ");
        sr.setIngredient('D', Material.DIAMOND);
        sr.setIngredient('C', Material.CARROT);

        Bukkit.getServer().addRecipe(sr);
    }

    private static void createLightEnderPearl() {
        ItemStack item = new ItemStack(Material.ENDER_PEARL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(1);
        meta.setDisplayName("§r§bLight Ender Pearl");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        lightEnderPearl = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("light_ender_pearl"), item);
        sr.shape("FSF", "SES", "FSF");
        sr.setIngredient('F', Material.FEATHER);
        sr.setIngredient('S', Material.SUGAR);
        sr.setIngredient('E', Material.ENDER_PEARL);

        Bukkit.getServer().addRecipe(sr);
    }

    private static void createTimeEnderPearl() {
        ItemStack item = new ItemStack(Material.ENDER_PEARL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(2);
        meta.setDisplayName("§r§5Time Ender Pearl");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        timeEnderPearl = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("time_ender_pearl"), item);
        sr.shape("ACA", "CLC", "ACA");
        sr.setIngredient('A', Material.AMETHYST_SHARD);
        sr.setIngredient('C', Material.CRYING_OBSIDIAN);
        sr.setIngredient('L', new RecipeChoice.ExactChoice(lightEnderPearl));

        Bukkit.getServer().addRecipe(sr);
    }

    private static void createCursedEnderPearl() {
        ItemStack item = new ItemStack(Material.CLOCK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(3);
        meta.setDisplayName("§6§k|| §r§6§lCursed Ender Pearl §k||");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lore = new ArrayList<String>();
        lore.add("§eThis item radiates with energy");
        lore.add("§emaybe it can be used somewhere ?");
        meta.setLore(lore);
        item.setItemMeta(meta);
        cursedEnderPearl = item;
    }

    private static void createCryingObsidianRecipe() {
        ItemStack item = new ItemStack(Material.CRYING_OBSIDIAN, 8);

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("crying_obsidian"), item);
        sr.shape("OOO",
                 "OGO",
                 "OOO");
        sr.setIngredient('O', Material.OBSIDIAN);
        sr.setIngredient('G', Material.GHAST_TEAR);

        Bukkit.getServer().addRecipe(sr);
    }

    private static void createEchantedGoldenAppleRecipe() {
        ItemStack item = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("enchanted_golden_apple"), item);
        sr.shape("GGG",
                "GAG",
                "GGG");
        sr.setIngredient('G', Material.GOLD_BLOCK);
        sr.setIngredient('A', Material.APPLE);

        Bukkit.getServer().addRecipe(sr);
    }

    private static void createTridentRecipe() {
        ItemStack item = new ItemStack(Material.TRIDENT, 1);

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("trident"), item);
        sr.shape(" II",
                 " PI",
                 "P  ");
        sr.setIngredient('I', Material.IRON_INGOT);
        sr.setIngredient('P', Material.PRISMARINE_SHARD);

        Bukkit.getServer().addRecipe(sr);
    }

    private static void createSculkSensorRecipe() {
        ItemStack item = new ItemStack(Material.SCULK_SENSOR, 1);

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("sculk_sensor"), item);
        sr.shape("   ",
                 "G G",
                 "ERE");
        sr.setIngredient('G', Material.GLOW_INK_SAC);
        sr.setIngredient('E', Material.ENDER_PEARL);
        sr.setIngredient('R', Material.REPEATER);

        Bukkit.getServer().addRecipe(sr);
    }

    private static void createGlowstoneDustRecipe() {
        ItemStack item = new ItemStack(Material.GLOWSTONE_DUST, 4);

        ShapelessRecipe slr = new ShapelessRecipe(NamespacedKey.minecraft("glowstone_dust"), item);
        slr.addIngredient(Material.GLOW_BERRIES);

        Bukkit.getServer().addRecipe(slr);
    }

    private static void createGlowInkSacRecipe() {
        ItemStack item = new ItemStack(Material.GLOW_INK_SAC, 1);

        ShapelessRecipe slr = new ShapelessRecipe(NamespacedKey.minecraft("glow_ink_sac"), item);
        slr.addIngredient(Material.GLOWSTONE_DUST);
        slr.addIngredient(Material.INK_SAC);

        Bukkit.getServer().addRecipe(slr);
    }

    private static void createNametagRecipe() {
        ItemStack item = new ItemStack(Material.NAME_TAG, 1);

        ShapelessRecipe slr = new ShapelessRecipe(NamespacedKey.minecraft("nametag"), item);
        slr.addIngredient(Material.STRING);
        slr.addIngredient(Material.PAPER);
        slr.addIngredient(Material.INK_SAC);

        Bukkit.getServer().addRecipe(slr);
    }

    private static void createLeatherRecipe() {
        ItemStack item = new ItemStack(Material.LEATHER, 1);

        FurnaceRecipe fr = new FurnaceRecipe(NamespacedKey.minecraft("leather_furnace"), item, Material.ROTTEN_FLESH, 0.5f, 100);

        Bukkit.getServer().addRecipe(fr);
    }

    public static boolean itemCompare(ItemStack item_one, ItemStack item_two) {
        ItemStack instance_item_one = new ItemStack(item_one);
        ItemStack instance_item_two = new ItemStack(item_two);
        instance_item_two.setAmount(instance_item_one.getAmount());
        return instance_item_one.equals(instance_item_two);
    }
}
