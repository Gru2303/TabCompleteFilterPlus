package me.Gru2303.TabCompleteFilterPlus;

import me.Gru2303.TabCompleteFilterPlus.Commands.TabCompleteFilterPlusCommand;
import me.Gru2303.TabCompleteFilterPlus.Manager.ConfigManager;
import me.Gru2303.TabCompleteFilterPlus.listener.EventHandlerClass;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.permission.Permission;

public final class TabCompleteFilterPlus extends JavaPlugin {

    public String systemName = "TabCompleteFilterPlus";
    public String version = "1.0";

    private static TabCompleteFilterPlus instance;
    private static Permission perms;

    @Override
    public void onEnable() {
        instance = this;

        GeneralUtils.systemLog(GeneralUtils.getEnableSign(), false);

        setupPermissions();

        ConfigManager.load();

        getCommand("tabcompletefilterplus").setExecutor(new TabCompleteFilterPlusCommand());

        getServer().getPluginManager().registerEvents(new EventHandlerClass(), this);
    }

    @Override
    public void onDisable() {
        GeneralUtils.systemLog(GeneralUtils.getShutdownSign(), false);
    }

    public Permission getPerms() {
        return perms;
    }

    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();

        if (perms == null) {
            GeneralUtils.systemLog("&cError: &ffailed hooked to Vault!");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    public static TabCompleteFilterPlus getInstance() {
        return instance;
    }
}
