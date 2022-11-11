package eu.eclesia_network.bedwars.config;

import eu.eclesia_network.bedwars.BedWars;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private Map<ConfigType, ConfigHandler> configurations;

    public ConfigManager() {
        configurations = new HashMap<>();
    }

    public void loadFiles(BedWars plugin) {

        registerFile(ConfigType.SETTINGS, new ConfigHandler(plugin, "config"));
        registerFile(ConfigType.MYSQL, new ConfigHandler(plugin, "mysql"));
        registerFile(ConfigType.MESSAGES, new ConfigHandler(plugin, "messages"));

        configurations.values().forEach(ConfigHandler::saveDefaultConfig);

        Messages.setConfiguration(getFile(ConfigType.MESSAGES).getConfig());
    }

    public ConfigHandler getFile(ConfigType type) {
        return configurations.get(type);
    }

    public void reloadFiles() {
        configurations.values().forEach(ConfigHandler::reload);
        Messages.setConfiguration(getFile(ConfigType.MESSAGES).getConfig());
    }

    public void saveFiles() {
        //getFile(ConfigType.DATA).save();
    }

    public void registerFile(ConfigType type, ConfigHandler config) {
        configurations.put(type, config);
    }

    public FileConfiguration getFileConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

}
