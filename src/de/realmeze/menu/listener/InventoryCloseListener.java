package de.realmeze.menu.listener;

import de.realmeze.menu.controller.EnchantingController;
import de.realmeze.menu.controller.InventoryController;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryCloseListener implements Listener {

    private InventoryController inventoryController;
    private EnchantingController enchantingController;

    public InventoryCloseListener(InventoryController inventoryController, EnchantingController enchantingController) {
        this.inventoryController = inventoryController;
        this.enchantingController = enchantingController;
    }

    public InventoryController getInventoryController() {
        return inventoryController;
    }

    public void setInventoryController(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
    }

    public EnchantingController getEnchantingController() {
        return enchantingController;
    }

    public void setEnchantingController(EnchantingController enchantingController) {
        this.enchantingController = enchantingController;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if(event.getInventory().getType() == InventoryType.ENCHANTING) {
            event.getInventory().setItem(1, null);
            Bukkit.broadcastMessage("exited any enchanter");
            Player player = (Player) event.getPlayer();
            if(getEnchantingController().isEnchanting(player)){
                getEnchantingController().removeEnchantingPlayer(player);
                Bukkit.broadcastMessage("exited enchanter custom");
            } else {
                Bukkit.broadcastMessage("exited normal enchanter");
            }
        }

        String inventoryIdentifier = getInventoryController().getInventoryIdentifier();
        if (!event.getInventory().getTitle().contains(inventoryIdentifier)) {
            return;
        }
        String parsedInvKey = event.getInventory().getTitle().split(" : ")[0];
        Bukkit.broadcastMessage(parsedInvKey);
        // only need to update cache if inv is changeable by user actions aka buying/activating perks
        // this changing itemstack behaviour still needs to be implemented tho
        if(parsedInvKey.equalsIgnoreCase("perkbuy") || parsedInvKey.equalsIgnoreCase("perk")){
            inventoryController.updateContentInCache((Player) event.getPlayer(), parsedInvKey.toLowerCase(), event.getInventory().getContents());
        }
    }
}
