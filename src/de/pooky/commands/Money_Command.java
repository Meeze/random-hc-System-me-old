package de.pooky.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.pooky.MySQL.Coin_API;
import de.pooky.MySQL.MySQL;

public class Money_Command implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player p = (Player)sender;
		
		if(MySQL.isConnected()) {
			String uuid = p.getUniqueId().toString();
			int currentCoins = Coin_API.getCoins(p.getPlayer());
			p.sendMessage("§8» §6Du hast momentan §e " + currentCoins + "$" + " §6Coins");
		}else {
			p.sendMessage("§8» §cEin Fehler ist aufgetreten (MySQL disconnect), melde das einem Admin!");
		}
		
		return false;
	}

	
	
}
