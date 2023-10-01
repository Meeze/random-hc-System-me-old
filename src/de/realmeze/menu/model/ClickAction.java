package de.realmeze.menu.model;

import org.bukkit.inventory.ItemStack;

public class ClickAction {

    private int slot;
    private String commandToExecute;
    private ItemStack clickedItem;

    public ClickAction(int slot, String commandToExecute, ItemStack clickedItem) {
        this.slot = slot;
        this.commandToExecute = commandToExecute;
        this.clickedItem = clickedItem;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getCommandToExecute() {
        return commandToExecute;
    }

    public void setCommandToExecute(String commandToExecute) {
        this.commandToExecute = commandToExecute;
    }

    public ItemStack getClickedItem() {
        return clickedItem;
    }

    public void setClickedItem(ItemStack clickedItem) {
        this.clickedItem = clickedItem;
    }
}
