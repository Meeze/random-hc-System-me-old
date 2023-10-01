package de.pooky.commands;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

public class Fix_Command implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {

		Player p = (Player) sender;
		
		if(!(sender instanceof Player)) {
			sender.sendMessage("Diesen Befehl darfst du nur als Spieler ausführen!");
			return false;
		}
			
			
		
			
		
			if (args.length == 0) {
				ArrayList<ItemStack> liste = new ArrayList<>();
				for (ItemStack i : p.getInventory().getContents())
					liste.add(i);
				for (ItemStack i : p.getInventory().getArmorContents())
					liste.add(i);

				for (ItemStack item : liste) {
					
					if(item != null && item.getDurability() == (short)-1){
						ActionBarAPI.sendActionBar(p, "§cEs gibt keine Items zum reparieren.");
						return true;
					}else if(item != null && shouldRepaired(item.getType()))  {
						item.setDurability((short) -1);
						ActionBarAPI.sendActionBar(p, "§7Deine Items wurden §arepariert§7.");
						return true;
				}
				p.updateInventory();
				}
			}else {
				ActionBarAPI.sendActionBar(p, "§cVerwende /fix");
				return true;
		}
			return false;
	}
		

		
	

	public boolean shouldRepaired(Material item) {
		ArrayList<Material> torepair = new ArrayList<>();
		
		//DIAMOND
		torepair.add(Material.DIAMOND_SWORD);
		torepair.add(Material.DIAMOND_AXE);
		torepair.add(Material.DIAMOND_HOE);
		torepair.add(Material.DIAMOND_PICKAXE);
		torepair.add(Material.DIAMOND_SPADE);
		
		torepair.add(Material.DIAMOND_HELMET);
		torepair.add(Material.DIAMOND_CHESTPLATE);
		torepair.add(Material.DIAMOND_LEGGINGS);
		torepair.add(Material.DIAMOND_BOOTS);
		
		//IRON
		torepair.add(Material.IRON_SWORD);
		torepair.add(Material.IRON_AXE);
		torepair.add(Material.IRON_HOE);
		torepair.add(Material.IRON_PICKAXE);
		torepair.add(Material.IRON_SPADE);
		
		torepair.add(Material.IRON_HELMET);
		torepair.add(Material.IRON_CHESTPLATE);
		torepair.add(Material.IRON_LEGGINGS);
		torepair.add(Material.IRON_BOOTS);
		
		//GOLD
		torepair.add(Material.GOLD_SWORD);
		torepair.add(Material.GOLD_AXE);
		torepair.add(Material.GOLD_HOE);
		torepair.add(Material.GOLD_PICKAXE);
		torepair.add(Material.GOLD_SPADE);
		
		torepair.add(Material.GOLD_HELMET);
		torepair.add(Material.GOLD_CHESTPLATE);
		torepair.add(Material.GOLD_LEGGINGS);
		torepair.add(Material.GOLD_BOOTS);
		
		//LEATHER | WOOD
		torepair.add(Material.WOOD_SWORD);
		torepair.add(Material.WOOD_AXE);
		torepair.add(Material.WOOD_HOE);
		torepair.add(Material.WOOD_PICKAXE);
		torepair.add(Material.WOOD_SPADE);
		
		torepair.add(Material.LEATHER_HELMET);
		torepair.add(Material.LEATHER_CHESTPLATE);
		torepair.add(Material.LEATHER_LEGGINGS);
		torepair.add(Material.LEATHER_BOOTS);
		
		//CHAINMAIL | STONE
		torepair.add(Material.STONE_SWORD);
		torepair.add(Material.STONE_AXE);
		torepair.add(Material.STONE_HOE);
		torepair.add(Material.STONE_PICKAXE);
		torepair.add(Material.STONE_SPADE);
		
		torepair.add(Material.CHAINMAIL_HELMET);
		torepair.add(Material.CHAINMAIL_CHESTPLATE);
		torepair.add(Material.CHAINMAIL_LEGGINGS);
		torepair.add(Material.CHAINMAIL_BOOTS);
		
		//OTHER TOOLS
		torepair.add(Material.BOW);
		torepair.add(Material.CARROT_STICK);
		torepair.add(Material.FLINT_AND_STEEL);
		torepair.add(Material.FISHING_ROD);
		torepair.add(Material.SHEARS);
		
		

		return torepair.contains(item);

	
}
	
}


	
