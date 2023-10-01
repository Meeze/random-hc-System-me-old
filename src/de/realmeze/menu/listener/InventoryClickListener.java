package de.realmeze.menu.listener;

import de.realmeze.menu.controller.InventoryController;
import de.realmeze.menu.model.ClickAction;
import de.realmeze.menu.model.ClickableInventory;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InventoryClickListener implements Listener {

    private InventoryController inventoryController;

    public InventoryClickListener(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
    }

    public InventoryController getInventoryController() {
        return inventoryController;
    }

    public void setInventoryController(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getClickedInventory().getType() == InventoryType.ENCHANTING) {
            if(event.getSlot() == 1){
                event.setCancelled(true);
                return;
            }
        }
        String inventoryIdentifier = getInventoryController().getInventoryIdentifier();
        if (!event.getInventory().getTitle().contains(inventoryIdentifier)) {
            return;
        }
        if(event.getWhoClicked().getOpenInventory().getTopInventory().getTitle().contains(inventoryIdentifier)){
            event.setCancelled(true);
        }
        Inventory clickedInventory = event.getClickedInventory();

        for (ClickableInventory clickableInventory : getInventoryController().getInventories().values()) {
            if (clickedInventory.getTitle().equalsIgnoreCase(clickableInventory.getTitle() + inventoryIdentifier)) {
                for (ClickAction actionSlot : clickableInventory.getClickActions()) {
                    if (event.getSlot() == actionSlot.getSlot()) {
                        Bukkit.dispatchCommand(event.getWhoClicked(), actionSlot.getCommandToExecute());
                    }
                }
            }
        }
    }
}
