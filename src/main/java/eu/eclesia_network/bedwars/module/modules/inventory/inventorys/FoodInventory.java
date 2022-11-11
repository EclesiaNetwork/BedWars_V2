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

public class FoodInventory extends SubInventory {
    @Override
    public void performS(Player p) {

        Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, ChatColor.GOLD+"Marchand de nourriture");

        ItemStack apple = new ItemStack(Material.APPLE, 32);
        ItemMeta im1 = apple.getItemMeta();
        im1.setDisplayName(ChatColor.GOLD+"Prix : 25 lingots d'or");
        apple.setItemMeta(im1);
        inv.setItem(10, apple);

        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 32);
        ItemMeta im2 = steak.getItemMeta();
        im2.setDisplayName(ChatColor.GOLD+"Prix : 30 lingots d'or");
        steak.setItemMeta(im2);
        inv.setItem(12, steak);

        ItemStack goldenapple = new ItemStack(Material.GOLDEN_APPLE, 10);
        ItemMeta im3 = goldenapple.getItemMeta();
        im3.setDisplayName(ChatColor.GOLD+"Prix : 30 lingots d'or et 10 émeraudes");
        goldenapple.setItemMeta(im3);
        inv.setItem(14, goldenapple);

        ItemStack applecheat = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 10);
        ItemMeta im4 = applecheat.getItemMeta();
        im4.setDisplayName(ChatColor.GOLD+"Prix : 64 lingots d'or et 32 émeraudes");
        applecheat.setItemMeta(im4);
        inv.setItem(16, applecheat);

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

        Location loc1 = new Location(world,62, -2, -5);
        Location loc2 = new Location(world, 3, -2, 61);
        Location loc3 = new Location(world, -64, -2, 3);
        Location loc4 = new Location(world, -5, -2, -64);

        Entity villager1 = world.spawnEntity(loc1, EntityType.VILLAGER);
        Entity villager2 = world.spawnEntity(loc2, EntityType.VILLAGER);
        Entity villager3 = world.spawnEntity(loc3, EntityType.VILLAGER);
        Entity villager4 = world.spawnEntity(loc4, EntityType.VILLAGER);

        villager1.setCustomName(ChatColor.GOLD+"Marchand de nourriture");
        villager1.setInvulnerable(true);
        villager1.setPersistent(true);
        villager1.setSilent(true);

        villager2.setCustomName(ChatColor.GOLD+"Marchand de nourriture");
        villager2.setInvulnerable(true);
        villager2.setPersistent(true);
        villager2.setSilent(true);

        villager3.setCustomName(ChatColor.GOLD+"Marchand de nourriture");
        villager3.setInvulnerable(true);
        villager3.setPersistent(true);
        villager3.setSilent(true);

        villager4.setCustomName(ChatColor.GOLD+"Marchand de nourriture");
        villager4.setInvulnerable(true);
        villager4.setPersistent(true);
        villager4.setSilent(true);

    }
}