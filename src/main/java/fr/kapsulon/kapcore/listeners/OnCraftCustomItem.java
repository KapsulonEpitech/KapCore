package fr.kapsulon.kapcore.listeners;

import fr.kapsulon.kapcore.KapCore;
import fr.kapsulon.kapcore.items.ItemManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

public class OnCraftCustomItem implements Listener {
    private final KapCore plugin;

    public OnCraftCustomItem(KapCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCraftCustomItem(CraftItemEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        if (ItemManager.CustomItem.getCustomItemByItemStack(item) == "timeEnderPearl") {
            player.playSound(player, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 10f, 10f);
            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 10f, 10f);
            new ParticleBuilder(ParticleEffect.END_ROD, player.getLocation()).setAmount(100).display();
            new ParticleBuilder(ParticleEffect.END_ROD, event.getClickedInventory().getLocation().add(0, 1, 0)).setAmount(100).display();
        }
    }
}
