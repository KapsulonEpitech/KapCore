package fr.kapsulon.kapcore.listeners;

import fr.kapsulon.kapcore.KapCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoinNameChange implements Listener {

    private final KapCore plugin;

    public OnJoinNameChange(KapCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinNameChange(PlayerJoinEvent event) {
        String newName = plugin.getRankDisplayName(event.getPlayer());
        event.getPlayer().setDisplayName(newName);
    }
}
