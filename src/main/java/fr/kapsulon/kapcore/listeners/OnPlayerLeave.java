package fr.kapsulon.kapcore.listeners;

import fr.kapsulon.kapcore.KapCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerLeave implements Listener {
    private final KapCore plugin;

    public OnPlayerLeave(KapCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(plugin.getRankDisplayName(player) + "§r a quitté.");
    }
}
