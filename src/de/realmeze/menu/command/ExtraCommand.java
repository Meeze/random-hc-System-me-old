package de.realmeze.menu.command;

import de.realmeze.menu.controller.ExtraController;
import de.realmeze.menu.controller.InventoryController;
import de.realmeze.menu.model.ExtraCmd;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ExtraCommand implements CommandExecutor {

    private InventoryController inventoryController;
    private ExtraController extraController;

    public ExtraCommand(InventoryController inventoryController, ExtraController extraController) {
        this.inventoryController = inventoryController;
        this.extraController = extraController;
    }

    public InventoryController getInventoryController() {
        return inventoryController;
    }

    public void setInventoryController(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
    }

    public ExtraController getExtraController() {
        return extraController;
    }

    public void setExtraController(ExtraController extraController) {
        this.extraController = extraController;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        if (command.getName().equalsIgnoreCase("extra")) {
            if (args.length == 0) {
                Inventory inventoryView = getInventoryController().createInventoryView(command.getName().toLowerCase(), player, true);
                player.openInventory(inventoryView);
            } else if (args.length == 1) {
                player.sendMessage("extra buy name");
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("buy")) {
                    String cmdName = args[1];
                    if (getExtraController().existExtraCommand(cmdName)) {
                        ExtraCmd toBuy = getExtraController().getCommand(cmdName);
                        if(player.hasPermission("lionpvp.extra." + toBuy.getName() )){
                            player.sendMessage("Du hast dieses Extra Command bereits gekauft!");
                            return true;
                        }
                        boolean purchased = getExtraController().purchaseCommand(player, toBuy);
                        if (purchased) {
                            player.sendMessage("command " + toBuy.getName() + " gekauft");
                        } else {
                            player.sendMessage("nicht genug geld");
                        }
                    } else {
                        player.sendMessage("das gibts nicht kek");
                    }
                }
            }

        }
        return false;
    }
}
