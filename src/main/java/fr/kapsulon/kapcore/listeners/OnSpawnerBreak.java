package fr.kapsulon.kapcore.listeners;

import de.tr7zw.nbtapi.NBTItem;
import fr.kapsulon.kapcore.KapCore;
import org.apache.commons.lang.StringUtils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.Objects;

public class OnSpawnerBreak implements Listener {
    private final KapCore plugin;

    public OnSpawnerBreak(KapCore plugin) {
        this.plugin = plugin;
    }

    public ItemStack getSpawnerFromEntityType(EntityType entityType) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.SPAWNER));
        ItemMeta meta = item.getItemMeta();

        NBTItem nbti = new NBTItem(item);
        nbti.setString("kap_mob", entityType.name());

        return nbti.getItem();
    }

    @EventHandler
    public void onSpawnerBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Block blockAbove = player.getWorld().getBlockAt(block.getLocation().getBlockX(), block.getLocation().getBlockY()+1, block.getLocation().getBlockZ());
        if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)
                && block.getType() == Material.SPAWNER
                && blockAbove.getType() == Material.BEACON
                && player.getGameMode() != GameMode.CREATIVE) {
            CreatureSpawner spawner = (CreatureSpawner) block.getState();
            ItemStack item = getSpawnerFromEntityType(spawner.getSpawnedType());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§r§b" + StringUtils.capitalize(spawner.getSpawnedType().toString().toLowerCase()) + " Spawner");
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);
            event.setExpToDrop(0);
            player.getWorld().dropItemNaturally(block.getLocation(), item);
            blockAbove.setType(Material.AIR);
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_HARP, 100f, 10f);
            new ParticleBuilder(ParticleEffect.ELECTRIC_SPARK, block.getLocation().add(0.5, 0.5, 0.5)).setOffset(1,1,1).setSpeed(0.5f).setAmount(100).display();
        }
    }
}
