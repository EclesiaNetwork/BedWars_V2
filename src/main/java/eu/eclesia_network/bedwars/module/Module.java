package eu.eclesia_network.bedwars.module;

import eu.eclesia_network.bedwars.BedWars;
import eu.eclesia_network.bedwars.config.ConfigType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

public abstract class Module implements Listener {

    private BedWars plugin;
    private ModuleType moduleType;

    public Module(BedWars plugin, ModuleType type) {
        this.plugin = plugin;
        this.moduleType = type;
    }


    public BedWars getPlugin() {
        return plugin;
    }


    public FileConfiguration getConfig(ConfigType type) {
        return getPlugin().getConfigManager().getFile(type).getConfig();
    }

    /*public void executeActions(Player player, List<String> actions) {
        getPlugin().getActionManager().executeActions(player, actions);
    }*/

    public ModuleType getModuleType() {
        return moduleType;
    }

    public abstract void onEnable();

    public abstract void onDisable();

}
