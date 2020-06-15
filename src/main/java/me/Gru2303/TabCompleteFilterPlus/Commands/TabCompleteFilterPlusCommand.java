package me.Gru2303.TabCompleteFilterPlus.Commands;

import me.Gru2303.TabCompleteFilterPlus.GeneralUtils;
import me.Gru2303.TabCompleteFilterPlus.Manager.ConfigManager;
import me.Gru2303.TabCompleteFilterPlus.Manager.PlayerManager;
import me.Gru2303.TabCompleteFilterPlus.TabCompleteFilterPlus;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import net.milkbowl.vault.permission.Permission;

import java.util.ArrayList;
import java.util.List;

public class TabCompleteFilterPlusCommand implements CommandExecutor, TabCompleter {

    private TabCompleteFilterPlus plugin = TabCompleteFilterPlus.getInstance();
    private Permission perms = plugin.getPerms();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sendMessage(sender);

        if (!(sender instanceof Player)) {
            commandHandler(sender, args);
        } else {
            Player p = (Player) sender;
            PlayerManager pm = new PlayerManager(p);

            if (pm.isAdmin()) {
                commandHandler(sender, args);
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> list = new ArrayList<String>();

        if (!(sender instanceof Player)) {
            list = getTabComplete(args);
        } else {
            Player p = (Player) sender;
            PlayerManager pm = new PlayerManager(p);

            if (pm.isAdmin()) {
                list = getTabComplete(args);
            }
        }

        return list;
    }

    private void commandHandler(CommandSender sender, String[] args) {
        if (args.length == 1 && args[0].equals("reload")) {
            reloadConfig(sender);
        } else {
            sendHelpMessage(sender);
        }
    }

    private void reloadConfig(CommandSender sender) {
        sender.sendMessage(GeneralUtils.messageFormat("&7&l[&a&lTCF+&7&l] &a&l> &fConfig successfully reloaded"));

        ConfigManager.load();
    }

    private void sendMessage(CommandSender sender) {
        sender.sendMessage(GeneralUtils.messageFormat("&7&l[&a&lTCF+&7&l] &fRunning &a" + plugin.systemName + " v" + plugin.version));
        sender.sendMessage(GeneralUtils.messageFormat("&7&l[&a&lTCF+&7&l] &cPlugin by Gru2303"));
    }

    private void sendHelpMessage(CommandSender sender) {
        sender.sendMessage(GeneralUtils.messageFormat("&a&l> &e/tabfilter reload - reload config"));
    }

    private List<String> getTabComplete(String[] args) {
        List<String> list = new ArrayList<String>();

        if (args.length == 1) {
            list.add("reload");
        }

        return list;
    }
}
