package fr.kapsulon.kapcore.commands;

import fr.kapsulon.kapcore.KapCore;
import fr.kapsulon.kapcore.items.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveLifeTokenCommand implements CommandExecutor {
    private final KapCore plugin;

    public GiveLifeTokenCommand(KapCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) { return false; }
        Player target = plugin.getServer().getPlayerExact(args[0]);
        if (target != null) {
            target.getInventory().addItem(ItemManager.lifeToken);
            return true;
        } else {
            return false;
        }
    }
}
