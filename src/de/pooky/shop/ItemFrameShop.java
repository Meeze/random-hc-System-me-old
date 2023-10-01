package de.pooky.shop;



import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import de.pooky.MySQL.Coin_API;

public class ItemFrameShop implements Listener{

	
    ItemStack item;
    public static HashMap<Material, Integer> preise = new HashMap<Material, Integer>();
    
		@EventHandler
	   public void onRightClick(PlayerInteractEntityEvent event){
	     Entity e = event.getRightClicked();
	     Player p = event.getPlayer();
	     ItemStack item;
	     ItemFrame itemFrame;
	     
	     setPreise();
		
	     
	     if(e instanceof ItemFrame){
	       itemFrame = (ItemFrame) e;
	       item = itemFrame.getItem();
	       

	       
	       if(item.getType() == Material.AIR || item.getType() == null) {
	    	   
	    	   ItemStack hand = p.getInventory().getItemInHand();
	    	   itemFrame.setItem(hand);
	    	   
	    	   event.setCancelled(true);
	    	   return;
	       }else if(!preise.containsKey(item.getType())) {
			   p.sendMessage("§8» §cDieses Item ist derzeit nicht verfügbar.");
			   event.setCancelled(true);
			   return;
		   }
	     
	       
	    	   
	    	   if(p.isSneaking()) {
	    		   

	    		   
	    		   if(shouldNotShiftClick(item.getType())) {
	    			   item.setAmount(64);
	    			   
	    			   int preis = preise.get(item.getType());
	    			   int stackPreis = preis * 64;
		    		   if(Coin_API.canPurchase(p, stackPreis)) {
			    		   p.sendMessage("§8» §eDu hast §764x §6" + item.getType() + " §efür §e" + stackPreis + "$" + " §egekauft");
			    		   for(int i = 0; i<64; i++) {
			    			   p.getInventory().addItem(item);
			    		   }
		    		   }else {
		    			   p.sendMessage("§8» §cDu hast nicht genügend Geld.");
		    		   }
	    			   event.setCancelled(true);
	    			   return;
	    		   }
	    		   
	    		   
	    		   int preis = preise.get(item.getType());
	    		   int stackPreis = preis * 64;
	    		   if(Coin_API.canPurchase(p, stackPreis)) {
		    		   p.sendMessage("§8» §eDu hast §764x §6" + item.getType() + " §efür §e" + stackPreis + "$" + " §egekauft");
		    		   for(int i = 0; i<64; i++) {
		    			   p.getInventory().addItem(item);
		    		   }
	    		   }else {
	    			   p.sendMessage("§8» §cDu hast nicht genügend Geld.");
	    		   }
	    		   event.setCancelled(true);
	    		   
	    		   return;
	    	   }
	    	   
		       
		       int preis = preise.get(item.getType());
		       int normalPreis = preis * 1;
    		   if(Coin_API.canPurchase(p, normalPreis)) {
	    		   p.sendMessage("§8» §eDu hast §71x §6" + item.getType() + " §efür §e" + normalPreis + "$" + " §egekauft");
	    		   p.getInventory().addItem(item);
    		   }else {
    			   p.sendMessage("§8» §cDu hast nicht genügend Geld.");
    		   }
		     


		       
		       event.setCancelled(true);
	    	   
	       }
		}
	       

	     
	   
		
		public boolean shouldNotShiftClick(Material item) {
			ArrayList<Material> noShift = new ArrayList<>();
			
			//DIAMOND
			noShift.add(Material.DIAMOND_SWORD);
			noShift.add(Material.DIAMOND_AXE);
			noShift.add(Material.DIAMOND_HOE);
			noShift.add(Material.DIAMOND_PICKAXE);
			noShift.add(Material.DIAMOND_SPADE);
			
			noShift.add(Material.DIAMOND_HELMET);
			noShift.add(Material.DIAMOND_CHESTPLATE);
			noShift.add(Material.DIAMOND_LEGGINGS);
			noShift.add(Material.DIAMOND_BOOTS);
			
			//IRON
			noShift.add(Material.IRON_SWORD);
			noShift.add(Material.IRON_AXE);
			noShift.add(Material.IRON_HOE);
			noShift.add(Material.IRON_PICKAXE);
			noShift.add(Material.IRON_SPADE);
			
			noShift.add(Material.IRON_HELMET);
			noShift.add(Material.IRON_CHESTPLATE);
			noShift.add(Material.IRON_LEGGINGS);
			noShift.add(Material.IRON_BOOTS);
			
			//GOLD
			noShift.add(Material.GOLD_SWORD);
			noShift.add(Material.GOLD_AXE);
			noShift.add(Material.GOLD_HOE);
			noShift.add(Material.GOLD_PICKAXE);
			noShift.add(Material.GOLD_SPADE);
			
			noShift.add(Material.GOLD_HELMET);
			noShift.add(Material.GOLD_CHESTPLATE);
			noShift.add(Material.GOLD_LEGGINGS);
			noShift.add(Material.GOLD_BOOTS);
			
			//LEATHER | WOOD
			noShift.add(Material.WOOD_SWORD);
			noShift.add(Material.WOOD_AXE);
			noShift.add(Material.WOOD_HOE);
			noShift.add(Material.WOOD_PICKAXE);
			noShift.add(Material.WOOD_SPADE);
			
			noShift.add(Material.LEATHER_HELMET);
			noShift.add(Material.LEATHER_CHESTPLATE);
			noShift.add(Material.LEATHER_LEGGINGS);
			noShift.add(Material.LEATHER_BOOTS);
			
			//CHAINMAIL | STONE
			noShift.add(Material.STONE_SWORD);
			noShift.add(Material.STONE_AXE);
			noShift.add(Material.STONE_HOE);
			noShift.add(Material.STONE_PICKAXE);
			noShift.add(Material.STONE_SPADE);
			
			noShift.add(Material.CHAINMAIL_HELMET);
			noShift.add(Material.CHAINMAIL_CHESTPLATE);
			noShift.add(Material.CHAINMAIL_LEGGINGS);
			noShift.add(Material.CHAINMAIL_BOOTS);
			
			//OTHER TOOLS
			noShift.add(Material.BOW);
			noShift.add(Material.CARROT_STICK);
			noShift.add(Material.FLINT_AND_STEEL);
			noShift.add(Material.FISHING_ROD);
			noShift.add(Material.SHEARS);
			noShift.add(Material.BOOK);
			noShift.add(Material.BOOK_AND_QUILL);
			noShift.add(Material.ENCHANTED_BOOK);
			noShift.add(Material.WRITTEN_BOOK);
			noShift.add(Material.POTION);
			noShift.add(Material.WATER_BUCKET);
			noShift.add(Material.LAVA_BUCKET);
			noShift.add(Material.GLASS_BOTTLE);
			noShift.add(Material.MILK_BUCKET);
			
			
			

			return noShift.contains(item);

		
	}
		
