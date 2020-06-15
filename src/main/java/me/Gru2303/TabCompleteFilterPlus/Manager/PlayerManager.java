package me.Gru2303.TabCompleteFilterPlus.Manager;

import me.Gru2303.TabCompleteFilterPlus.GeneralUtils;
import me.Gru2303.TabCompleteFilterPlus.TabCompleteFilterPlus;

import org.bukkit.entity.Player;

import net.milkbowl.vault.permission.Permission;

import java.util.Collection;

public class PlayerManager {

    private Permission perms = TabCompleteFilterPlus.getInstance().getPerms();

    private Player p;

    public PlayerManager(Player p) {
        this.p = p;
    }

    public boolean isAdmin() {
        return (perms.has(p, "TabCompleteFilterPlus.admin") || perms.has(p, "*") || p.isOp());
    }

    public Collection<String> getCompletions(Collection<String> list) {
        CompletionsManager cm = new CompletionsManager(list);

        return cm.getCompletionsPlayer(p);
    }
}
