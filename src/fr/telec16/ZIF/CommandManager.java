package fr.telec16.ZIF;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import fr.telec16.ZIF.PlayerManager;
import fr.telec16.ZIF.DropManager;
import fr.telec16.ZIF.ChatManager;

public class CommandManager  extends Zombie_infection{

	@SuppressWarnings("unused")
	private Zombie_infection plugin;
	
	public CommandManager(Zombie_infection plugin){

		this.plugin = plugin;
	}
	
	public static Boolean OnCmdJoin(String path, Player p)
	{
		//si il ne s'est pas enregistrer
		if(!PlayerManager.getAllPlayers().contains(path))
		{
			if(!PlayerManager.getGame())
			{
				p.setGameMode(GameMode.SURVIVAL);
				
				TpManager.TpSurvivor(p);
				
				PlayerManager.addToAllPlayers(path);
				PlayerManager.setPlayerSurvivor(path);

				ChatManager.chatGame(path+" a rejoind la partie");
				ChatManager.chatGame("Il y a "+PlayerManager.getSurvivor().size()+" survivant(s)");
			}
			else
			{
				p.sendMessage("Le jeu est déjà lancé...");
			}
		}
		return true;
	}
	
	public static Boolean OnCmdLeave(String path, Player p)
	{
		if(PlayerManager.getAllPlayers().contains(path))
		{
			ChatManager.chatLose(path+" a quitté la partie ...");
			
			PlayerManager.removeToAllPlayers(path);
			PlayerManager.removeToInfected(path);
			PlayerManager.removeToSurvivor(path);

			ChatManager.chatGame("Il reste "+PlayerManager.getSurvivor().size()+" survivant(s)...");
			
			if(PlayerManager.getSurvivor().size() == 0)
			{
				ChatManager.chatLose("Tout le monde à abandonné ...");
				PlayerManager.clearAll();
			}
		}
		else
		{
			p.sendMessage("Tu n'était pas inscrit...");
		}
		return true;
	}
	
	public static Boolean OnCmdLaunch(Player p)
	{
		if(p.isOp() && PlayerManager.getSurvivor().size() > 0)
		{
			String randomOne = PlayerManager.randomOne();
			
			TpManager.TpZombie(Bukkit.getPlayer(randomOne));
			
			PlayerManager.setPlayerInfected(randomOne);
			PlayerManager.setGame(true);

			ChatManager.chatGame("Les zombies attaquent, cachez vous !");
			ChatManager.chatGame(randomOne+" est désigné comme le Zombie de départ !");
		}
		else
		{
			p.sendMessage("Tu n'est pas Op...");
		}
		return true;
	}
	
	public static Boolean OnCmdPause(Player p)
	{
		if(p.isOp())
		{
			PlayerManager.setGame(false);
			ChatManager.chatLose("L'Infection continuras plus tard ...");
		}
		else
		{
			p.sendMessage("Tu n'est pas Op...");
		}
		return true;
	}
	
	public static Boolean OnCmdStop(Player p)
	{
		if(p.isOp())
		{
			ChatManager.chatLose("C'est la fin ...");
			
			//On supprime les traces
			PlayerManager.clearAll();
		}
		else
		{
			p.sendMessage("Tu n'est pas Op...");
		}
		return true;
	}

	public static Boolean OnCmdSetDrop(Player p, String Id, String Nb, String Damage) {

		if(p.isOp())
			DropManager.setDrop(Integer.parseInt(Id), Integer.parseInt(Nb), (short)Integer.parseInt(Damage));
		
		return true;
	}

	public static Boolean OnCmdSetDrop(Player p, String Id, String Nb) {
		
		if(p.isOp())
			DropManager.setDrop(Integer.parseInt(Id), Integer.parseInt(Nb));
		
		return true;
	}

	public static Boolean OnCmdEnableDrop(Player p, String b) {

		if(p.isOp())
			DropManager.enableDrop(Boolean.parseBoolean(b));
		
		return true;
	}

	public static boolean OnCmdSetSurvivorTp(Player p) {
		
		if(p.isOp())
			TpManager.setSurvivorTp(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());

		return true;
	}

	public static boolean OnCmdSetZombieTp(Player p) {
		
		if(p.isOp())
			TpManager.setZombieTp(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());

		return true;
	}

	public static boolean OnCmdEnableTping(Player p, String b) {

		if(p.isOp())
			TpManager.enableTping(Boolean.parseBoolean(b));
		return true;
	}

	public static boolean OnCmdSetLoseTp(Player p) {
		 
		if(p.isOp())
			TpManager.setZombieTp(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());

		return true;
		
	}

	public static boolean OnCmdEnableTpingLose(Player p, String b) {
		
		if(p.isOp())
			TpManager.enableTping(Boolean.parseBoolean(b));
		return true;
	}
	
}
