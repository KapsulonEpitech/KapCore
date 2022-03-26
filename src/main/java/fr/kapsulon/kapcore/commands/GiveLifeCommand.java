package fr.kapsulon.kapcore.commands;

import fr.kapsulon.kapcore.KapCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveLifeCommand implements CommandExecutor {

    private final KapCore plugin;

    public GiveLifeCommand(KapCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) { return false; }
        Player target = plugin.getServer().getPlayerExact(args[0]);
        if (target != null && sender instanceof Player player) {
            if (plugin.getLives(player) > 1) {
                plugin.setLives(player, plugin.getLives(player)-1);
                plugin.setLives(target, plugin.getLives(target)+1);
                if (plugin.getLives(target) == 1) {
                    plugin.setLives(target, plugin.getLives(target)+1);
                    target.setHealth(0);
                }
                return true;
            }
        } else {
            return false;
        }
        return false;
    }
}
