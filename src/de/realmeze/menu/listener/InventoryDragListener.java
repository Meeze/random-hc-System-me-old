package de.realmeze.menu.listener;

import de.realmeze.menu.controller.InventoryController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryDragListener implements Listener {

    private InventoryController inventoryController;

    public InventoryDragListener(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
    }

    public InventoryController getInventoryController() {
        return inventoryController;
    }

    public void setInventoryController(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        String inventoryIdentifier = getInventoryController().getInventoryIdentifier();
        if (event.getInventory().getTitle().contains(inventoryIdentifier)) {
            event.setCancelled(true);
        }
    }
}
