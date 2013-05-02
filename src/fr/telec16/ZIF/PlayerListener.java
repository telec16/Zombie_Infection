package fr.telec16.ZIF;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.telec16.ZIF.PlayerManager;
import fr.telec16.ZIF.DropManager;
import fr.telec16.ZIF.ChatManager;

public class PlayerListener implements Listener {
	
	@SuppressWarnings("unused")
	private Zombie_infection plugin;
	
	public PlayerListener(Zombie_infection plugin){
		
		this.plugin = plugin;
	}
	
	//Tapage des entites
	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityGetHurt(EntityDamageByEntityEvent e){
		
		String preneur;
		String doneur;
		Boolean preneurOk = false;
		Boolean doneurOk = false;

		if(PlayerManager.getGame() == true)
		{
			if(e.getEntityType() == EntityType.PLAYER && (e.getDamager().getType() == EntityType.ZOMBIE || e.getDamager().getType() == EntityType.PLAYER))
			{
				//Preneur existe ?
				preneur = PlayerManager.getPlayerName(e.getEntity().toString());
				if(PlayerManager.getAllPlayers().contains(preneur) == true)
				{
					preneurOk = true;
				}
	
				//Doneur existe ?
				if(e.getDamager().getType() == EntityType.PLAYER)
				{
					doneur = PlayerManager.getPlayerName(e.getDamager().toString());
					if(PlayerManager.getAllPlayers().contains(doneur) == true)
					{
						doneurOk = true;
					}
				}
				else
				{
					doneur = "Zombie";
					doneurOk = true;
				}
	
				//Si ils extistent
				if(preneurOk && doneurOk)
				{
					//Si le donneur est un zombie
					if(PlayerManager.getInfected().contains(doneur) == true || doneur.equals("Zombie"))
					{
						//aucun dommage
						e.setDamage(0);
						
						//Si le preneur n'est pas un zombie
						if(PlayerManager.getInfected().contains(preneur) == false)
						{
							ChatManager.chatLose(preneur+" devient un zombie en "+PlayerManager.getPos(e.getEntity().getLocation())+", méchant "+doneur);
							PlayerManager.setPlayerInfected(preneur);
							
							if(PlayerManager.getSurvivor().size() == 1)
							{
								ChatManager.chatWin("Fin du jeu !");
								ChatManager.chatWin("Le gagnant : "+PlayerManager.getSurvivor().get(0));
								DropManager.drop(Bukkit.getPlayer(PlayerManager.getSurvivor().get(0)));
								PlayerManager.clearAll();
							}
							else
							{
								ChatManager.chatGame("Il reste "+PlayerManager.getSurvivor().size()+" survivants...");
							}
						}
						//Sinon on le dit
						else
						{
							ChatManager.chatGame("J'entend des Zombies en : "+PlayerManager.getPos(e.getEntity().getLocation())+" ...");
						}
					}
				}
			}
		}
	}
	
	//Quand meurt
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerDeath(PlayerDeathEvent e){

		if(TpManager.GetEnableTpingLose())
		{
			ChatManager.chatLose(e.getEntity().getName()+" quitte le jeu ...");

			PlayerManager.removeToAllPlayers(e.getEntity().getName());
			PlayerManager.removeToInfected(e.getEntity().getName());
			PlayerManager.removeToSurvivor(e.getEntity().getName());

			ChatManager.chatGame("Il reste "+PlayerManager.getSurvivor().size()+" survivant(s)...");
			
			if(PlayerManager.getSurvivor().size() == 0)
			{
				ChatManager.chatLose("Tout le monde à abandonné ...");
				PlayerManager.clearAll();
			}
		}
	}
}