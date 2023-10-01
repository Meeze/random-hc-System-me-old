package de.pooky.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.pooky.MySQL.Coin_API;
import de.pooky.MySQL.MySQL;

public class Pay_Command implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player p = (Player)sender;
		
		if(args.length != 2) {
			p.sendMessage("�7Verwendung : �c/pay <Spieler> <Betrag>");
		}else {
			if(MySQL.isConnected()) {
				String puuid = p.getUniqueId().toString();
				int coins = Integer.parseInt(args[1]);
				Coin_API.removeCoins(p.getPlayer(), coins);
				
				Player t = Bukkit.getPlayer(args[0]);
				String tuuid = p.getUniqueId().toString();
				int tCoins = Coin_API.getCoins(t.getPlayer());
				Coin_API.addCoins(t.getPlayer(), coins);
				p.sendMessage("�8� �6Du hast �e " + t.getName() + " �e " + coins + "$ �6�berwiesen.");
				
			}else {
				p.sendMessage("�8� �cEin Fehler ist aufgetreten (MySQL disconnect), melde das einem Admin!");
			}
			
			
		}
		return false;

	}

	
	
}
