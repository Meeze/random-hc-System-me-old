package de.realmeze.menu.controller;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class EnchantingController {

    private ArrayList<Player> enchantingPlayers;

    public EnchantingController(ArrayList<Player> enchantingPlayers) {
        this.enchantingPlayers = enchantingPlayers;
    }

    public ArrayList<Player> getEnchantingPlayers() {
        return enchantingPlayers;
    }

    public void setEnchantingPlayers(ArrayList<Player> enchantingPlayers) {
        this.enchantingPlayers = enchantingPlayers;
    }

    public void addEnchantingPlayer(Player player) {
        getEnchantingPlayers().add(player);
    }

    public void removeEnchantingPlayer(Player player) {
        getEnchantingPlayers().remove(player);
    }

    public boolean isEnchanting(Player player) {
        return getEnchantingPlayers().contains(player);
    }

}
