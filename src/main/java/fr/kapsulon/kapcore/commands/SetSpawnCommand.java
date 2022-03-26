package fr.kapsulon.kapcore.commands;

import fr.kapsulon.kapcore.KapCore;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    private final KapCore plugin;

    public SetSpawnCommand(KapCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            Location location = player.getLocation();
            plugin.getConfig().set("spawn", location);
            plugin.saveConfig();
            player.sendMessage("§c[KapCore]§r: Spawn Location Set!");
        } else {
            System.out.println("Player only command.");
        }
        return true;
    }
}
