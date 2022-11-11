package eu.eclesia_network.bedwars.config;

import eu.eclesia_network.bedwars.utility.TextUtil;
import org.bukkit.configuration.file.FileConfiguration;

public enum Messages {

    PREFIX("GENERAL.PREFIX"),
    NO_PERMISSION("GENERAL.NO_PERMISSION"),
    CUSTOM_COMMAND_NO_PERMISSION("GENERAL.CUSTOM_COMMAND_NO_PERMISSION"),
    INVALID_PLAYER("GENERAL.INVALID_PLAYER"),
    CONFIG_RELOAD("GENERAL.CONFIG_RELOAD"),
    COOLDOWN_ACTIVE("GENERAL.COOLDOWN_ACTIVE");

    private static FileConfiguration config;
    private String path;

    Messages(String path) {
        this.path = path;
    }

    public static void setConfiguration(FileConfiguration c) {
        config = c;
    }

    @Override
    public String toString() {
        String message = config.getString("Messages." + this.path);

        if (message == null || message.isEmpty()) {
            return "DeluxeHub: message not found (" + this.path + ")";
        }

        String prefix = config.getString("Messages." + PREFIX.getPath());
        return TextUtil.color(message.replace("%prefix%", prefix != null && !prefix.isEmpty() ? prefix : ""));
    }

    public String getPath() {
        return this.path;
    }

}
