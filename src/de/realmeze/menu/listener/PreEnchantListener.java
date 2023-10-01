package de.realmeze.menu.listener;

import de.realmeze.menu.controller.EnchantingController;
import org.bukkit.DyeColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

import java.util.concurrent.ThreadLocalRandom;

public class PreEnchantListener implements Listener {

    private EnchantingController enchantingController;

    public PreEnchantListener(EnchantingController enchantingController) {
        this.enchantingController = enchantingController;
    }

    @EventHandler
    public void onEnchanterOpen(InventoryOpenEvent event){
        if(event.getInventory().getType() == InventoryType.ENCHANTING){
            Dye dye = new Dye();
            dye.setColor(DyeColor.BLUE);
            ItemStack lapis = dye.toItemStack();
            lapis.setAmount(64);
            event.getInventory().setItem(1, lapis);
        }
    }

    @EventHandler
    public void onPreEnchant(PrepareItemEnchantEvent event) {
        if(enchantingController.isEnchanting(event.getEnchanter())) {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int[] xpCosts = new int[]{random.nextInt(1, 8), random.nextInt(9, 22), 30};
            event.getExpLevelCostsOffered()[0] = xpCosts[0];
            event.getExpLevelCostsOffered()[1] = xpCosts[1];
            event.getExpLevelCostsOffered()[2] = xpCosts[2];
        }
    }

}
