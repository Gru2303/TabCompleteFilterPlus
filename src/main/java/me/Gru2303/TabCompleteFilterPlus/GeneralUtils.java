package me.Gru2303.TabCompleteFilterPlus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class GeneralUtils {

    private static TabCompleteFilterPlus plugin = TabCompleteFilterPlus.getInstance();
    private static String systemName = plugin.systemName;
    private static String version = plugin.version;

    public static void systemLog(String str) {
        systemLog(str, true);
    }

    public static void systemLog(String str, boolean isUsedPrefix) {
        String prefix = (isUsedPrefix) ? "&l&8[&a&l" + systemName + "&l&8]&r " : "";
        String message = messageFormat(prefix + str);

        Bukkit.getServer().getConsoleSender().sendMessage(message);
    }

    public static String messageFormat(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static String getEnableSign() {
        return "\n \n&8[]=====[&aEnabling " + systemName +"&8]=====[]"
                + "\n&8| &cInformation:"
                + "\n&8|   &cName: &a" + systemName
                + "\n&8|   &cDeveloper: &aGru2303"
                + "\n&8|   &cVersion: &a" + version
                + "\n&8| &cSupport:"
                + "\n&8|   &cGithub: &ahttps://github.com/Gru2303"
                + "\n&8|   &cTelegram: &ahttps://t.me/Gru2303"
                + "\n&8|   &cDiscord: &aGru2303#4252"
                + "\n&8[]==========================================[]&r\n";
    }

    public static String getShutdownSign() {
        return "\n \n&8[]=====[&aDisabling " + systemName +"&8]=====[]"
                + "\n&8| &cInformation:"
                + "\n&8|   &cName: &a" + systemName
                + "\n&8|   &cDeveloper: &aGru2303"
                + "\n&8|   &cVersion: &a" + version
                + "\n&8| &cSupport:"
                + "\n&8|   &cGithub: &ahttps://github.com/Gru2303"
                + "\n&8|   &cTelegram: &ahttps://t.me/Gru2303"
                + "\n&8|   &cDiscord: &aGru2303#4252"
                + "\n&8[]===========================================[]&r\n";
    }
}
