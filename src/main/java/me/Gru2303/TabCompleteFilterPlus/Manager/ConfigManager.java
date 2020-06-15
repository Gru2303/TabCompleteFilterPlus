package me.Gru2303.TabCompleteFilterPlus.Manager;

import me.Gru2303.TabCompleteFilterPlus.TabCompleteFilterPlus;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private static TabCompleteFilterPlus plugin = TabCompleteFilterPlus.getInstance();
    private static FileConfiguration cfg = plugin.getConfig();

    public static boolean removeColonCompletions;
    public static boolean removeTwoSlashCompletions;
    public static boolean whiteListType;

    public static void load() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();

        removeColonCompletions = cfg.getBoolean("Settings.removeColonCompletions");
        removeTwoSlashCompletions = cfg.getBoolean("Settings.removeTwoSlashCompletions");
        whiteListType = cfg.getBoolean("Settings.isWhiteListType");
    }
}
