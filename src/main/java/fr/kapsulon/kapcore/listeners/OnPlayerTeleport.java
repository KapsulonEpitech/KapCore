package fr.kapsulon.kapcore.listeners;

import fr.kapsulon.kapcore.KapCore;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class OnPlayerTeleport implements Listener {
    private final KapCore plugin;

    public OnPlayerTeleport(KapCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL) && plugin.getData(player, "Kap.LastTimePearled") == 1L) {
            player.sendMessage("§c[KapCore]§r: You will be teleported back in 10 seconds...");
            plugin.setData(player, "Kap.LastTimePearled", 0L);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                player.teleport(event.getFrom());
                player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT, 10f, 1f);
            }, 10 * 20L);
        }
    }
}
