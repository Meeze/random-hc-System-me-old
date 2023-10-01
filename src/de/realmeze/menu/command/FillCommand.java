package de.realmeze.menu.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class FillCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(command.getName().equalsIgnoreCase("fill")){
            if (!(commandSender instanceof Player)) {
                return false;
            }
            Player player = (Player) commandSender;
            if(!player.hasPermission("lionpvp.extra.fill")){
                player.sendMessage("no perms, but it!");
                return true;
            }
            ItemStack[] invContent = player.getInventory().getContents();
            for (ItemStack potentialItemToFill: invContent) {
                if(potentialItemToFill == null) {
                    continue;
                }
                if(potentialItemToFill.getType() == Material.GLASS_BOTTLE) {
                    potentialItemToFill.setType(Material.POTION);
                }
            }
            player.sendMessage("Deine Flaschen wurden aufgefüllt!");
            return true;
        }
        return false;
    }
}
