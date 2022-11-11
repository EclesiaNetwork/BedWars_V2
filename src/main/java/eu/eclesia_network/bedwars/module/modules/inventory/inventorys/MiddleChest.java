package eu.eclesia_network.bedwars.module.modules.inventory.inventorys;

import eu.eclesia_network.bedwars.module.modules.inventory.SubInventory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MiddleChest extends SubInventory {

    @Override
    public void performS(Player p) {}

    @Override
    public void performM(Player p, Player target) {}

    @Override
    public void performU(World world) {
        Chest chest = (Chest) world.getBlockAt(-1, -2, -1).getState();
        chest.setCustomName(ChatColor.AQUA+"Coffre du milieu");

        ItemStack bow = new ItemStack(Material.BOW, 1);
        ItemMeta im1 = bow.getItemMeta();
        im1.setDisplayName(ChatColor.GOLD+"Arc enchanté");
        im1.addEnchant(Enchantment.ARROW_FIRE, 1, false);
        im1.addEnchant(Enchantment.DAMAGE_ALL, 2, false);
        im1.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
        im1.addEnchant(Enchantment.DURABILITY, 3, false);
        im1.addEnchant(Enchantment.ARROW_DAMAGE, 2, false);
        im1.addEnchant(Enchantment.ARROW_KNOCKBACK, 2, false);
        im1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bow.setItemMeta(im1);
        chest.getSnapshotInventory().setItem(10, bow);

        ItemStack arrow = new ItemStack(Material.ARROW, 1);
        ItemMeta im2 = arrow.getItemMeta();
        im2.setDisplayName(ChatColor.GOLD+"Flèche enchantée");
        im2.addEnchant(Enchantment.MULTISHOT, 1, false);
        arrow.setItemMeta(im2);
        chest.getSnapshotInventory().setItem(11, arrow);

        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta im3 = sword.getItemMeta();
        im3.setDisplayName(ChatColor.GOLD+"Épée enchantée");
        im3.addEnchant(Enchantment.KNOCKBACK, 2, false);
        im3.addEnchant(Enchantment.DAMAGE_ALL, 5, false);
        im3.addEnchant(Enchantment.DURABILITY, 3, false);
        im3.addEnchant(Enchantment.PIERCING, 3, false);
        im3.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        sword.setItemMeta(im3);
        chest.getSnapshotInventory().setItem(12, sword);

        ItemStack applecheat = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 32);
        chest.getSnapshotInventory().setItem(13, applecheat);

        chest.update(true);
    }

}
