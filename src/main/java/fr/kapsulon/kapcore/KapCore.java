package fr.kapsulon.kapcore;

import fr.kapsulon.kapcore.commands.*;
import fr.kapsulon.kapcore.items.ItemManager;
import fr.kapsulon.kapcore.listeners.*;
import fr.kapsulon.kapcore.tasks.KapLifeScore;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class KapCore extends JavaPlugin implements Listener {
    public String getDisplayPrefix(Player player) {
        return switch (player.getName()) {
            case "Kapsulon" -> ("\uE001");
            case "KuZanee" -> ("\uE002");
            case "Erreyfy" -> ("\uE003");
            case "Stayzen" -> ("\uE004");
            case "GabiFrsn" -> ("\uE005");
            case "LiliMadWoman" -> ("\uE006");
            default -> ("\uE000");
        };
    }

    public String getRankDisplayName(Player player) {
        String newName;
        if (player.isOp()) {
            newName = String.format("%s§c%s§r", getDisplayPrefix(player), player.getName());
        } else {
            newName = String.format("%s%s", getDisplayPrefix(player), player.getName());
        }
        return (newName);
    }

    public String getLifeColored(Player player) {
        int l = getLives(player);
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

    public String getDataString(Entity entity, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(this, key);
        PersistentDataContainer data = entity.getPersistentDataContainer();
        if (!data.has(namespacedKey, PersistentDataType.STRING)) { return "";}
        return (data.get(namespacedKey, PersistentDataType.STRING));
    }

    public String setDataString(Entity entity, String key, String d) {
        NamespacedKey namespacedKey = new NamespacedKey(this, key);
        PersistentDataContainer data = entity.getPersistentDataContainer();
        data.set(namespacedKey, PersistentDataType.STRING, d);
        return (d);
    }

    public long getData(Entity entity, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(this, key);
        PersistentDataContainer data = entity.getPersistentDataContainer();
        if (!data.has(namespacedKey, PersistentDataType.LONG)) { return -1;}
        return (data.get(namespacedKey, PersistentDataType.LONG));
    }

    public long setData(Entity entity, String key, long nb) {
        NamespacedKey namespacedKey = new NamespacedKey(this, key);
        PersistentDataContainer data = entity.getPersistentDataContainer();
        data.set(namespacedKey, PersistentDataType.LONG, nb);
        return (nb);
    }

    public int getLives(Player player) {
        NamespacedKey lives = new NamespacedKey(this, "KapCore.Lives");
        PersistentDataContainer data = player.getPersistentDataContainer();
        if (!data.has(lives, PersistentDataType.INTEGER)) {
            System.out.println("no data found for: " + player.getName() + ", setting them to 3. (default)");
            data.set(lives, PersistentDataType.INTEGER, 3);
        }
        return (data.get(lives, PersistentDataType.INTEGER));
    }

    public int setLives(Player player, int nb) {
        NamespacedKey lives = new NamespacedKey(this, "KapCore.Lives");
        PersistentDataContainer data = player.getPersistentDataContainer();
        if (!data.has(lives, PersistentDataType.INTEGER) && nb >= 1) { return (-1); }
        data.set(lives, PersistentDataType.INTEGER, nb);
        System.out.println(player.getName() + "'s lives has been set to " + nb + ".");
        return (nb);
    }

    public void buildCommands() {
        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("getlives").setExecutor(new GetLivesCommand(this));
        getCommand("setlives").setExecutor(new SetLivesCommand(this));
        getCommand("givelife").setExecutor(new GiveLifeCommand(this));
        getCommand("givelifetoken").setExecutor(new GiveLifeTokenCommand(this));
        getCommand("givecustomitem").setExecutor(new GiveCustomItem(this));
    }

    public void buildListeners() {
        getServer().getPluginManager().registerEvents(new OnJoinNameChange(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerChat(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerLeave(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDie(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerSpawn(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteract(this), this);
        getServer().getPluginManager().registerEvents(new OnCustomFoodEat(this), this);
        getServer().getPluginManager().registerEvents(new OnSpawnerBreak(this), this);
        getServer().getPluginManager().registerEvents(new OnSpawnerPlace(this), this);
        getServer().getPluginManager().registerEvents(new OnCraftCustomItem(this), this);
        getServer().getPluginManager().registerEvents(new OnEntityLaunchProjectile(this), this);
        //getServer().getPluginManager().registerEvents(new OnProjectileHitEvent(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerTeleport(this), this);
    }

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        buildCommands();
        buildListeners();
        ItemManager.init();
        BukkitTask KapScoreTask = new KapLifeScore(this).runTaskTimer(this, 0L, 10L);
    }
}
