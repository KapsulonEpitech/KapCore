package fr.kapsulon.kapcore.commands;

import fr.kapsulon.kapcore.KapCore;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private final KapCore plugin;

    public SpawnCommand(KapCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            Location location = plugin.getConfig().getLocation("spawn");
            if (location != null) {
                player.teleport(location);
                player.sendMessage("§c[KapCore]§r: You have been teleported to spawn!");
            } else {
                player.sendMessage("§c[KapCore]§r: No spawn has been set!");
            }
        } else {
            System.out.println("Player only command.");
        }
        return true;
    }
}
