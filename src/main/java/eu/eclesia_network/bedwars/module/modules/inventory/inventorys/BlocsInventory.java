package eu.eclesia_network.bedwars.module.modules.inventory.inventorys;

import eu.eclesia_network.bedwars.module.modules.inventory.SubInventory;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Collections;

import static eu.eclesia_network.bedwars.command.CommandManager.getPlugin;

public class BlocsInventory extends SubInventory {

    Scoreboard scoreboard = getPlugin().getServer().getScoreboardManager().getMainScoreboard();


    @Override
    public void performS(Player p) {

        Inventory inv = Bukkit.createInventory(p, InventoryType.CHEST, ChatColor.AQUA+"Marchand de blocs");

        if (scoreboard.getTeam("Rouge").hasPlayer(p)){
            ItemStack redterracota = new ItemStack(Material.RED_TERRACOTTA, 64);
            ItemMeta im5 = redterracota.getItemMeta();
            im5.setDisplayName(ChatColor.GOLD+"Prix : 128 briques");
            redterracota.setItemMeta(im5);
            inv.setItem(0, redterracota);
        } if (scoreboard.getTeam("Bleu").hasPlayer(p)){
            ItemStack blueterracota = new ItemStack(Material.BLUE_TERRACOTTA, 64);
            ItemMeta im5 = blueterracota.getItemMeta();
            im5.setDisplayName(ChatColor.GOLD+"Prix : 128 briques");
            blueterracota.setItemMeta(im5);
            inv.setItem(0, blueterracota);
        }if (scoreboard.getTeam("Jaune").hasPlayer(p)){
            ItemStack yellowterracota = new ItemStack(Material.YELLOW_TERRACOTTA, 64);
            ItemMeta im5 = yellowterracota.getItemMeta();
            im5.setDisplayName(ChatColor.GOLD+"Prix : 128 briques");
            yellowterracota.setItemMeta(im5);
            inv.setItem(0, yellowterracota);
        }if (scoreboard.getTeam("Vert").hasPlayer(p)) {
            ItemStack greenterracota = new ItemStack(Material.GREEN_TERRACOTTA, 64);
            ItemMeta im5 = greenterracota.getItemMeta();
            im5.setDisplayName(ChatColor.GOLD+"Prix : 128 briques");
            greenterracota.setItemMeta(im5);
            inv.setItem(0, greenterracota);
        }

        ItemStack stone = new ItemStack(Material.STONE, 64);
        ItemMeta im1 = stone.getItemMeta();
        im1.setDisplayName(ChatColor.GOLD+"Prix : 128 briques");
        stone.setItemMeta(im1);
        inv.setItem(10, stone);

        ItemStack sandstone = new ItemStack(Material.CUT_SANDSTONE, 64);
        ItemMeta im2 = sandstone.getItemMeta();
        im2.setDisplayName(ChatColor.GOLD+"Prix : 75 briques");
        sandstone.setItemMeta(im2);
        inv.setItem(12, sandstone);

        ItemStack wood = new ItemStack(Material.ACACIA_WOOD, 64);
        ItemMeta im3 = wood.getItemMeta();
        im3.setDisplayName(ChatColor.GOLD+"Prix : 50 briques");
        wood.setItemMeta(im3);
        inv.setItem(14, wood);

        ItemStack glassblock = new ItemStack(Material.GLASS, 64);
        ItemMeta im4 = glassblock.getItemMeta();
        im4.setDisplayName(ChatColor.GOLD+"Prix : 20 Irons");
        glassblock.setItemMeta(im4);
        inv.setItem(16, glassblock);

        for(int i=0; i<inv.getSize(); i++){
            ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
            ItemStack item2 = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
            ItemMeta im99 = item.getItemMeta();
            im99.setDisplayName(" "); im99.setLore(Collections.singletonList(" "));
            item.setItemMeta(im99); item2.setItemMeta(im99);
            if(inv.getItem(i) != null) continue;
            if(i % 2 == 0) inv.setItem(i, item2); else inv.setItem(i, item);
        }
        p.openInventory(inv);
    }

    @Override
    public void performM(Player p, Player target) {

    }

    @Override
    public void performU(World world) {

        Location loc1 = new Location(world,62, -2, -7);
        Location loc2 = new Location(world, 5, -2, 61);
        Location loc3 = new Location(world, -64, -2, 5);
        Location loc4 = new Location(world, -7, -2, -64);

        Entity villager1 = world.spawnEntity(loc1, EntityType.VILLAGER);
        Entity villager2 = world.spawnEntity(loc2, EntityType.VILLAGER);
        Entity villager3 = world.spawnEntity(loc3, EntityType.VILLAGER);
        Entity villager4 = world.spawnEntity(loc4, EntityType.VILLAGER);

        villager1.setCustomName(ChatColor.AQUA+"Marchand de blocs");
        villager1.setInvulnerable(true);
        villager1.setPersistent(true);
        villager1.setSilent(true);

        villager2.setCustomName(ChatColor.AQUA+"Marchand de blocs");
        villager2.setInvulnerable(true);
        villager2.setPersistent(true);
        villager2.setSilent(true);

        villager3.setCustomName(ChatColor.AQUA+"Marchand de blocs");
        villager3.setInvulnerable(true);
        villager3.setPersistent(true);
        villager3.setSilent(true);

        villager4.setCustomName(ChatColor.AQUA+"Marchand de blocs");
        villager4.setInvulnerable(true);
        villager4.setPersistent(true);
        villager4.setSilent(true);

    }
}
