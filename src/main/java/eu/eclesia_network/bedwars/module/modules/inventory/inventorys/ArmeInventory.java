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

import java.util.Collections;

public class ArmeInventory extends SubInventory {
    @Override
    public void performS(Player p) {

        Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, ChatColor.RED+"Marchand d'armes");

        ItemStack ironsword = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta im1 = ironsword.getItemMeta();
        im1.setDisplayName(ChatColor.GOLD+"Prix : 30 lingots de fer");
        ironsword.setItemMeta(im1);
        inv.setItem(10, ironsword);

        ItemStack goldensword = new ItemStack(Material.GOLDEN_SWORD, 1);
        ItemMeta im2 = goldensword.getItemMeta();
        im2.setDisplayName(ChatColor.GOLD+"Prix : 30 lingots d'or et 2 Émeraudes ");
        goldensword.setItemMeta(im2);
        inv.setItem(12, goldensword);

        ItemStack diamondsword = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta im3 = diamondsword.getItemMeta();
        im3.setDisplayName(ChatColor.GOLD+"Prix : 15 Émeraudes ");
        diamondsword.setItemMeta(im3);
        inv.setItem(14, diamondsword);

        ItemStack bow = new ItemStack(Material.BOW, 1);
        ItemMeta im4 = bow.getItemMeta();
        im4.setDisplayName(ChatColor.GOLD+"Prix : 20 lingots de fer et 10 lingots d'or ");
        bow.setItemMeta(im4);
        inv.setItem(16, bow);

        ItemStack arrow = new ItemStack(Material.ARROW, 32);
        ItemMeta im5 = arrow.getItemMeta();
        im5.setDisplayName(ChatColor.GOLD+"Prix : 10 lingots d'or et 1 émeraude");
        arrow.setItemMeta(im5);
        inv.setItem(25, arrow);

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
        Location loc1 = new Location(world,62, -2, 5);
        Location loc2 = new Location(world, -7, -2, 61);
        Location loc3 = new Location(world, -64, -2, -7);
        Location loc4 = new Location(world, 5, -2, -64);

        Entity villager1 = world.spawnEntity(loc1, EntityType.VILLAGER);
        Entity villager2 = world.spawnEntity(loc2, EntityType.VILLAGER);
        Entity villager3 = world.spawnEntity(loc3, EntityType.VILLAGER);
        Entity villager4 = world.spawnEntity(loc4, EntityType.VILLAGER);

        villager1.setCustomName(ChatColor.RED+"Marchand d'armes");
        villager1.setInvulnerable(true);
        villager1.setPersistent(true);
        villager1.setSilent(true);

        villager2.setCustomName(ChatColor.RED+"Marchand d'armes");
        villager2.setInvulnerable(true);
        villager2.setPersistent(true);
        villager2.setSilent(true);

        villager3.setCustomName(ChatColor.RED+"Marchand d'armes");
        villager3.setInvulnerable(true);
        villager3.setPersistent(true);
        villager3.setSilent(true);

        villager4.setCustomName(ChatColor.RED+"Marchand d'armes");
        villager4.setInvulnerable(true);
        villager4.setPersistent(true);
        villager4.setSilent(true);
    }
}
