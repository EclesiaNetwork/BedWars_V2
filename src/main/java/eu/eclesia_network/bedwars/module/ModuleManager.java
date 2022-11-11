package eu.eclesia_network.bedwars.module;


import eu.eclesia_network.bedwars.BedWars;
import eu.eclesia_network.bedwars.config.ConfigType;
import eu.eclesia_network.bedwars.module.modules.inventory.InventoryHandler;
import eu.eclesia_network.bedwars.module.modules.mysql.Mysql;
import eu.eclesia_network.bedwars.module.modules.player.PlayerListener;
import eu.eclesia_network.bedwars.module.modules.teams.Teams;
import eu.eclesia_network.bedwars.module.modules.world.WorldManager;
import org.bukkit.event.HandlerList;

import java.util.HashMap;
import java.util.Map;

public class ModuleManager {

    private BedWars plugin;
    private Map<ModuleType, Module> modules = new HashMap<>();

    public void loadModules(BedWars plugin) {
        this.plugin = plugin;

        if (!modules.isEmpty()) unloadModules();


        registerModule(new Mysql(plugin));
        registerModule(new InventoryHandler(plugin));
        registerModule(new PlayerListener(plugin));
        registerModule(new WorldManager(plugin));
        registerModule(new Teams(plugin));


        for (Module module : modules.values()) {
            try {
                module.onEnable();
            } catch (Exception e) {
                e.printStackTrace();
                plugin.getLogger().severe("============= TEMPLATE MODULE LOAD ERROR =============");
                plugin.getLogger().severe("There was an error loading the " + module.getModuleType() + " module");
                plugin.getLogger().severe("The plugin will now disable..");
                plugin.getLogger().severe("============= TEMPLATE MODULE LOAD ERROR =============");
                plugin.getServer().getPluginManager().disablePlugin(plugin);
                break;
            }
        }

        plugin.getLogger().info("Loaded " + modules.size() + " plugin modules.");
    }

    public void unloadModules() {
        for (Module module : modules.values()) {
            try {
                HandlerList.unregisterAll(module);
                module.onDisable();
            } catch (Exception e) {
                e.printStackTrace();
                plugin.getLogger().severe("There was an error unloading the " + module.getModuleType().toString() + " module.");
            }
        }
        modules.clear();
    }

    public Module getModule(ModuleType type) {
        return modules.get(type);
    }

    public void registerModule(Module module) {
        registerModule(module, null);
    }

    public void registerModule(Module module, String isEnabledPath) {
        BedWars plugin = module.getPlugin();
        if (isEnabledPath != null && !plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig().getBoolean(isEnabledPath, false))
            return;

        plugin.getServer().getPluginManager().registerEvents(module, plugin);
        modules.put(module.getModuleType(), module);
    }

    public boolean isEnabled(ModuleType type) {
        return modules.containsKey(type);
    }

}
