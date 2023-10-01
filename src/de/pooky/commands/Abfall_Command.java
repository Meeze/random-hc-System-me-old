package de.pooky.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Abfall_Command implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		
		Player p = (Player)sender;
		
		if(sender instanceof Player) {
			Inventory abfall = Bukkit.createInventory(null, 9*2, "§c§lAbfall");
			
			p.openInventory(abfall);
			
			
		}else {
			sender.sendMessage("Diesen Befehl darfst du nur als Spieler ausführen!");
		}
		
		return false;
	}

	
	
}
