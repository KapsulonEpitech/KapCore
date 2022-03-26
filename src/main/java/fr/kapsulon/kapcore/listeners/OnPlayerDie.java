package fr.kapsulon.kapcore.listeners;

import fr.kapsulon.kapcore.KapCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnPlayerDie implements Listener {
    private final KapCore plugin;

    public OnPlayerDie(KapCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        plugin.setLives(player, plugin.getLives(player)-1);
        event.setDeathMessage(player.getDisplayName() + " §rest mort. Il lui reste: §b" + plugin.getLives(player) + " vie(s)§r.");
    }
}