		public Material getItem() {
			
			//welches Item wurde geklickt?
			return null;
			
		}
		
		public void setPreise() {
			
			//Brewings
			preise.put(Material.BLAZE_POWDER, 10);
			preise.put(Material.BREWING_STAND, 150);
			preise.put(Material.FERMENTED_SPIDER_EYE, 10);
			preise.put(Material.MAGMA_CREAM, 10);
			preise.put(Material.SPECKLED_MELON, 10);	//welche melone?
			preise.put(Material.GOLDEN_CARROT, 10);
			preise.put(Material.RABBIT_FOOT, 10);
			preise.put(Material.GLASS_BOTTLE, 5);
			preise.put(Material.GLOWSTONE_DUST, 10);
			preise.put(Material.SUGAR, 10);
			
			//Fight
			preise.put(Material.BOW, 100);
			preise.put(Material.ARROW, 1);
			preise.put(Material.DIAMOND_SWORD, 300);
			preise.put(Material.STONE_SWORD, 50);
			preise.put(Material.IRON_SWORD, 150);
			preise.put(Material.GOLD_SWORD, 40);
			preise.put(Material.WOOD_SWORD, 30);
			
			preise.put(Material.CHAINMAIL_BOOTS, 30);
			preise.put(Material.CHAINMAIL_CHESTPLATE, 100);
			preise.put(Material.CHAINMAIL_HELMET, 30);
			preise.put(Material.CHAINMAIL_LEGGINGS, 70);
			preise.put(Material.LEATHER_BOOTS, 10);
			preise.put(Material.LEATHER_CHESTPLATE, 20);
			preise.put(Material.LEATHER_HELMET, 10);
			preise.put(Material.LEATHER_LEGGINGS, 15);
			preise.put(Material.GOLD_HELMET, 30);
			preise.put(Material.GOLD_LEGGINGS, 50);
			preise.put(Material.GOLD_BOOTS, 30);
			preise.put(Material.GOLD_CHESTPLATE, 70);
			preise.put(Material.IRON_CHESTPLATE, 200);
			preise.put(Material.IRON_HELMET, 120);
			preise.put(Material.IRON_BOOTS, 120);						//fertig (nurnoch Preise abchecken)
			preise.put(Material.IRON_LEGGINGS, 150);
			preise.put(Material.DIAMOND_CHESTPLATE, 500);
			preise.put(Material.DIAMOND_HELMET, 350);
			preise.put(Material.DIAMOND_BOOTS, 350);			
			preise.put(Material.DIAMOND_LEGGINGS, 450);
			preise.put(Material.STONE_AXE, 30);
			preise.put(Material.STONE_HOE, 30);
			preise.put(Material.STONE_PICKAXE, 30);
			preise.put(Material.STONE_SPADE, 30);
			preise.put(Material.WOOD_AXE, 15);
			preise.put(Material.WOOD_HOE, 15);
			preise.put(Material.WOOD_PICKAXE, 15);
			preise.put(Material.WOOD_SPADE, 15);
			preise.put(Material.GOLD_AXE, 20);
			preise.put(Material.GOLD_HOE, 20);
			preise.put(Material.GOLD_PICKAXE, 20);
			preise.put(Material.GOLD_SPADE, 20);
			preise.put(Material.IRON_AXE, 100);
			preise.put(Material.IRON_HOE, 100);
			preise.put(Material.IRON_PICKAXE, 100);
			preise.put(Material.IRON_SPADE, 100);
			preise.put(Material.DIAMOND_AXE, 400);
			preise.put(Material.DIAMOND_HOE, 400);
			preise.put(Material.DIAMOND_PICKAXE, 400);
			preise.put(Material.DIAMOND_SPADE, 400);
			preise.put(Material.FISHING_ROD, 50);
			preise.put(Material.SHEARS, 30);
			
			//ores
			preise.put(Material.COAL, 10);
			preise.put(Material.COAL_BLOCK, 70);
			preise.put(Material.COAL_ORE, 5);
			preise.put(Material.DIAMOND_ORE, 50);
			preise.put(Material.DIAMOND, 70);
			preise.put(Material.DIAMOND_BLOCK, 350);
			preise.put(Material.GOLD_ORE, 20);
			preise.put(Material.GOLD_INGOT, 30);
			preise.put(Material.GOLD_BLOCK, 220);					//fertig (nurnoch Preise abchecken)
			preise.put(Material.IRON_BLOCK, 400);
			preise.put(Material.IRON_INGOT, 50);
			preise.put(Material.IRON_ORE, 30);
			preise.put(Material.EMERALD, 40);
			preise.put(Material.EMERALD_BLOCK, 300);
			preise.put(Material.EMERALD_ORE, 25);
			preise.put(Material.LAPIS_BLOCK, 150);
			preise.put(Material.LAPIS_ORE, 10);
			preise.put(Material.BRICK, 10);
			preise.put(Material.NETHER_BRICK, 15);
			preise.put(Material.GOLD_NUGGET, 2);
			preise.put(Material.LEATHER, 5);
			
			//eatables
			preise.put(Material.APPLE, 50);
			preise.put(Material.MUSHROOM_SOUP, 10);
			preise.put(Material.BREAD, 10);
			preise.put(Material.RAW_BEEF, 10);
			preise.put(Material.RAW_CHICKEN, 10);
			preise.put(Material.RAW_FISH, 10);
			preise.put(Material.GOLDEN_APPLE, 100);
			preise.put(Material.COOKED_BEEF, 20);
			preise.put(Material.COOKED_CHICKEN, 20);
			preise.put(Material.COOKED_FISH, 20);
			preise.put(Material.COOKED_MUTTON, 20);
			preise.put(Material.COOKED_RABBIT, 20);
			preise.put(Material.COOKIE, 2);
			preise.put(Material.RABBIT_STEW, 20);
			preise.put(Material.BAKED_POTATO, 15);
			preise.put(Material.POTATO, 5);
			preise.put(Material.CARROT, 5);
			preise.put(Material.MELON, 5);
			preise.put(Material.CAKE, 80);
			
			//Blöcke
			preise.put(Material.NETHER_BRICK, 10);
			preise.put(Material.NETHERRACK, 10);
			preise.put(Material.BEDROCK, 1000);
			preise.put(Material.WOOD, 10);
			preise.put(Material.BOOKSHELF, 100);
			preise.put(Material.QUARTZ_BLOCK, 10);
			preise.put(Material.WOOL, 10);
			preise.put(Material.COBBLESTONE, 1);
			preise.put(Material.MOSSY_COBBLESTONE, 10);
			preise.put(Material.CACTUS, 10);
			preise.put(Material.SAND, 10);
			preise.put(Material.SANDSTONE, 10);
			preise.put(Material.RED_SANDSTONE, 10);
			preise.put(Material.SOUL_SAND, 50);
			preise.put(Material.MYCEL, 100);
			preise.put(Material.GRAVEL, 2);
			preise.put(Material.DIRT, 1);
			preise.put(Material.GRASS, 5);
			preise.put(Material.SPONGE, 50);
			preise.put(Material.OBSIDIAN, 100);
			preise.put(Material.SNOW_BLOCK, 10);
			preise.put(Material.PUMPKIN, 10);
			preise.put(Material.CLAY, 10);		//welcher clay?
			preise.put(Material.GLOWSTONE, 10);
			preise.put(Material.MELON_BLOCK, 10);
			preise.put(Material.ENDER_STONE, 20);
			preise.put(Material.STONE, 5);
			preise.put(Material.ICE, 20);
			preise.put(Material.PACKED_ICE, 30);
			preise.put(Material.FURNACE, 10);
			preise.put(Material.CHEST, 10);
			preise.put(Material.LADDER, 5);
			preise.put(Material.WORKBENCH, 10);
			preise.put(Material.ENDER_CHEST, 200);
			preise.put(Material.ENCHANTMENT_TABLE, 200);
			preise.put(Material.ANVIL, 300);
			
			//else
			preise.put(Material.BONE, 5);
			preise.put(Material.FEATHER, 2);
			preise.put(Material.STRING, 5);
			preise.put(Material.STICK, 2);
			preise.put(Material.FLINT, 2);
			preise.put(Material.BOWL, 5);
			preise.put(Material.INK_SACK, 2);
			preise.put(Material.SUGAR_CANE, 5);
			preise.put(Material.MELON_SEEDS, 2);
			preise.put(Material.PUMPKIN_SEEDS, 2);
			preise.put(Material.SEEDS, 5);
			preise.put(Material.BLAZE_ROD, 10);
			
			
		}
	 
}


