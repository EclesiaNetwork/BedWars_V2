package eu.eclesia_network.bedwars.module.modules.inventory;

import eu.eclesia_network.bedwars.BedWars;
import eu.eclesia_network.bedwars.module.Module;
import eu.eclesia_network.bedwars.module.ModuleType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;

public class InventoryHandler extends Module {

	public static ArrayList<String> redMember = new ArrayList<>();
	public static ArrayList<String> blueMember = new ArrayList<>();
	public static ArrayList<String> yellowMember = new ArrayList<>();
	public static ArrayList<String> greenMember = new ArrayList<>();

	public InventoryHandler(BedWars plugin) { super(plugin, ModuleType.INVENTORY_HANDLER); }

	@Override
	public void onEnable() {



	}

	@Override
	public void onDisable() {

	}

	@EventHandler
	public void onMenuClick(InventoryClickEvent e){

		Player p = (Player) e.getWhoClicked();
		ItemStack current = e.getCurrentItem();
		ScoreboardManager scoreboardManager = getPlugin().getServer().getScoreboardManager();

		if (e.getView().getTitle().equalsIgnoreCase(ChatColor.AQUA+"Choix Équipe")){
			e.setCancelled(true);
			if (current == null || current.getType() == Material.GREEN_STAINED_GLASS_PANE || current.getType() == Material.GRAY_STAINED_GLASS_PANE){
				return;
			}else{
				if (current.getType() == Material.RED_WOOL) {
					p.closeInventory();
					scoreboardManager.getMainScoreboard().getTeam("Rouge").addPlayer(p);
					redMember.add(p.getName());
				}
				if (current.getType() == Material.GREEN_WOOL){
					p.closeInventory();
					scoreboardManager.getMainScoreboard().getTeam("Vert").addPlayer(p);
					greenMember.add(p.getName());
				}
				if (current.getType() == Material.LIGHT_BLUE_WOOL){
					p.closeInventory();
					scoreboardManager.getMainScoreboard().getTeam("Bleu").addPlayer(p);
					blueMember.add(p.getName());
				}
				if (current.getType() == Material.YELLOW_WOOL){
					p.closeInventory();
					scoreboardManager.getMainScoreboard().getTeam("Jaune").addPlayer(p);
					yellowMember.add(p.getName());
				}
			}
		}

		if (e.getView().getTitle().equalsIgnoreCase(ChatColor.AQUA+"Marchand de blocs")){
			e.setCancelled(true);
			if (current == null || current.getType() == Material.GREEN_STAINED_GLASS_PANE || current.getType() == Material.GRAY_STAINED_GLASS_PANE){
				return;
			}else{
				if (current.getType() == Material.STONE){

					if (p.getInventory().contains(Material.BRICK, 128)){
						ItemStack brick = new ItemStack(Material.BRICK, 128);
						ItemStack stone = new ItemStack(Material.STONE, 64);
						p.getInventory().addItem(stone);
						p.getInventory().removeItem(brick);
						p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter un stack de roche");
						p.closeInventory();
					}else {
						p.closeInventory();
						p.sendMessage(ChatColor.RED+"Vous devez avoir au minimum 128 briques pour acheter ces blocs");
					}
				}if (current.getType() == Material.CUT_SANDSTONE){
					if (p.getInventory().contains(Material.BRICK, 75)){
						ItemStack brick = new ItemStack(Material.BRICK, 75);
						ItemStack sandstone = new ItemStack(Material.CUT_SANDSTONE, 64);
						p.getInventory().addItem(sandstone);
						p.getInventory().removeItem(brick);
						p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter un stack de grès");
						p.closeInventory();
					}else {
						p.closeInventory();
						p.sendMessage(ChatColor.RED+"Vous devez avoir au minimum 75 briques pour acheter ces blocs");
					}
				}if (current.getType() == Material.ACACIA_WOOD){
					if (p.getInventory().contains(Material.BRICK, 50)){
						ItemStack brick = new ItemStack(Material.BRICK, 50);
						ItemStack wood = new ItemStack(Material.ACACIA_WOOD, 64);
						p.getInventory().addItem(wood);
						p.getInventory().removeItem(brick);
						p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter un stack de bois");
						p.closeInventory();
					}else {
						p.closeInventory();
						p.sendMessage(ChatColor.RED+"Vous devez avoir au minimum 50 briques pour acheter ces blocs");
					}
				}
				if (current.getType() == Material.GLASS) {
					if (p.getInventory().contains(Material.IRON_INGOT, 20)){
						ItemStack iron = new ItemStack(Material.IRON_INGOT, 20);
						ItemStack glass = new ItemStack(Material.GLASS, 64);
						p.getInventory().addItem(glass);
						p.getInventory().removeItem(iron);
						p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter un stack de bloc en verre");
						p.closeInventory();
					}else{
						p.closeInventory();
						p.sendMessage(ChatColor.RED+"Vous devez avoir au minimum 20 lingots de fer pour acheter ces blocs");
					}
				}if (current.getType() == Material.RED_TERRACOTTA){
					if (p.getInventory().contains(Material.BRICK, 128)){
						ItemStack brick = new ItemStack(Material.BRICK, 128);
						ItemStack redterracota = new ItemStack(Material.RED_TERRACOTTA, 64);
						p.getInventory().addItem(redterracota);
						p.getInventory().removeItem(brick);
						p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter un stack de terre rouge");
						p.closeInventory();
					}else {
						p.closeInventory();
						p.sendMessage(ChatColor.RED+"Vous devez avoir au minimum 128 briques pour acheter ces blocs");
					}
				}if (current.getType() == Material.BLUE_TERRACOTTA){
					if (p.getInventory().contains(Material.BRICK, 128)){
						ItemStack brick = new ItemStack(Material.BRICK, 128);
						ItemStack blueterracota = new ItemStack(Material.BLUE_TERRACOTTA, 64);
						p.getInventory().addItem(blueterracota);
						p.getInventory().removeItem(brick);
						p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter un stack de terre bleu");
						p.closeInventory();
					}else {
						p.closeInventory();
						p.sendMessage(ChatColor.RED+"Vous devez avoir au minimum 128 briques pour acheter ces blocs");
					}
				}if (current.getType() == Material.YELLOW_TERRACOTTA){
					if (p.getInventory().contains(Material.BRICK, 128)){
						ItemStack brick = new ItemStack(Material.BRICK, 128);
						ItemStack yellowterracota = new ItemStack(Material.YELLOW_TERRACOTTA, 64);
						p.getInventory().addItem(yellowterracota);
						p.getInventory().removeItem(brick);
						p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter un stack de terre jaune");
						p.closeInventory();
					}else {
						p.closeInventory();
						p.sendMessage(ChatColor.RED+"Vous devez avoir au minimum 128 briques pour acheter ces blocs");
					}
				}if (current.getType() == Material.GREEN_TERRACOTTA){
					if (p.getInventory().contains(Material.BRICK, 128)){
						ItemStack brick = new ItemStack(Material.BRICK, 128);
						ItemStack greenterracota = new ItemStack(Material.GREEN_TERRACOTTA, 64);
						p.getInventory().addItem(greenterracota);
						p.getInventory().removeItem(brick);
						p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter un stack de terre verte");
						p.closeInventory();
					}else {
						p.closeInventory();
						p.sendMessage(ChatColor.RED+"Vous devez avoir au minimum 128 briques pour acheter ces blocs");
					}
				}
			}
		}
		if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED+"Marchand d'armes")){
			e.setCancelled(true);
			if (current == null || current.getType() == Material.GREEN_STAINED_GLASS_PANE || current.getType() == Material.GRAY_STAINED_GLASS_PANE){
				return;
			}else{

				if (current.getType() == Material.IRON_SWORD){
					if (p.getInventory().contains(Material.IRON_INGOT, 30)){
						ItemStack iron = new ItemStack(Material.IRON_INGOT, 30);
						ItemStack ironsword = new ItemStack(Material.IRON_SWORD,1);
						p.getInventory().addItem(ironsword);
						p.getInventory().removeItem(iron);
						p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter une épée en fer");
						p.closeInventory();
					}else{
						p.closeInventory();
						p.sendMessage(ChatColor.RED+"Vous devez avoir au minimum 30 lingots de fer pour acheter cette arme");
					}
				}if (current.getType() == Material.GOLDEN_SWORD){
					if (p.getInventory().contains(Material.GOLD_INGOT, 30) && p.getInventory().contains(Material.EMERALD, 2)){
						ItemStack emerald = new ItemStack(Material.EMERALD, 2);
						ItemStack goldingot = new ItemStack(Material.GOLD_INGOT, 30);
						ItemStack goldensword = new ItemStack(Material.GOLDEN_SWORD, 1);
						p.getInventory().addItem(goldensword);
						p.getInventory().removeItem(goldingot);
						p.getInventory().removeItem(emerald);
						p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter une épée en or");
						p.closeInventory();
					}else{
						p.closeInventory();
						p.sendMessage(ChatColor.RED+"Vous devez avoir au minimum 30 lingots d'or et 2 émeraudes pour acheter cette arme");
					}
				}if (current.getType() == Material.DIAMOND_SWORD){
					if (p.getInventory().contains(Material.EMERALD, 15)){
						ItemStack emerald = new ItemStack(Material.EMERALD, 15);
						ItemStack diamondsword = new ItemStack(Material.DIAMOND_SWORD, 1);
						p.getInventory().addItem(diamondsword);
						p.getInventory().removeItem(emerald);
						p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter une épée en Diamand");
						p.closeInventory();
					}else{
						p.closeInventory();
						p.sendMessage(ChatColor.RED+"Vous devez avoir au minimum 15 Émeraudes pour acheter cette arme");
					}
				}if (current.getType() == Material.BOW){
					if (p.getInventory().contains(Material.IRON_INGOT, 20) && p.getInventory().contains(Material.GOLD_INGOT, 10)){
						ItemStack iron = new ItemStack(Material.IRON_INGOT, 20);
						ItemStack gold = new ItemStack(Material.GOLD_INGOT, 10);
						ItemStack bow = new ItemStack(Material.BOW, 1);
						p.getInventory().addItem(bow);
						p.getInventory().removeItem(iron);
						p.getInventory().removeItem(gold);
						p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter un arc");
						p.closeInventory();
					}else{
						p.closeInventory();
						p.sendMessage(ChatColor.RED+"Vous devez avoir au minimum 20 lingots de fer et 10 lingots d'or pour acheter cette arme");
					}
				}if (current.getType() == Material.ARROW){
					if (p.getInventory().contains(Material.GOLD_INGOT, 10) && p.getInventory().contains(Material.EMERALD, 1)){
						ItemStack gold = new ItemStack(Material.GOLD_INGOT, 10);
						ItemStack emerald = new ItemStack(Material.EMERALD, 1);
						ItemStack arrow = new ItemStack(Material.ARROW, 32);
						p.getInventory().addItem(arrow);
						p.getInventory().removeItem(gold);
						p.getInventory().removeItem(emerald);
						p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter 32 flèches");
						p.closeInventory();
					}else{
						p.closeInventory();
						p.sendMessage(ChatColor.RED+"Vous devez avoir au minimum 10 lingots d'or et 1 émeraude pour acheter cette arme");
					}
				}

			}
		}if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD+"Marchand de nourriture")) {
			e.setCancelled(true);
			if (current == null || current.getType() == Material.GREEN_STAINED_GLASS_PANE || current.getType() == Material.GRAY_STAINED_GLASS_PANE) {
				return;
			} else {
				if (current.getType() == Material.APPLE) {
					if (p.getInventory().contains(Material.GOLD_INGOT, 25)) {
						ItemStack gold = new ItemStack(Material.GOLD_INGOT, 25);
						ItemStack apple = new ItemStack(Material.APPLE, 32);
						p.getInventory().addItem(apple);
						p.getInventory().removeItem(gold);
						p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter 32 pommes");
						p.closeInventory();
					}else{
						p.closeInventory();
						p.sendMessage(ChatColor.RED+"Vous devez avoir au minimum 25 lingots d'or pour acheter cette nourriture");
					}
				}if (current.getType() == Material.COOKED_BEEF){
					if (p.getInventory().contains(Material.GOLD_INGOT, 30)){
						ItemStack gold = new ItemStack(Material.GOLD_INGOT, 30);
						ItemStack steak = new ItemStack(Material.COOKED_BEEF, 32);
						p.getInventory().addItem(steak);
						p.getInventory().removeItem(gold);
						p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter 32 steaks");
						p.closeInventory();
					}else{
						p.closeInventory();
						p.sendMessage(ChatColor.RED+"Vous devez avoir au minimum 30 lingots d'or pour acheter cette nourriture");
					}
				}if (current.getType() == Material.GOLDEN_APPLE){
					if (p.getInventory().contains(Material.GOLD_INGOT, 30) && p.getInventory().contains(Material.EMERALD, 10)){
						ItemStack gold = new ItemStack(Material.GOLD_INGOT, 30);
						ItemStack emerald = new ItemStack(Material.EMERALD, 10);
						ItemStack goldenapple = new ItemStack(Material.GOLDEN_APPLE, 10);
						p.getInventory().addItem(goldenapple);
						p.getInventory().removeItem(gold);
						p.getInventory().removeItem(emerald);
						p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter 10 pommes dorées");
						p.closeInventory();
					}else{
						p.closeInventory();
						p.sendMessage(ChatColor.RED+"Vous devez avoir au minimum 30 lingots d'or et 10 émeraudes pour acheter cette nourriture");
					}
				}if (current.getType() == Material.ENCHANTED_GOLDEN_APPLE){
					if (p.getInventory().contains(Material.GOLD_INGOT, 64) && p.getInventory().contains(Material.EMERALD, 32)){
						ItemStack gold = new ItemStack(Material.GOLD_INGOT, 64);
						ItemStack emerald = new ItemStack(Material.EMERALD, 32);
						ItemStack applecheat = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 10);
						p.getInventory().addItem(applecheat);
						p.getInventory().removeItem(gold);
						p.getInventory().removeItem(emerald);
						p.sendMessage(ChatColor.GREEN+"Vous venez d'acheter 10 pommes cheat");
						p.closeInventory();
					}else{
						p.closeInventory();
						p.sendMessage(ChatColor.RED+"Vous devez avoir au minimum 64 lingots d'or et 32 émeraudes pour acheter cette nourriture");
					}
				}
			}
		}if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE+"Marchand de stuff")) {
			e.setCancelled(true);
			if (current == null || current.getType() == Material.GREEN_STAINED_GLASS_PANE || current.getType() == Material.GRAY_STAINED_GLASS_PANE) {
				return;
			} else {
				if (current.getType() == Material.DIAMOND_AXE) {
					if (p.getInventory().contains(Material.GOLD_INGOT, 25) && p.getInventory().contains(Material.EMERALD, 5)) {
						ItemStack gold = new ItemStack(Material.GOLD_INGOT, 25);
						ItemStack emerald = new ItemStack(Material.EMERALD, 5);
						ItemStack diamondaxe = new ItemStack(Material.DIAMOND_AXE, 1);
						p.getInventory().addItem(diamondaxe);
						p.getInventory().removeItem(emerald);
						p.getInventory().removeItem(gold);
						p.sendMessage(ChatColor.GREEN + "Vous venez d'acheter une hache en diamand");
						p.closeInventory();
					} else {
						p.closeInventory();
						p.sendMessage(ChatColor.RED + "Vous devez avoir au minimum 25 lingots d'or et 5 émeraude pour acheter cet outil");
					}
				}
				if (current.getType() == Material.DIAMOND_PICKAXE) {
					if (p.getInventory().contains(Material.GOLD_INGOT, 25) && p.getInventory().contains(Material.EMERALD, 5)) {
						ItemStack gold = new ItemStack(Material.GOLD_INGOT, 25);
						ItemStack emerald = new ItemStack(Material.EMERALD, 5);
						ItemStack diamondpickaxe = new ItemStack(Material.DIAMOND_PICKAXE, 1);
						p.getInventory().addItem(diamondpickaxe);
						p.getInventory().removeItem(emerald);
						p.getInventory().removeItem(gold);
						p.sendMessage(ChatColor.GREEN + "Vous venez d'acheter une pioche en diamand");
						p.closeInventory();
					} else {
						p.closeInventory();
						p.sendMessage(ChatColor.RED + "Vous devez avoir au minimum 25 lingots d'or et 5 émeraude pour acheter cet outil");
					}

				}
				if (current.getType() == Material.FLINT_AND_STEEL) {
					if (p.getInventory().contains(Material.EMERALD, 15)) {
						ItemStack emerald = new ItemStack(Material.EMERALD, 15);
						ItemStack flint = new ItemStack(Material.FLINT_AND_STEEL, 1);
						p.getInventory().addItem(flint);
						p.getInventory().removeItem(emerald);
						p.sendMessage(ChatColor.GREEN + "Vous venez d'acheter un briquet");
						p.closeInventory();
					} else {
						p.closeInventory();
						p.sendMessage(ChatColor.RED + "Vous devez avoir au moins 15 émeraudes pour acheter cet outil");
					}
				}
				if (current.getType() == Material.TNT) {
					if (p.getInventory().contains(Material.GOLD_INGOT, 64) && p.getInventory().contains(Material.EMERALD, 64)) {
						ItemStack gold = new ItemStack(Material.GOLD_INGOT, 64);
						ItemStack emerald = new ItemStack(Material.EMERALD, 64);
						ItemStack tnt = new ItemStack(Material.TNT, 10);
						p.getInventory().addItem(tnt);
						p.getInventory().removeItem(gold);
						p.getInventory().removeItem(emerald);
						p.sendMessage(ChatColor.GREEN + "Vous venez d'acheter 10 tnt");
						p.closeInventory();
					} else {
						p.closeInventory();
						p.sendMessage(ChatColor.RED + "Vous devez avoir au moins 64 lingots d'or et 64 émeraudes pour acheter cet outil");
					}
				}
				if (current.getType() == Material.DIAMOND_HELMET) {
					if (p.getInventory().contains(Material.EMERALD, 20)) {
						ItemStack emerald = new ItemStack(Material.EMERALD, 25);
						ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET, 1);
						p.getInventory().addItem(helmet);
						p.getInventory().removeItem(emerald);
						p.sendMessage(ChatColor.GREEN + "Vous venez d'acheter un casque en diamand");
						p.closeInventory();
					} else {
						p.closeInventory();
						p.sendMessage(ChatColor.RED + "Vous devez avoir au moins 20 émeraudes pour acheter cet outil");
					}
				}
				if (current.getType() == Material.DIAMOND_CHESTPLATE) {
					if (p.getInventory().contains(Material.EMERALD, 20)) {
						ItemStack emerald = new ItemStack(Material.EMERALD, 25);
						ItemStack chesplate = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
						p.getInventory().addItem(chesplate);
						p.getInventory().removeItem(emerald);
						p.sendMessage(ChatColor.GREEN + "Vous venez d'acheter un plastron en diamand");
						p.closeInventory();
					} else {
						p.closeInventory();
						p.sendMessage(ChatColor.RED + "Vous devez avoir au moins 20 émeraudes pour acheter cet outil");
					}
				}
				if (current.getType() == Material.DIAMOND_LEGGINGS) {
					if (p.getInventory().contains(Material.EMERALD, 20)) {
						ItemStack emerald = new ItemStack(Material.EMERALD, 25);
						ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
						p.getInventory().addItem(leggings);
						p.getInventory().removeItem(emerald);
						p.sendMessage(ChatColor.GREEN + "Vous venez d'acheter un pentalon en diamand");
						p.closeInventory();
					} else {
						p.closeInventory();
						p.sendMessage(ChatColor.RED + "Vous devez avoir au moins 20 émeraudes pour acheter cet outil");
					}
				}
				if (current.getType() == Material.DIAMOND_BOOTS) {
					if (p.getInventory().contains(Material.EMERALD, 20)) {
						ItemStack emerald = new ItemStack(Material.EMERALD, 25);
						ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS, 1);
						p.getInventory().addItem(boots);
						p.getInventory().removeItem(emerald);
						p.sendMessage(ChatColor.GREEN + "Vous venez d'acheter des chaussures en diamand");
						p.closeInventory();
					} else {
						p.closeInventory();
						p.sendMessage(ChatColor.RED + "Vous devez avoir au moins 20 émeraudes pour acheter cet outil");
					}
				}
			}
		}
	}







}