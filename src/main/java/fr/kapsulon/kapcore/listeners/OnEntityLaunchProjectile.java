package fr.kapsulon.kapcore.listeners;

import fr.kapsulon.kapcore.KapCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;

public class OnEntityLaunchProjectile implements Listener {
    private final KapCore plugin;

    public OnEntityLaunchProjectile(KapCore plugin) {
        this.plugin = plugin;
    }

    private void lightEnderPearlThrow(ProjectileLaunchEvent event) {
        Entity entity = event.getEntity();
        Vector v = entity.getVelocity().multiply(2);
        entity.setVelocity(v);
    }

    private void timeEnderPearlThrow(ProjectileLaunchEvent event) {
        Entity entity = event.getEntity();
        Vector v = entity.getVelocity().multiply(1.5);
        entity.setVelocity(v);
    }

    @EventHandler
    public void onEntityLaunchProjectile(ProjectileLaunchEvent event) {
        if (event.getEntity().getType().equals(EntityType.ARROW)) {
            //event.getEntity().setBounce(true);
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            switch (plugin.getDataString(event.getEntity(), "Kap.EnderPearlType")) {
                case "lightEnderPearl":
                    lightEnderPearlThrow(event);
                case "timeEnderPearl":
                    timeEnderPearlThrow(event);
            }
        }, 1L);
    }
}
