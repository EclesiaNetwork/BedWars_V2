package eu.eclesia_network.bedwars.module.modules.inventory.inventorys;

import eu.eclesia_network.bedwars.module.modules.inventory.SubInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class EquipeInventory extends SubInventory {

    @Override
    public void performS(Player p) {

        Inventory inventory = Bukkit.createInventory(p, InventoryType.CHEST, ChatColor.AQUA+"Choix Équipe");

        ItemStack greenwool = new ItemStack(Material.GREEN_WOOL,1);
        ItemMeta im1 = greenwool.getItemMeta();
        im1.setDisplayName(ChatColor.GREEN+"Équipe Verte");
        greenwool.setItemMeta(im1);
        inventory.setItem(10, greenwool);

        ItemStack redwool = new ItemStack(Material.RED_WOOL,1);
        ItemMeta im2 = redwool.getItemMeta();
        im2.setDisplayName(ChatColor.RED+"Équipe Rouge");
        redwool.setItemMeta(im2);
        inventory.setItem(12, redwool);

        ItemStack lightbluewool = new ItemStack(Material.LIGHT_BLUE_WOOL, 1);
        ItemMeta im3 = lightbluewool.getItemMeta();
        im3.setDisplayName(ChatColor.BLUE+"Équipe Bleu");
        lightbluewool.setItemMeta(im3);
        inventory.setItem(14, lightbluewool);

        ItemStack yellowwool = new ItemStack(Material.YELLOW_WOOL,1);
        ItemMeta im4 = yellowwool.getItemMeta();
        im4.setDisplayName(ChatColor.YELLOW+"Équipe Jaune");
        yellowwool.setItemMeta(im4);
        inventory.setItem(16, yellowwool);

        for(int i=0; i<inventory.getSize(); i++){
            ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
            ItemStack item2 = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
            ItemMeta im99 = item.getItemMeta();
            im99.setDisplayName(" "); im99.setLore(Collections.singletonList(" "));
            item.setItemMeta(im99); item2.setItemMeta(im99);
            if(inventory.getItem(i) != null) continue;
            if(i % 2 == 0) inventory.setItem(i, item2); else inventory.setItem(i, item);
        }
        p.openInventory(inventory);

    }

    @Override
    public void performM(Player p, Player target) {}

    @Override
    public void performU(World world) {}

}