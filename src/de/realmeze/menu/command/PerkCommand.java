package de.realmeze.menu.command;

import de.realmeze.menu.controller.InventoryController;
import de.realmeze.menu.controller.PerkController;
import de.realmeze.menu.model.Perk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PerkCommand implements CommandExecutor {

    private InventoryController inventoryController;
    private PerkController perkController;

    public PerkCommand(InventoryController inventoryController, PerkController perkController) {
        this.inventoryController = inventoryController;
        this.perkController = perkController;
    }

    public PerkController getPerkController() {
        return perkController;
    }

    public void setPerkController(PerkController perkController) {
        this.perkController = perkController;
    }

    public InventoryController getInventoryController() {
        return inventoryController;
    }

    public void setInventoryController(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("perk")) {
            if (!(commandSender instanceof Player)) {
                return true;
            }
            Player player = (Player) commandSender;
            if (args.length == 0) {
                Inventory inventoryView = getInventoryController().createInventoryView(command.getName().toLowerCase(), player, true);
                player.openInventory(inventoryView);
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("buy")) {
                    Inventory inventoryView = getInventoryController().createInventoryView(command.getName().toLowerCase() + args[0].toLowerCase(), player, true);
                    player.openInventory(inventoryView);
                }
            } else if (args.length == 2) {
                String subCommand = args[0];
                String perkName = args[1];
                if (!getPerkController().exists(perkName)) {
                    return false;
                }
                Perk perk = getPerkController().getPerk(perkName);
                if (subCommand.equalsIgnoreCase("buy")) {
                    if(player.hasPermission("lionpvp.perk." + perk.getName() )){
                        player.sendMessage("Du hast dieses Perk bereits gekauft!");
                        return true;
                    }
                    if (getPerkController().buyPerk(player, perk)) {
                        player.sendMessage("bought: " + perk.getName());
                    } else {
                        player.sendMessage("nicht genug geld");
                    }
                } else if (subCommand.equalsIgnoreCase("on")) {
                    if (player.hasPermission("lionpvp.perk." + perkName)) {
                        boolean activated = perkController.activatePerk(player, perk);
                        if (activated) {
                            player.sendMessage("Das Perk wurde aktiviert");
                        } else {
                            perkController.deactivatePerk(player, perk);
                            player.sendMessage("Das Perk war bereits aktiv und wurde deaktiviert");
                        }
                    } else {
                        player.sendMessage("Du hast dieses Perk nicht!");
                    }
                } else if (subCommand.equalsIgnoreCase("off")) {
                    if (player.hasPermission("lionpvp.perk." + perkName)) {
                        boolean deactivated = perkController.deactivatePerk(player, perk);
                        if (deactivated) {
                            player.sendMessage("Das Perk wurde deaktiviert");
                        } else {
                            perkController.activatePerk(player, perk);
                            player.sendMessage("Das Perk war nicht aktiv und wurde aktiviert");
                        }
                    } else {
                        player.sendMessage("Du hast dieses Perk nicht!");
                    }
                }
            }
            return true;
        }
        return false;
    }
}
