package de.pooky.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

public class Coin_API {

	
	
	public static int getCoins(Player p) {
		int i = 500;
		try {
			ResultSet rs = MySQL.Query("SELECT * FROM `coinTable` WHERE `UUID` = '" + p.getUniqueId().toString() + "'");
			if(rs.next()) {
				i = rs.getInt("coins");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;

	}
	
	public static void addCoins(Player p, int coins) {
		int current = getCoins(p.getPlayer());
		setCoins(p.getPlayer(), current + coins);
	}
	
	public static void removeCoins(Player p, int coins) {
		int current = getCoins(p.getPlayer());
		if(coins > current) {
			p.sendMessage("§8» §cDu hast nicht genügend Geld.");
			return;
		}
		setCoins(p.getPlayer(), current - coins);
	}
	
	public static void setCoins(Player p, int coins) {
		MySQL.Update("UPDATE `coinTable` SET `coins` = '" + coins + "' WHERE `UUID` = '" + p.getUniqueId().toString() + "'");
	}
	
	public static boolean canPurchase(Player p, int coins) {
		
		int current = getCoins(p.getPlayer());
		
		if(coins > current) {
			return false;
		}else {
			return true;
		}
		
		
		
	}
	
}
