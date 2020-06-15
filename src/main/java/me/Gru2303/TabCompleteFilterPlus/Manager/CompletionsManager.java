package me.Gru2303.TabCompleteFilterPlus.Manager;

import me.Gru2303.TabCompleteFilterPlus.GeneralUtils;
import me.Gru2303.TabCompleteFilterPlus.TabCompleteFilterPlus;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.milkbowl.vault.permission.Permission;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CompletionsManager {

    private TabCompleteFilterPlus plugin = TabCompleteFilterPlus.getInstance();
    private FileConfiguration cfg = plugin.getConfig();
    private Permission perms = plugin.getPerms();

    private Collection<String> list;

    public CompletionsManager(Collection<String> list) {
        this.list = list;

        if (!ConfigManager.whiteListType) {
            removeUnnecessaryCompletions();
        }
    }

    public Collection<String> getCompletionsPlayer(Player p) {
        String group = perms.getPrimaryGroup(p);

        if (group != null) {
            ConfigurationSection section = cfg.getConfigurationSection("Groups." + group);

            if (section != null) {
                getGenerationCompletions(section);
            }
        }

        return this.list;
    }

    private void getGenerationCompletions(ConfigurationSection section) {
        List<String> showOtherGroupCommands = section.getStringList("showOtherGroupCommands");
        List<String> commands = section.getStringList("Commands");

        for (String s : showOtherGroupCommands) {
            ConfigurationSection ss = cfg.getConfigurationSection("Groups." + s);

            if (ss != null) {
                commands.addAll(ss.getStringList("Commands"));
            }
        }

        if (ConfigManager.whiteListType) {
            list.clear();
            list.addAll(commands);
        } else {
            for (String s : commands) {
                if (list.contains(s)) {
                    list.remove(s);
                }
            }
        }
    }

    private void removeUnnecessaryCompletions() {
        removeColonCompletions();
        removeTwoSlashCompletions();
    }

    private void removeColonCompletions() {
        if (ConfigManager.removeColonCompletions) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                String element = (String) iterator.next();

                if (element.contains(":")) {
                    iterator.remove();
                }
            }
        }
    }

    private void removeTwoSlashCompletions() {
        if (ConfigManager.removeTwoSlashCompletions) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                String element = (String) iterator.next();

                if (element.contains("/")) {
                    iterator.remove();
                }
            }
        }
    }
}
