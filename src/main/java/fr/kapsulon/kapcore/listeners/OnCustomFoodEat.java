package fr.kapsulon.kapcore.listeners;

import fr.kapsulon.kapcore.KapCore;
import fr.kapsulon.kapcore.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Random;

public class OnCustomFoodEat implements Listener {
    private final KapCore plugin;

    public OnCustomFoodEat(KapCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCustomFoodEat(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if (ItemManager.itemCompare(item, ItemManager.diamondApple)) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                player.removePotionEffect(PotionEffectType.ABSORPTION);
                player.removePotionEffect(PotionEffectType.REGENERATION);
                player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 240*20, 1, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 300*20, 1, true));
            }, 1L);
        } else if (ItemManager.itemCompare(item, ItemManager.enchantedDiamondApple)) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                player.removePotionEffect(PotionEffectType.ABSORPTION);
                player.removePotionEffect(PotionEffectType.REGENERATION);
                player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 600*20, 3, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 300*20, 2, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300*20, 2, true));
            }, 1L);
        }
    }
}