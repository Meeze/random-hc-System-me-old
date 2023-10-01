package de.pooky.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import de.pooky.main.Main;

public class Eat_Command implements CommandExecutor{

	String prefix = "§5§lAwakeMC§8§l » ";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player)sender;
		
		if(!(sender instanceof Player)) {
			sender.sendMessage("Diesen Befehl darfst du nur als Spieler ausführen!");
			return true;
		}
		if(!(sender.hasPermission("eat.use"))) {
			return true;
		}
		if(args.length >= 1) {
			ActionBarAPI.sendActionBar(p, "§7Verwende §6/eat");
			return true;
		}
		
		if(sender instanceof Player) {
			p.setFoodLevel(30);
			ActionBarAPI.sendActionBar(p, "§7Dein Hunger wurde gestillt.");
			return true;
		}
		return false;
		
	}

}
