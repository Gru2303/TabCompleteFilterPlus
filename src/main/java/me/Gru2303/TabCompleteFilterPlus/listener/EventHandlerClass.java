package me.Gru2303.TabCompleteFilterPlus.listener;

import me.Gru2303.TabCompleteFilterPlus.GeneralUtils;
import me.Gru2303.TabCompleteFilterPlus.Manager.ConfigManager;
import me.Gru2303.TabCompleteFilterPlus.Manager.PlayerManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.Collection;

public class EventHandlerClass implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerCommandSendEvent(PlayerCommandSendEvent e) {
        Player p = e.getPlayer();
        PlayerManager pm = new PlayerManager(p);
        Collection<String> commands = e.getCommands();

        if (!pm.isAdmin()) {
            Collection<String> list = pm.getCompletions(e.getCommands());

            checkWarnings(commands, list);

            commands.addAll(list);
            commands.add("tabfilter");
        }
    }

    private void checkWarnings(Collection<String> commands, Collection<String> list) {
        if (ConfigManager.whiteListType) {
            for (String s : list) {
                if (!commands.contains(s)) {
                    GeneralUtils.systemLog(String.format("&6Warning: &fUnable to delete command, that the player cannot access. (%s)", s));
                }
            }
        }
    }
}
