package fr.telec16.ZIF;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class TpManager {

	private static Zombie_infection plugin;
	@SuppressWarnings("static-access")
	public TpManager(Zombie_infection plugin){

		this.plugin = plugin;
	}
	
	public static Boolean enableTping(Boolean b)
	{
		FileConfiguration config = plugin.getConfig();

		config.set("EnableTping", b);
		plugin.saveConfig();
		
		return true;
	}
	
	public static Boolean enableTpingLose(Boolean b)
	{
		FileConfiguration config = plugin.getConfig();

		config.set("EnableTpingDeath", b);
		plugin.saveConfig();
		
		return true;
	}
	
	
	/*Set the teleport point*/
	public static Boolean setZombieTp(int x, int y, int z)
	{
		FileConfiguration config = plugin.getConfig();

		config.set("Zombie.X", x);
		config.set("Zombie.Y", y);
		config.set("Zombie.Z", z);
		plugin.saveConfig();
		
		return true;
	}
	public static Boolean setSurvivorTp(int x, int y, int z)
	{
		FileConfiguration config = plugin.getConfig();

		config.set("Survivor.X", x);
		config.set("Survivor.Y", y);
		config.set("Survivor.Z", z);
		plugin.saveConfig();
		
		return true;
	}
	public static Boolean setLoseTp(int x, int y, int z)
	{
		FileConfiguration config = plugin.getConfig();

		config.set("Lose.X", x);
		config.set("Lose.Y", y);
		config.set("Lose.Z", z);
		plugin.saveConfig();
		
		return true;
	}
	
	
	/*Teleport the player*/
	public static Boolean TpZombie(Player p)
	{
		FileConfiguration config = plugin.getConfig();
		int x = config.getInt("Zombie.X");
		int y = config.getInt("Zombie.Y");
		int z = config.getInt("Zombie.Z");
		Boolean statment = false;
		
		if(config.getBoolean("EnableTping"))
			statment = p.teleport(new Location(p.getWorld(), x, y, z));
		
		return statment;
	}
	public static Boolean TpSurvivor(Player p)
	{
		FileConfiguration config = plugin.getConfig();
		int x = config.getInt("Survivor.X");
		int y = config.getInt("Survivor.Y");
		int z = config.getInt("Survivor.Z");
		Boolean statment = false;
		
		if(config.getBoolean("EnableTping"))
			statment = p.teleport(new Location(p.getWorld(), x, y, z));
		
		return statment;
	}
	
	
	/*Spawn Joueur*/
	public static Location GetSpawnLose(Player p)
	{
		FileConfiguration config = plugin.getConfig();
		int x = config.getInt("Lose.X");
		int y = config.getInt("Lose.Y");
		int z = config.getInt("Lose.Z");
		
		return new Location(p.getWorld(), x, y, z);
	}
	public static Location GetSpawnZombie(Player p)
	{
		FileConfiguration config = plugin.getConfig();
		int x = config.getInt("Zombie.X");
		int y = config.getInt("Zombie.Y");
		int z = config.getInt("Zombie.Z");
		
		return new Location(p.getWorld(), x, y, z);
	}
	public static Location GetSpawnSurvivor(Player p)
	{
		FileConfiguration config = plugin.getConfig();
		int x = config.getInt("Survivor.X");
		int y = config.getInt("Survivor.Y");
		int z = config.getInt("Survivor.Z");
		
		return new Location(p.getWorld(), x, y, z);
	}
	
	/*Get enable*/
	public static boolean GetEnableTpingLose()
	{
		FileConfiguration config = plugin.getConfig();
		
		return config.getBoolean("EnableTpingDeath");
	}
	
	
	/*Test the configFile*/
	public static Boolean configTpExist()
	{
		FileConfiguration config = plugin.getConfig();
		
		return ((config.get("EnableTping") != null) && (config.get("EnableTpingDeath") != null) && (config.get("Lose") != null)  && (config.get("Zombie") != null) && (config.get("Survivor") != null));
	}
}
