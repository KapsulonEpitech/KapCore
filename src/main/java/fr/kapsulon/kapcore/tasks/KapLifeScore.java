package fr.kapsulon.kapcore.tasks;

import fr.kapsulon.kapcore.KapCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class KapLifeScore extends BukkitRunnable {
    private final KapCore plugin;

    public KapLifeScore(KapCore plugin) {
        this.plugin = plugin;
    }

    private String getLifeColored(Player player) {
        int l = plugin.getLives(player);
        return switch (l) {
            case 0 -> (String.format("§8%s§r", l));
            case 1 -> (String.format("§4§l%s§r", l));
            case 2 -> (String.format("§4%s§r", l));
            case 3 -> (String.format("§c%s§r", l));
            case 4 -> (String.format("§6%s§r", l));
            case 5 -> (String.format("§e%s§r", l));
            case 6, 7 -> (String.format("§a%s§r", l));
            case 8 -> (String.format("§2%s§r", l));
            case 9 -> (String.format("§2§l%s§r", l));
            case 10 -> (String.format("§b%s§r", l));
            default -> "";
        };
    }

    @Override
    public void run() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("KapLifeDisplay", "dummy",
                "KapTest", RenderType.HEARTS);
        objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        for (Player player : Bukkit.getOnlinePlayers()) {
            Score score = objective.getScore(player.getName());
            score.setScore((int) player.getHealth());
            player.setScoreboard(scoreboard);
            player.setPlayerListName(String.format("%s [%s]",
                    plugin.getRankDisplayName(player), getLifeColored(player)));
        }
    }
}
