package de.realmeze.menu.command;

import de.realmeze.menu.controller.EnchantingController;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

public class EnchantCommand implements CommandExecutor {

    private EnchantingController enchantingController;

    public EnchantCommand(EnchantingController enchantingController) {
        this.enchantingController = enchantingController;
    }

    public EnchantingController getEnchantingController() {
        return enchantingController;
    }

    public void setEnchantingController(EnchantingController enchantingController) {
        this.enchantingController = enchantingController;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("enchant")) {
            if (!(commandSender instanceof Player)) {
                return false;
            }
            Player player = (Player) commandSender;
            if(!player.hasPermission("lionpvp.extra.enchant")){
                player.sendMessage("no perms, but it!");
                return true;
            }
            Location enchanter = player.getLocation().clone();
            enchanter.setY(0.0D);
            enchanter.getBlock().setType(Material.ENCHANTMENT_TABLE);
            getEnchantingController().addEnchantingPlayer(player);
            InventoryView inventoryView = player.openEnchanting(enchanter, true);
            enchanter.getBlock().setType(Material.BEDROCK);
            return true;
        }
        return false;
    }
}
