package eu.eclesia_network.bedwars.module.modules.player;



import eu.eclesia_network.bedwars.BedWars;
import eu.eclesia_network.bedwars.GState;
import eu.eclesia_network.bedwars.config.ConfigType;
import eu.eclesia_network.bedwars.module.ModuleType;
import eu.eclesia_network.bedwars.module.modules.mysql.Mysql;
import eu.eclesia_network.bedwars.module.modules.teams.Teams;
import eu.eclesia_network.bedwars.utility.PlaceholderUtil;
import eu.eclesia_network.bedwars.utility.TextUtil;
import eu.eclesia_network.bedwars.module.Module;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.Conversation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.List;

public class PlayerListener extends Module {

    private boolean joinQuitMessagesEnabled;
    private String joinMessage;
    private String quitMessage;
    Scoreboard scoreboard = getPlugin().getServer().getScoreboardManager().getMainScoreboard();
    private BedWars plugin;




    public PlayerListener(BedWars plugin) {
        super(plugin, ModuleType.PLAYER_LISTENER);
        this.plugin = plugin;
    }

    @Override
    public void onEnable() {


    }

    @Override
    public void onDisable() {

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        p.getInventory().clear();

        ItemStack book = new ItemStack(Material.BOOK, 1);
        ItemMeta im1 = book.getItemMeta();
        im1.setDisplayName(ChatColor.RED+"Choix Équipe");
        im1.addEnchant(Enchantment.ARROW_INFINITE,1,false);
        im1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        book.setItemMeta(im1);

        p.getInventory().setItem(4, book);

        p.updateInventory();

        Location spawn = new Location(Bukkit.getWorld("lobby"), 0.418, -60, 0.555, -1.1f, -4.9f);
        p.teleport(spawn);
        p.setFoodLevel(20);
        p.setHealth(20);

        Mysql.setState(GState.WAITING_STARTING, "lobby");

        if (!Mysql.states.equalsIgnoreCase(String.valueOf(GState.WAITING_STARTING))){
            p.setGameMode(GameMode.SPECTATOR);
            p.sendMessage(ChatColor.GOLD+"Le jeu a déjà démarré !");
            event.setJoinMessage(null);
            return;
        }

        if (Mysql.states.equalsIgnoreCase(String.valueOf(GState.WAITING_STARTING))) {
            p.setGameMode(GameMode.ADVENTURE);
            event.setJoinMessage(ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "Bedwars" + ChatColor.DARK_GRAY + "] " + ChatColor.GOLD + p.getName() + ChatColor.GREEN + " viens de rejoindre la partie ! <" + ChatColor.WHITE + plugin.getServer().getOnlinePlayers().size() + ChatColor.YELLOW + "/" + ChatColor.WHITE + p.getServer().getMaxPlayers() + ChatColor.GREEN + ">");
        }
        /*if (Mysql.states.equalsIgnoreCase(String.valueOf(GState.WAITING_STARTING)) && plugin.getServer().getOnlinePlayers().size() == 2) {
            Autostart start = new Autostart(plugin);
            start.runTaskTimer(plugin, 0, 20);
            Mysql.setState(GState.STARTING, "1");
        }*/


    }



    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();


    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

    }



}
