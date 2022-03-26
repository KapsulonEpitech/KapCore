package fr.kapsulon.kapcore.commands;

import fr.kapsulon.kapcore.KapCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLivesCommand implements CommandExecutor {

    private final KapCore plugin;

    public SetLivesCommand(KapCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) { return false; }
        Player target = plugin.getServer().getPlayerExact(args[0]);
        if (target != null) {
            String result = String.format("§b" + target.getName() + "'s lives have been set to: %s.", plugin.setLives(target, Integer.parseInt(args[1])));
            if (sender instanceof Player player) {
                player.sendMessage(result);
            } else {
                System.out.println(result);
            }
            return true;
        } else {
            return false;
        }
    }
}
