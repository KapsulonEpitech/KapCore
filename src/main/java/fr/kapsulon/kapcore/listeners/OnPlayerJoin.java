package fr.kapsulon.kapcore.listeners;

import fr.kapsulon.kapcore.KapCore;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoin implements Listener {
    private final KapCore plugin;

    public OnPlayerJoin(KapCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(plugin.getRankDisplayName(player) + "§r a rejoint.");
        plugin.getLives(event.getPlayer());
        if (plugin.getLives(event.getPlayer()) <= 0) {
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
            event.getPlayer().sendMessage("§cIl ne vous reste plus de vies, un autre joueur peut vous en donner une.");
        }
    }
}
