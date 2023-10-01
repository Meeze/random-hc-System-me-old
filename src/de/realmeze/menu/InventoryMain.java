package de.realmeze.menu;

import de.pooky.main.Main;
import de.realmeze.menu.command.*;
import de.realmeze.menu.controller.EnchantingController;
import de.realmeze.menu.controller.ExtraController;
import de.realmeze.menu.controller.InventoryController;
import de.realmeze.menu.controller.PerkController;
import de.realmeze.menu.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryMain {

    private Main main;
    private InventoryController inventoryController;
    private PerkController perkController;
    private EnchantingController enchantingController;
    private ExtraController extraController;

    public InventoryMain(Main main) {
        this.main = main;
        registerControllers();
        registerCommands();
        registerListeners();
    }

    private void registerCommands() {
        getMain().getCommand("menu").setExecutor(new MenuCommand(getInventoryController()));
        getMain().getCommand("perk").setExecutor(new PerkCommand(getInventoryController(), getPerkController()));
        getMain().getCommand("goldswitch").setExecutor(new GoldSwitchCommand());
        getMain().getCommand("enchant").setExecutor(new EnchantCommand(getEnchantingController()));
        getMain().getCommand("extra").setExecutor(new ExtraCommand(getInventoryController(), getExtraController()));
        getMain().getCommand("fill").setExecutor(new FillCommand());
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryClickListener(getInventoryController()), getMain());
        pm.registerEvents(new InventoryDragListener(getInventoryController()), getMain());
        pm.registerEvents(new PlayerJoinListener(getPerkController()), getMain());
        pm.registerEvents(new PlayerRespawnListener(getPerkController()), getMain());
        pm.registerEvents(new InventoryCloseListener(getInventoryController(), getEnchantingController()), getMain());
        pm.registerEvents(new PreEnchantListener(getEnchantingController()), getMain());
    }

    private void registerControllers() {
        setInventoryController(new InventoryController(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), " : clickable"));
        setPerkController(new PerkController(new HashMap<>(), new HashMap<>()));
        setEnchantingController(new EnchantingController(new ArrayList<>()));
        setExtraController(new ExtraController(new HashMap<>()));
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public InventoryController getInventoryController() {
        return inventoryController;
    }

    public void setInventoryController(InventoryController inventoryController) { this.inventoryController = inventoryController; }

    public PerkController getPerkController() {
        return perkController;
    }

    public void setPerkController(PerkController perkController) {
        this.perkController = perkController;
    }

    public EnchantingController getEnchantingController() {
        return enchantingController;
    }

    public void setEnchantingController(EnchantingController enchantingController) {
        this.enchantingController = enchantingController;
    }

    public ExtraController getExtraController() {
        return extraController;
    }

    public void setExtraController(ExtraController extraController) {
        this.extraController = extraController;
    }
}
