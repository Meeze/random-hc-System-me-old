package de.realmeze.menu.command;

import de.realmeze.menu.controller.InventoryController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MenuCommand implements CommandExecutor {

    private InventoryController inventoryController;

    public MenuCommand(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
    }

    public InventoryController getInventoryController() {
        return inventoryController;
    }

    public void setInventoryController(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("menu")) {
            if(!(commandSender instanceof Player)){
                return true;
            }
            Player player = (Player) commandSender;
            Inventory inventoryView = getInventoryController().createInventoryView(command.getName().toLowerCase(), player, true);
            player.openInventory(inventoryView);
            return true;
        }
        return false;
    }
}
