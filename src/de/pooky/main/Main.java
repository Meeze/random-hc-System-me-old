package de.pooky.main;

import de.realmeze.menu.InventoryMain;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.pooky.MySQL.MySQL;
import de.pooky.commands.Abfall_Command;
import de.pooky.commands.AddCoins_Command;
import de.pooky.commands.Eat_Command;
import de.pooky.commands.Fix_Command;
import de.pooky.commands.Money_Command;
import de.pooky.commands.Pay_Command;
import de.pooky.commands.RemoveCoins_Command;
import de.pooky.commands.SetCoins_Command;
import de.pooky.shop.ItemFrameShop;
import de.pooky.utils.ColoredTablist;

public class Main extends JavaPlugin implements Listener{
	
	public void onEnable() {
	
		System.out.println("Das Plugin wurde aktiviert!");
		registerCommands();
		registerListener();
		MySQL.connect();
		MySQL.createTable();
		InventoryMain inventoryMain = new InventoryMain(this);
	}
	
	public void onDisable() {
		
		System.out.println("Das Plugin wurde deaktiviert!");
		MySQL.disconnect();
		
	}
	
	public void registerCommands() {
		getCommand("money").setExecutor((CommandExecutor)new Money_Command());
		getCommand("addcoins").setExecutor((CommandExecutor)new AddCoins_Command());
		getCommand("setcoins").setExecutor((CommandExecutor)new SetCoins_Command());
		getCommand("removecoins").setExecutor((CommandExecutor)new RemoveCoins_Command());
		getCommand("pay").setExecutor((CommandExecutor)new Pay_Command());
		getCommand("eat").setExecutor((CommandExecutor)new Eat_Command());
		getCommand("fix").setExecutor((CommandExecutor)new Fix_Command());
		getCommand("abfall").setExecutor((CommandExecutor)new Abfall_Command());
	}
	
	public void registerListener() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new ItemFrameShop(), this);
		pm.registerEvents(new ColoredTablist(), this);
	}
	
	
	

}
