package de.realmeze.menu.controller;

import de.realmeze.menu.model.ClickAction;
import de.realmeze.menu.model.ClickableInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class InventoryController {

    private HashMap<String, ClickableInventory> inventories;
    private HashMap<Player, Inventory> menuViewCache;
    private HashMap<Player, Inventory> perkViewCache;
    private HashMap<Player, Inventory> perkBuyViewCache;
    private HashMap<Player, Inventory> extraViewCache;
    private String inventoryIdentifier;

    private ItemStack placeholder = new ItemStack(Material.STAINED_GLASS_PANE, 1);

    public InventoryController(HashMap<String, ClickableInventory> inventories, HashMap<Player, Inventory> menuViewCache, HashMap<Player, Inventory> perkViewCache, HashMap<Player, Inventory> perkBuyViewCache, HashMap<Player, Inventory> extraViewCache, String inventoryIdentifier) {
        this.inventories = inventories;
        this.menuViewCache = menuViewCache;
        this.perkViewCache = perkViewCache;
        this.perkBuyViewCache = perkBuyViewCache;
        this.extraViewCache = extraViewCache;
        this.inventoryIdentifier = inventoryIdentifier;
        registerInventories();
    }

    public HashMap<String, ClickableInventory> getInventories() {
        return inventories;
    }

    public void setInventories(HashMap<String, ClickableInventory> inventories) {
        this.inventories = inventories;
    }

    public HashMap<Player, Inventory> getMenuViewCache() {
        return menuViewCache;
    }

    public void setMenuViewCache(HashMap<Player, Inventory> inventoryViewCache) {
        this.menuViewCache = inventoryViewCache;
    }

    public HashMap<Player, Inventory> getPerkViewCache() {
        return perkViewCache;
    }

    public void setPerkViewCache(HashMap<Player, Inventory> perkViewCache) {
        this.perkViewCache = perkViewCache;
    }

    public HashMap<Player, Inventory> getPerkBuyViewCache() {
        return perkBuyViewCache;
    }

    public void setPerkBuyViewCache(HashMap<Player, Inventory> perkBuyViewCache) {
        this.perkBuyViewCache = perkBuyViewCache;
    }

    public HashMap<Player, Inventory> getExtraViewCache() {
        return extraViewCache;
    }

    public void setExtraViewCache(HashMap<Player, Inventory> extraViewCache) {
        this.extraViewCache = extraViewCache;
    }

    public String getInventoryIdentifier() {
        return inventoryIdentifier;
    }

    public void setInventoryIdentifier(String inventoryIdentifier) {
        this.inventoryIdentifier = inventoryIdentifier;
    }


    public ItemStack getPlaceholder() {
        return placeholder;
    }

    public ClickableInventory getInventory(String inventoryKey) {
        return inventories.get(inventoryKey);
    }

    public void registerInventories() {
        registerMenu();
        registerPerkMenu();
        registerPerkBuyMenu();
        registerExtraCommand();
    }

    public void registerInventory(String key, int size, String title, ItemStack[] content, ClickAction[] clickActions) {
        ClickableInventory clickableInventory = new ClickableInventory(content, size, title, clickActions);
        getInventories().put(key, clickableInventory);
    }

    private void registerMenu() {
        String title = "Menu";

        ItemStack perkDisplayItem = new ItemStack(Material.POTION, 1);
        ItemStack extraCommandDisplayItem = new ItemStack(Material.COMMAND, 1);

        ClickAction extra = new ClickAction(12, "extra", extraCommandDisplayItem);
        ClickAction perk = new ClickAction(14, "perk buy", perkDisplayItem);
        ClickAction[] clickActions = new ClickAction[]{extra, perk};

        int size = 36;

        ItemStack[] content = new ItemStack[36];
        for (int i = 0; i < size; i++) {
            if (i == perk.getSlot()) {
                content[i] = perk.getClickedItem();
            } else if (i == extra.getSlot()) {
                content[i] = extra.getClickedItem();
            } else {
                content[i] = getPlaceholder();
            }
        }
        registerInventory("menu", size, title, content, clickActions);
    }

    private void registerPerkBuyMenu() {
        String title = "Perkbuy";
        ItemStack potion = new ItemStack(Material.POTION);
        ClickAction speedPerk = new ClickAction(0, "perk buy speed", potion);
        ClickAction strengthPerk = new ClickAction(1, "perk buy strength", potion);
        ClickAction jumpPerk = new ClickAction(2, "perk buy jump", potion);
        ClickAction anti_firePerk = new ClickAction(3, "perk buy antifire", potion);
        ClickAction no_hungerPerk = new ClickAction(4, "perk buy nohunger", potion);
        ClickAction night_visionPerk = new ClickAction(5, "perk buy nightvision", potion);
        ClickAction[] clickActions = new ClickAction[]{speedPerk, strengthPerk, jumpPerk, anti_firePerk, no_hungerPerk, night_visionPerk};
        int size = 9;
        ItemStack[] content = new ItemStack[9];
        for (int i = 0; i < size; i++) {
            if (i == speedPerk.getSlot()) {
                content[i] = speedPerk.getClickedItem();
            } else if (i == strengthPerk.getSlot()) {
                content[i] = strengthPerk.getClickedItem();
            } else if (i == jumpPerk.getSlot()) {
                content[i] = jumpPerk.getClickedItem();
            } else if (i == anti_firePerk.getSlot()) {
                content[i] = anti_firePerk.getClickedItem();
            } else if (i == no_hungerPerk.getSlot()) {
                content[i] = no_hungerPerk.getClickedItem();
            } else if (i == night_visionPerk.getSlot()) {
                content[i] = night_visionPerk.getClickedItem();
            } else {
                content[i] = getPlaceholder();
            }
        }
        registerInventory("perkbuy", size, title, content, clickActions);
    }

    private void registerPerkMenu() {
        String title = "Perks";
        ItemStack potion = new ItemStack(Material.POTION);
        ClickAction speedPerk = new ClickAction(0, "perk on speed", potion);
        ClickAction strengthPerk = new ClickAction(1, "perk on strength", potion);
        ClickAction jumpPerk = new ClickAction(2, "perk on jump", potion);
        ClickAction anti_firePerk = new ClickAction(3, "perk on antifire", potion);
        ClickAction no_hungerPerk = new ClickAction(4, "perk on nohunger", potion);
        ClickAction night_visionPerk = new ClickAction(5, "perk on nightvision", potion);
        ClickAction[] clickActions = new ClickAction[]{speedPerk, strengthPerk, jumpPerk, anti_firePerk, no_hungerPerk, night_visionPerk};
        int size = 9;
        ItemStack[] content = new ItemStack[9];
        for (int i = 0; i < size; i++) {
            if (i == speedPerk.getSlot()) {
                content[i] = speedPerk.getClickedItem();
            } else if (i == strengthPerk.getSlot()) {
                content[i] = strengthPerk.getClickedItem();
            } else if (i == jumpPerk.getSlot()) {
                content[i] = jumpPerk.getClickedItem();
            } else if (i == anti_firePerk.getSlot()) {
                content[i] = anti_firePerk.getClickedItem();
            } else if (i == no_hungerPerk.getSlot()) {
                content[i] = no_hungerPerk.getClickedItem();
            } else if (i == night_visionPerk.getSlot()) {
                content[i] = night_visionPerk.getClickedItem();
            } else {
                content[i] = getPlaceholder();
            }
        }
        registerInventory("perk", size, title, content, clickActions);
    }

    private void registerExtraCommand() {
        String title = "Extra Commands";

        ItemStack extraCommandItem = new ItemStack(Material.COMMAND, 1);

        ClickAction buyGoldSwitch = new ClickAction(1, "extra buy goldswitch", extraCommandItem);
        ClickAction buyFill = new ClickAction(3, "extra buy fill", extraCommandItem);
        ClickAction buyEnchant = new ClickAction(5, "extra buy enchant ", extraCommandItem);
        ClickAction[] clickActions = new ClickAction[]{buyEnchant, buyFill, buyGoldSwitch};

        int size = 9;

        ItemStack[] content = new ItemStack[9];
        for (int i = 0; i < size; i++) {
            if (i == buyGoldSwitch.getSlot()) {
                content[i] = buyGoldSwitch.getClickedItem();
            } else if (i == buyFill.getSlot()) {
                content[i] = buyFill.getClickedItem();
            }else if (i == buyEnchant.getSlot()) {
                content[i] = buyEnchant.getClickedItem();
            } else {
                content[i] = getPlaceholder();
            }
        }
        registerInventory("extra", size, title, content, clickActions);
    }

    public Inventory createInventoryView(String invKey, Player forPlayer, boolean tryCache) {
        Inventory view = null;
        if (tryCache) {
            view = getViewFromCache(invKey, forPlayer);
        }
        if (view == null) {
            ClickableInventory clickableInventory = getInventory(invKey);
            view = Bukkit.createInventory(forPlayer, clickableInventory.getSize(), clickableInventory.getTitle() + getInventoryIdentifier());
            view.setContents(clickableInventory.getContentView());

            cacheInventoryView(forPlayer, view, invKey);
        }
        return view;
    }

    private Inventory getViewFromCache(String invKey, Player player) {
        return getCacheFor(invKey).get(player);
    }

    private void cacheInventoryView(Player player, Inventory inventory, String invKey) {
        getCacheFor(invKey).put(player, inventory);
    }

    private HashMap<Player, Inventory> getCacheFor(String invKey) {
        if (invKey.equalsIgnoreCase("menu")) {
            return getMenuViewCache();
        } else if (invKey.equalsIgnoreCase("perk")) {
            return getPerkViewCache();
        } else if (invKey.equalsIgnoreCase("perkbuy")) {
            return getPerkBuyViewCache();
        } else if(invKey.equalsIgnoreCase("extra")) {
            return getExtraViewCache();
        }
        return null;
    }

    public void updateContentInCache(Player player, String invKey, ItemStack[] newContent) {
        getCacheFor(invKey).get(player).setContents(newContent);
    }

}
