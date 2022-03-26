package fr.kapsulon.kapcore.listeners;

import de.tr7zw.nbtapi.NBTItem;
import fr.kapsulon.kapcore.KapCore;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class OnSpawnerPlace implements Listener {
    private final KapCore plugin;

    public OnSpawnerPlace(KapCore plugin) {
        this.plugin = plugin;
    }

    public EntityType getEntityTypeFromItemStack(ItemStack item) {
        EntityType entityType = null;

        NBTItem nbti = new NBTItem(item);
        if (nbti.hasKey("kap_mob")) {
            entityType = EntityType.valueOf(nbti.getString("kap_mob"));
            return entityType;
        }

        return entityType;
    }

    @EventHandler
    public void onSpawnerPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Material material = block.getType();
        ItemStack item = event.getItemInHand();
        NBTItem nbti = new NBTItem(item);
        if (block.getType() == Material.SPAWNER && nbti.hasKey("kap_mob")) {
            EntityType entityType = getEntityTypeFromItemStack(item);
            CreatureSpawner spawner = (CreatureSpawner) block.getState();
            spawner.setSpawnedType(entityType);
            spawner.update();
        }
    }
}
