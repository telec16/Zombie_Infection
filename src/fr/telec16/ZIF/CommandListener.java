package fr.telec16.ZIF;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import fr.telec16.ZIF.PlayerManager;
import fr.telec16.ZIF.CommandManager;

public class CommandListener implements Listener{
	
	@SuppressWarnings("unused")
	private Zombie_infection plugin;
	
	public CommandListener(Zombie_infection plugin){

		this.plugin = plugin;
	}
	
	//Commandes des joueurs
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e){

		String path = PlayerManager.getPlayerName(e.getPlayer().toString());
		Player p = e.getPlayer();

		//Commande + args
		String message = e.getMessage();
		String[] args = message.split(" ");

		if(args[0].equalsIgnoreCase("/ZiJoin"))
		{
			e.setCancelled(true);
			
			CommandManager.OnCmdJoin(path, p);
	    }
		
		if(args[0].equalsIgnoreCase("/ZiLeave"))
		{
			e.setCancelled(true);
			
			CommandManager.OnCmdLeave(path, p);
		}
		
		if(args[0].equalsIgnoreCase("/ZiLaunch"))
		{
			e.setCancelled(true);
			
			CommandManager.OnCmdLaunch(p);
		}
		
		if(args[0].equalsIgnoreCase("/ZiPause"))
		{
			e.setCancelled(true);
			
			CommandManager.OnCmdPause(p);
		}

		if(args[0].equalsIgnoreCase("/ZiStop"))
		{
			e.setCancelled(true);
			
			CommandManager.OnCmdStop(p);
		}

		if(args[0].equalsIgnoreCase("/ZiSetDrop"))
		{
			e.setCancelled(true);
			if(args.length == 4)
				CommandManager.OnCmdSetDrop(p, args[1], args[2], args[3]);
			if(args.length == 3)
				CommandManager.OnCmdSetDrop(p, args[1], args[2]);
		}
		
		if(args[0].equalsIgnoreCase("/ZiEnableDrop"))
		{
			e.setCancelled(true);
			if(args.length == 2)
				CommandManager.OnCmdEnableDrop(p, args[1]);
		}
		
		if(args[0].equalsIgnoreCase("/ZiSetSurvivorTp"))
		{
			e.setCancelled(true);
			
			CommandManager.OnCmdSetSurvivorTp(p);
		}
		
		if(args[0].equalsIgnoreCase("/ZiSetZombieTp"))
		{
			e.setCancelled(true);
			
			CommandManager.OnCmdSetZombieTp(p);
		}
		
		if(args[0].equalsIgnoreCase("/ZiEnableTping"))
		{
			e.setCancelled(true);

			if(args.length == 2)
				CommandManager.OnCmdEnableTping(p, args[1]);
		}
		
		if(args[0].equalsIgnoreCase("/ZiSetDeathTp"))
		{
			e.setCancelled(true);
			
			CommandManager.OnCmdSetLoseTp(p);
		}
		
		if(args[0].equalsIgnoreCase("/ZiEnableTpingOnDeath"))
		{
			e.setCancelled(true);

			if(args.length == 2)
				CommandManager.OnCmdEnableTpingLose(p, args[1]);
		}
	}
}
