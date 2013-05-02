package fr.telec16.ZIF;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerManager{
	
	@SuppressWarnings("unused")
	private static Zombie_infection plugin;
	@SuppressWarnings("static-access")
	public PlayerManager(Zombie_infection plugin){

		this.plugin = plugin;
	}
	
	
	/*InGame variables*/
	public static ArrayList<String> allPlayers = new ArrayList<String>();
	public static ArrayList<String> infected = new ArrayList<String>();
	public static ArrayList<String> survivor = new ArrayList<String>();
	public static Boolean Game = false;
	
	//Position en String
	public static String getPos(Location pos){
		
		return ("X:"+pos.getBlockX()+" Y:"+pos.getBlockY()+" Z:"+pos.getBlockZ());
	}
	
	//Nom formate en String
	public static String getPlayerName(String entityName){
		
		entityName = entityName.substring(0,entityName.length()-1);
		entityName = entityName.substring(entityName.lastIndexOf("=")+1);
		entityName = entityName.toLowerCase();
		return entityName;
	}
	
	//Player in infected
	public static void setPlayerInfected(String player)
	{
		if(TpManager.GetEnableTpingLose())
			Bukkit.getPlayer(player).setBedSpawnLocation(TpManager.GetSpawnLose(Bukkit.getPlayer(player)));
		else
			Bukkit.getPlayer(player).setBedSpawnLocation(TpManager.GetSpawnZombie(Bukkit.getPlayer(player)));
		
		PlayerInventory pInv = Bukkit.getPlayer(player).getInventory();
		pInv.setHelmet(new ItemStack(397,1,(short)2));
		
		survivor.remove(player);
		infected.add(player);
	}
	
	//Player in survivor
	public static void setPlayerSurvivor(String player)
	{
		if(TpManager.GetEnableTpingLose())
			Bukkit.getPlayer(player).setBedSpawnLocation(TpManager.GetSpawnLose(Bukkit.getPlayer(player)));
		else
			Bukkit.getPlayer(player).setBedSpawnLocation(TpManager.GetSpawnSurvivor(Bukkit.getPlayer(player)));
		
		PlayerInventory pInv = Bukkit.getPlayer(player).getInventory();
		pInv.setHelmet(new ItemStack(397,1,(short)3));
		
		survivor.add(player);
		infected.remove(player);
	}

	//Clear All
	public static void clearAll(){

		survivor.clear();
		infected.clear();
	    allPlayers.clear();
	    Game = false;
	}

	//Get random One
	public static String randomOne(){
		
		Random rd = new Random();
		int i = rd.nextInt(allPlayers.size());
		return allPlayers.get(i);
	}
	
	
	/*AllPlayers constructor*/
	public static void addToAllPlayers(String s){

	    allPlayers.add(s);
	}
	public static void removeToAllPlayers(String s){

	    allPlayers.remove(s);
	}
	public static ArrayList<String> getAllPlayers(){

	    return allPlayers;
	}
	
	/*Survivor constructor*/
	public static void addToSurvivor(String s){

		survivor.add(s);
	}
	public static void removeToSurvivor(String s){

		survivor.remove(s);
	}
	public static ArrayList<String> getSurvivor(){

	    return survivor;
	}
	
	/*Infected constructor*/
	public static void addToInfected(String s){

		infected.add(s);
	}
	public static void removeToInfected(String s){

		infected.remove(s);
	}
	public static ArrayList<String> getInfected(){

	    return infected;
	}	
	
	/*Game constructor*/
	public static void setGame(Boolean b){

		Game = b;
	}
	public static Boolean getGame(){

	    return Game;
	}	
}
