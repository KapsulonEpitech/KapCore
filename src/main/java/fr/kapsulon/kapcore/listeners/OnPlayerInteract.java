package fr.kapsulon.kapcore.listeners;

import de.tr7zw.nbtapi.NBTEntity;
import fr.kapsulon.kapcore.KapCore;
import fr.kapsulon.kapcore.items.ItemManager;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class OnPlayerInteract implements Listener {
    private final KapCore plugin;

    public OnPlayerInteract(KapCore plugin) {
        this.plugin = plugin;
    }

    private void diamondCarrotUse(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) { return; }
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (player.getGameMode() != GameMode.CREATIVE) {event.getItem().setAmount(item.getAmount()-1);};
        player.playSound(player, Sound.ENTITY_PLAYER_BURP, 1f, 10f);
        Random rng = new Random();
        int roll = rng.nextInt(0, 101);
        if (roll < 40) {
            HashMap<Integer, ItemStack> leftOver = new HashMap<Integer, ItemStack>();
            leftOver.putAll(player.getInventory().addItem(new ItemStack(Material.DIAMOND_BLOCK, 1)));
            if (!leftOver.isEmpty()) {
                player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(Material.getMaterial(String.valueOf(leftOver.get(0).getType())), leftOver.get(0).getAmount()));
            }
            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 3f, 10f);
        }
    }

    private void lifeTokenUse(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) { return; }
        Player player = event.getPlayer();
        if (plugin.getLives(player) < 10) {
            ItemStack item = event.getItem();
            if (player.getGameMode() != GameMode.CREATIVE) {event.getItem().setAmount(item.getAmount()-1);};
            plugin.setLives(player, plugin.getLives(player)+1);
            player.playSound(player, Sound.ENTITY_ARROW_HIT_PLAYER, 5f, 10f);
            player.sendMessage("§c[KapCore]§r: You just used a §b§uLife Token§r, you now have " + plugin.getLifeColored(player) + "§r live(s).");
        } else {
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 5f, 10f);
            player.sendMessage("§c[KapCore]§r: You already have the maximum number of lives. (10)");
        }
    }

    private void lightEnderPearlUse(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) { return; }
        Player player = event.getPlayer();
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20*20, 1, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30*20, 1, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 30*20, 1, true));
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            List<Entity> list = player.getNearbyEntities(0.5, 0.5, 0.5);
            for (Entity entity : list) {
                NBTEntity nbtEntity = new NBTEntity(entity);
                boolean isOwnerThrower = Objects.equals(nbtEntity.getUUID("Owner").toString(), player.getUniqueId().toString());
                if (entity.getType() == EntityType.ENDER_PEARL && isOwnerThrower) {
                    plugin.setDataString(entity, "Kap.EnderPearlType", "lightEnderPearl");
                }
            }
        }, 1L);
    }

    private void timeEnderPearlUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) { return; }
        if (plugin.getData(player, "Kap.TimePearlCooldown") + 20000 > System.currentTimeMillis()) {
            player.sendMessage(String.format("§c[KapCore] §rUtilisation sous cooldown, il vous reste: %ss", 20-(System.currentTimeMillis()-plugin.getData(player, "Kap.TimePearlCooldown"))/1000));
            player.playSound(player, Sound.ENTITY_ENDERMAN_SCREAM, 5f, 1f);
            event.setCancelled(true);
            return;
        }
        plugin.setData(player, "Kap.TimePearlCooldown", System.currentTimeMillis());
        plugin.setData(player, "Kap.LastTimePearled", 1L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            List<Entity> list = player.getNearbyEntities(0.5, 0.5, 0.5);
            for (Entity entity : list) {
                NBTEntity nbtEntity = new NBTEntity(entity);
                boolean isOwnerThrower = Objects.equals(nbtEntity.getUUID("Owner").toString(), player.getUniqueId().toString());
                if (entity.getType() == EntityType.ENDER_PEARL && isOwnerThrower) {
                    plugin.setDataString(entity, "Kap.EnderPearlType", "timeEnderPearl");
                }
            }
        }, 1L);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = false)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item != null && ItemManager.CustomItem.isCustomItem(item)) {
            switch (ItemManager.CustomItem.getCustomItemByItemStack(item)) {
                case "diamondCarrot" -> diamondCarrotUse(event);
                case "lifeToken" -> lifeTokenUse(event);
                case "lightEnderPearl" -> lightEnderPearlUse(event);
                case "timeEnderPearl" -> timeEnderPearlUse(event);
                default -> {}
            }
        }
    }
}
