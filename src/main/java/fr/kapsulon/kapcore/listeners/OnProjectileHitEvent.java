package fr.kapsulon.kapcore.listeners;

import fr.kapsulon.kapcore.KapCore;
import org.bukkit.entity.Explosive;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class OnProjectileHitEvent implements Listener {
    private final KapCore plugin;

    public OnProjectileHitEvent(KapCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProjectileHitEvent(ProjectileHitEvent event) {
        /*event.getEntity().setBounce(false);
        event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 5, false, true);
        event.getEntity().remove();*/
    }
}
