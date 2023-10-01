package de.realmeze.menu.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GoldSwitchCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("goldswitch")) {
            if (!(commandSender instanceof Player)) {
                return false;
            }
            Player player = (Player) commandSender;
            if(!player.hasPermission("lionpvp.extra.goldswitch")){
                player.sendMessage("no perms, but it!");
                return true;
            }
            List<ItemStack> goldNuggetsInInv = getGoldNuggetsInInv(player);
            int amount = countTotalItemsInStacks(goldNuggetsInInv);
            convertToGoldBar(player, amount);
            player.updateInventory();
            return true;
        }
        return false;
    }

    private void convertToGoldBar(Player player, int nuggetAmount) {
        if (nuggetAmount <= 0) {
            player.sendMessage("Du hast keine Nuggets!");
            return;
        }
        int goldBars = 0;
        int goldNuggets = 0;
        if (nuggetAmount >= 9) {
            goldBars = nuggetAmount / 9;
            goldNuggets = nuggetAmount % 9;
            player.sendMessage(nuggetAmount + " Nuggets wurden in " + goldNuggets + " Nuggets und " + goldBars + " Goldbars getauscht");
        } else {
            goldNuggets = nuggetAmount;
            player.sendMessage(nuggetAmount + " Nuggets wurden in " + goldNuggets + " Nuggets getauscht");
        }
        if (goldBars > 0) {
            player.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, goldBars));
        }
        if (goldNuggets > 0) {
            player.getInventory().addItem(new ItemStack(Material.GOLD_NUGGET, goldNuggets));
        }
    }

    private List<ItemStack> getGoldNuggetsInInv(Player player) {
        List<ItemStack> goldNuggetsInInv = new ArrayList<ItemStack>();
        for (ItemStack potentialNugget : player.getInventory().getContents()) {
            if (potentialNugget == null || potentialNugget.getType() == Material.AIR) {
                continue;
            }
            if (potentialNugget.getType() != Material.GOLD_NUGGET) {
                continue;
            }
            player.getInventory().remove(potentialNugget);
            goldNuggetsInInv.add(potentialNugget);
        }
        player.updateInventory();
        return goldNuggetsInInv;
    }

    private int countTotalItemsInStacks(List<ItemStack> items) {
        int count = 0;
        for (ItemStack item : items) {
            count += item.getAmount();
        }
        return count;
    }
}
