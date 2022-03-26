package fr.kapsulon.kapcore.commands;

import fr.kapsulon.kapcore.KapCore;
import fr.kapsulon.kapcore.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveCustomItem implements CommandExecutor {
    private final KapCore plugin;

    public GiveCustomItem(KapCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) { return false; }
        Player player = Bukkit.getServer().getPlayerExact(args[0]);
        if (player == null) { return false; }
        ItemStack item = ItemManager.CustomItem.getCustomItemByCode(args[1]);
        if (item == null) { return false; }
        player.getInventory().addItem(item);
        player.sendMessage(String.format("§c[KapCore]§r: Gave %s§r [%s§r] to %s", item.getAmount(), item.getItemMeta().getDisplayName(), player.getDisplayName()));
        return true;
    }
}
