package fr.kapsulon.kapcore.listeners;

import fr.kapsulon.kapcore.KapCore;
import org.apache.commons.lang.StringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnPlayerChat implements Listener {
    private final KapCore plugin;

    public OnPlayerChat(KapCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerChat(AsyncPlayerChatEvent event) {
        if (event.getPlayer().isOp()) {
            event.setFormat(String.format("%s: %s", event.getPlayer().getDisplayName(), StringUtils.replaceChars(event.getMessage(), '&', '§')));
        } else {
            event.setFormat(String.format("%s: %s", event.getPlayer().getDisplayName(), event.getMessage()));
        }
    }
}
