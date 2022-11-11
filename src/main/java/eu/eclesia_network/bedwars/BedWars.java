package eu.eclesia_network.bedwars;


import eu.eclesia_network.bedwars.command.CommandManager;
import eu.eclesia_network.bedwars.config.ConfigManager;
import eu.eclesia_network.bedwars.module.ModuleManager;
import eu.eclesia_network.bedwars.module.modules.mysql.Mysql;
import eu.eclesia_network.bedwars.utility.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class BedWars extends JavaPlugin {

	public static int SERVER_VERSION;
	private ConfigManager configManager;
	private ModuleManager moduleManager;

	@Override
	public void onEnable() {
		long start = System.currentTimeMillis();

		getLogger().info("");
		getLogger().info("______          _ _    _                ");
		getLogger().info("| ___ \\        | | |  | |               ");
		getLogger().info("| |_/ / ___  __| | |  | | __ _ _ __ ___ ");
		getLogger().info("| ___ \\/ _ \\/ _` | |/\\| |/ _` | '__/ __|");
		getLogger().info("| |_/ /  __/ (_| \\  /\\  / (_| | |  \\__ \\");
		getLogger().info("\\____/ \\___|\\__,_|\\/  \\/ \\__,_|_|  |___/");
		getLogger().info("");
		getLogger().info("Version: "+getDescription().getVersion());
		getLogger().info("Auteurs: Kurama et DarknessOmbrage");
		getLogger().info("");

		try {
			Class.forName("org.spigotmc.SpigotConfig");
		} catch(ClassNotFoundException ex) {
			getLogger().severe("/!\\ SPIGOT NON DETECTÉE /!\\");
			getLogger().severe("Template_PaperMC a besoin de spigot pour fonctionner !");
			getLogger().severe("Le plugin est maintenant désactivé.");
			getLogger().severe("/!\\ SPIGOT NON DETECTÉE /!\\");
			getPluginLoader().disablePlugin(this);
			return;
		}

		SERVER_VERSION = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].replace(".", "#").split("#")[1]);
		if(SERVER_VERSION > 15) TextUtil.HEX_USE = true;


		configManager = new ConfigManager();
		configManager.loadFiles(this);

		if(!getServer().getPluginManager().isPluginEnabled(this)) return;
		getCommand("template").setExecutor(new CommandManager(this));

		moduleManager = new ModuleManager();
		moduleManager.loadModules(this);

		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

		if(getServer().getPluginManager().getPlugin("PlaceholderAPI") == null){
			getLogger().warning("Le Plugin PlaceholderAPI est manquant !");
		}

		getLogger().info("");
		getLogger().info("Successfully loaded in "+ (System.currentTimeMillis() - start)+" ms");

	}

	@Override
	public void onDisable() {
		Bukkit.getScheduler().cancelTasks(this);
		moduleManager.unloadModules();
		configManager.saveFiles();
		if(Mysql.dataSource != null) Mysql.dataSource.close();
	}

	public void reload(){

		//Mysql.reload = true;
		if(Mysql.dataSource != null) Mysql.dataSource.close();
		Bukkit.getScheduler().cancelTasks(this);
		HandlerList.unregisterAll(this);
		configManager.reloadFiles();
		moduleManager.loadModules(this);
		//Mysql.reload = false;

	}

	public ModuleManager getModuleManager() { return moduleManager; }
	public int getServerVersionNumber() { return SERVER_VERSION; }
	public ConfigManager getConfigManager() { return configManager; }

}