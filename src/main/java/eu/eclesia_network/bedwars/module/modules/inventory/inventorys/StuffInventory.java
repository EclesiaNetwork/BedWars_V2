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

public class StuffInventory extends SubInventory {
    @Override
    public void performS(Player p) {

        Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST.getDefaultSize()*2, ChatColor.BLUE+"Marchand de stuff");

        ItemStack diamondaxe = new ItemStack(Material.DIAMOND_AXE, 1);
        ItemMeta im1 = diamondaxe.getItemMeta();
        im1.setDisplayName(ChatColor.GOLD+"Prix : 25 lingots d'or + 5 émeraudes");
        diamondaxe.setItemMeta(im1);
        inv.setItem(10, diamondaxe);

        ItemStack diamondpickaxe = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemMeta im2 = diamondpickaxe.getItemMeta();
        im2.setDisplayName(ChatColor.GOLD+"Prix : 25 lingots d'or + 5 émeraudes");
        diamondpickaxe.setItemMeta(im2);
        inv.setItem(12, diamondpickaxe);

        ItemStack flint = new ItemStack(Material.FLINT_AND_STEEL, 1);
        ItemMeta im3 = flint.getItemMeta();
        im3.setDisplayName(ChatColor.GOLD+"Prix : 15 émeraudes");
        flint.setItemMeta(im3);
        inv.setItem(14, flint);

        ItemStack tnt = new ItemStack(Material.TNT, 10);
        ItemMeta im4 = tnt.getItemMeta();
        im4.setDisplayName(ChatColor.GOLD+"Prix : 64 lingots d'or et 64 émeraudes");
        tnt.setItemMeta(im4);
        inv.setItem(16, tnt);

        ItemStack diamondhelmet = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemMeta im5 = diamondhelmet.getItemMeta();
        im5.setDisplayName(ChatColor.GOLD+"Prix : 20 émeraudes");
        diamondhelmet.setItemMeta(im5);
        inv.setItem(37, diamondhelmet);

        ItemStack diamondchesplate = new ItemStack(Material.DIAMOND_CHESTPLATE,1);
        ItemMeta im6 = diamondchesplate.getItemMeta();
        im6.setDisplayName(ChatColor.GOLD+"Prix : 20 émeraudes");
        diamondchesplate.setItemMeta(im6);
        inv.setItem(39, diamondchesplate);

        ItemStack diamondleggings = new ItemStack(Material.DIAMOND_LEGGINGS,1);
        ItemMeta im7 = diamondleggings.getItemMeta();
        im7.setDisplayName(ChatColor.GOLD+"Prix : 20 émeraudes");
        diamondleggings.setItemMeta(im7);
        inv.setItem(41, diamondleggings);

        ItemStack diamondboots = new ItemStack(Material.DIAMOND_BOOTS,1);
        ItemMeta im8 = diamondboots.getItemMeta();
        im8.setDisplayName(ChatColor.GOLD+"Prix : 20 émeraudes");
        diamondboots.setItemMeta(im8);
        inv.setItem(43, diamondboots);

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

        Location loc1 = new Location(world,62, -2, 3);
        Location loc2 = new Location(world, -5, -2, 61);
        Location loc3 = new Location(world, -64, -2, -5);
        Location loc4 = new Location(world, 3, -2, -64);

        Entity villager1 = world.spawnEntity(loc1, EntityType.VILLAGER);
        Entity villager2 = world.spawnEntity(loc2, EntityType.VILLAGER);
        Entity villager3 = world.spawnEntity(loc3, EntityType.VILLAGER);
        Entity villager4 = world.spawnEntity(loc4, EntityType.VILLAGER);

        villager1.setCustomName(ChatColor.BLUE+"Marchand de stuff");
        villager1.setInvulnerable(true);
        villager1.setPersistent(true);
        villager1.setSilent(true);

        villager2.setCustomName(ChatColor.BLUE+"Marchand de stuff");
        villager2.setInvulnerable(true);
        villager2.setPersistent(true);
        villager2.setSilent(true);

        villager3.setCustomName(ChatColor.BLUE+"Marchand de stuff");
        villager3.setInvulnerable(true);
        villager3.setPersistent(true);
        villager3.setSilent(true);

        villager4.setCustomName(ChatColor.BLUE+"Marchand de stuff");
        villager4.setInvulnerable(true);
        villager4.setPersistent(true);
        villager4.setSilent(true);

    }
}
