package fr.kapsulon.kapcore.listeners;

import fr.kapsulon.kapcore.KapCore;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class OnPlayerSpawn implements Listener {
    private final KapCore plugin;

    public OnPlayerSpawn(KapCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerSpawn(PlayerRespawnEvent event) {
        if (plugin.getLives(event.getPlayer()) <= 0) {
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
            event.getPlayer().sendMessage("§cIl ne vous reste plus de vies, un autre joueur peut vous en donner une.");
        } else {
            event.getPlayer().setGameMode(GameMode.SURVIVAL);
        }
    }
}
