package fr.telec16.ZIF;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.telec16.ZIF.DropManager;
import fr.telec16.ZIF.TpManager;

public class Zombie_infection extends JavaPlugin{
	
	@Override
	public void onEnable(){
		
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new PlayerListener(this), this);
		pm.registerEvents(new CommandListener(this), this);
		
		new CommandManager(this);
		new PlayerManager(this);
		new DropManager(this);
		new ChatManager(this);
		new TpManager(this);
		
		if(!DropManager.configDropExist())
		{
			DropManager.enableDrop(true);
			DropManager.setDrop(397, 5, (short)2);
		}
		
		if(!TpManager.configTpExist())
		{
			TpManager.enableTping(false);
			TpManager.enableTpingLose(false);
			TpManager.setZombieTp(-10, 64, 0);
			TpManager.setSurvivorTp(0, 64, 0);
			TpManager.setLoseTp(0, 128, 0);
		}
	}

	@Override
	public void onDisable() {
		
	}
}