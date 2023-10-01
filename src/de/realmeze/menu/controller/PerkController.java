package de.realmeze.menu.controller;

import de.pooky.MySQL.Coin_API;
import de.realmeze.menu.model.Perk;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PerkController {

    private HashMap<String, Perk> perks;
    private HashMap<UUID, ArrayList<Perk>> activePerksForPlayer;

    public PerkController(HashMap<String, Perk> perks, HashMap<UUID, ArrayList<Perk>> activePerksForPlayer) {
        this.perks = perks;
        this.activePerksForPlayer = activePerksForPlayer;
        registerPerks();
    }

    public HashMap<String, Perk> getPerks() {
        return perks;
    }

    public void setPerks(HashMap<String, Perk> perks) {
        this.perks = perks;
    }

    public HashMap<UUID, ArrayList<Perk>> getActivePerksForPlayer() {
        return activePerksForPlayer;
    }

    public void setActivePerksForPlayer(HashMap<UUID, ArrayList<Perk>> activePerksForPlayer) {
        this.activePerksForPlayer = activePerksForPlayer;
    }

    private void registerPerk(Perk perk) {
        getPerks().put(perk.getName(), perk);
    }

    private void registerPerks() {
        Perk speedPerk = new Perk("speed", 2, 0, PotionEffectType.SPEED);
        Perk strengthPerk = new Perk("strength", 2, 0, PotionEffectType.INCREASE_DAMAGE);
        Perk jumpPerk = new Perk("jump", 2, 0, PotionEffectType.JUMP);
        Perk anti_firePerk = new Perk("antifire", 1, 0, PotionEffectType.FIRE_RESISTANCE);
        Perk no_hungerPerk = new Perk("nohunger", 1, 0, PotionEffectType.SATURATION);
        Perk night_visionPerk = new Perk("nightvision", 1, 0, PotionEffectType.NIGHT_VISION);
        registerPerk(speedPerk);
        registerPerk(strengthPerk);
        registerPerk(jumpPerk);
        registerPerk(anti_firePerk);
        registerPerk(no_hungerPerk);
        registerPerk(night_visionPerk);
    }

    public boolean exists(String perkName) {
        return getPerks().containsKey(perkName);
    }

    public Perk getPerk(String perkName) {
        return getPerks().get(perkName);
    }

    public ArrayList<Perk> getActivePerks(Player player) {
        return getActivePerksForPlayer().get(player.getUniqueId());
    }

    public boolean activatePerk(Player player, Perk perk) {
        if(getActivePerks(player).contains(perk)){
            return false;
        }
        getActivePerks(player).add(perk);
        player.addPotionEffect(new PotionEffect(perk.getEffectType(), Integer.MAX_VALUE, perk.getModifier() - 1));
        return true;
    }

    public boolean deactivatePerk(Player player, Perk perk) {
        if(!getActivePerks(player).contains(perk)) {
            return false;
        }
        player.removePotionEffect(perk.getEffectType());
        getActivePerks(player).remove(perk);
        return true;
    }

    public boolean buyPerk(Player player, Perk perk) {
        if (Coin_API.canPurchase(player, (int) perk.getBuyPrice())){
            Coin_API.removeCoins(player, (int) perk.getBuyPrice());
            PermissionUser user = PermissionsEx.getUser(player);
            user.addPermission("lionpvp.perk." + perk.getName());
            return true;
        } else {
            return false;
        }
    }

}
