package de.realmeze.menu.model;

import org.bukkit.inventory.ItemStack;

public class ClickableInventory {

    private ItemStack[] contentView;
    private int size;
    private String title;
    private ClickAction[] clickActions;

    public ClickableInventory(ItemStack[] contentView, int size, String title, ClickAction[] clickActions) {
        this.contentView = contentView;
        this.size = size;
        this.title = title;
        this.clickActions = clickActions;
    }

    public ItemStack[] getContentView() {
        return contentView;
    }

    public void setContentView(ItemStack[] contentView) {
        this.contentView = contentView;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ClickAction[] getClickActions() {
        return clickActions;
    }

    public void setClickActions(ClickAction[] clickActions) {
        this.clickActions = clickActions;
    }
}
