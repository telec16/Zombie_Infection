package fr.telec16.ZIF;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DropManager {

	private static Zombie_infection plugin;
	@SuppressWarnings("static-access")
	public DropManager(Zombie_infection plugin){

		this.plugin = plugin;
	}
	
	public static Boolean drop(Player p)
	{
		FileConfiguration config = plugin.getConfig();
		ItemStack ItSt = config.getItemStack("Drop");
		Boolean enableDrop = config.getBoolean("EnableDrop");
		
		if(enableDrop)
			p.getInventory().addItem(new ItemStack[] { ItSt });
		
		return true;
	}
	
	public static Boolean setDrop(int Id, int Nb)
	{
		FileConfiguration config = plugin.getConfig();
		ItemStack ItSt = new ItemStack(Id, Nb);
		
		config.set("Drop", ItSt);
		plugin.saveConfig();
		
		return true;
	}
	
	public static Boolean setDrop(int Id, int Nb, short Damage)
	{
		FileConfiguration config = plugin.getConfig();
		ItemStack ItSt = new ItemStack(Id, Nb, Damage);
		
		config.set("Drop", ItSt);
		plugin.saveConfig();
		
		return true;
	}
	
	public static Boolean enableDrop(Boolean enableDrop)
	{
		FileConfiguration config = plugin.getConfig();
		
		config.set("EnableDrop", enableDrop);
		plugin.saveConfig();
		
		return true;
	}
	
	public static Boolean configDropExist()
	{
		FileConfiguration config = plugin.getConfig();
		
		return (config.get("Drop") != null && config.get("EnableDrop") != null);
	}
	
}
