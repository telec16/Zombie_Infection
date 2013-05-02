package fr.telec16.ZIF;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ChatManager {

	@SuppressWarnings("unused")
	private Zombie_infection plugin;
	public ChatManager(Zombie_infection plugin){

		this.plugin = plugin;
	}

	public static Boolean chatGame(String msg)
	{
		int length = PlayerManager.getAllPlayers().size();
		
		for(int i = 0; i<length; i++)
			Bukkit.getPlayer(PlayerManager.getAllPlayers().get(i)).sendMessage(ChatColor.DARK_GREEN+msg);
		
		return true;
	}
	
	public static Boolean chatWin(String msg)
	{
		int length = PlayerManager.getAllPlayers().size();
		
		for(int i = 0; i<length; i++)
			Bukkit.getPlayer(PlayerManager.getAllPlayers().get(i)).sendMessage(ChatColor.GREEN+msg);
		
		return true;
	}
	
	public static Boolean chatLose(String msg)
	{
		int length = PlayerManager.getAllPlayers().size();
		
		for(int i = 0; i<length; i++)
			Bukkit.getPlayer(PlayerManager.getAllPlayers().get(i)).sendMessage(ChatColor.DARK_RED+msg);
		
		return true;
	}
	
}
