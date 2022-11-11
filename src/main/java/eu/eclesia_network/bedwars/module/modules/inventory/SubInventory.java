package eu.eclesia_network.bedwars.module.modules.inventory;

import org.bukkit.World;
import org.bukkit.entity.Player;

public abstract class SubInventory {

	public abstract void performS(Player p);
	public abstract void performM(Player p, Player target);

	public abstract void performU(World world);

}
