package de.realmeze.menu.listener;

import de.realmeze.menu.controller.PerkController;
import de.realmeze.menu.model.Perk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerJoinListener implements Listener {

    private PerkController perkController;

    public PlayerJoinListener(PerkController perkController) {
        this.perkController = perkController;
    }

    public PerkController getPerkController() {
        return perkController;
    }

    public void setPerkController(PerkController perkController) {
        this.perkController = perkController;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        getPerkController().getActivePerksForPlayer().put(player.getUniqueId(), new ArrayList<>());
        Collection<PotionEffect> activeEffects = player.getActivePotionEffects();
        for (PotionEffect activeEffect: activeEffects) {
            if(activeEffect.getDuration() > 10000) {
                for (Perk perk: getPerkController().getPerks().values()) {
                    if(perk.getEffectType().equals(activeEffect.getType())) {
                        if(!player.hasPermission("lionpvp.perk." + perk.getName())){
                            player.sendMessage("Es scheint du hattest das Perk: " + perk.getName() + " aktiviert jedoch keine Rechte dazu, pls tell an admin this is a bug!");
                        }
                        if(getPerkController().activatePerk(player, perk)){
                            player.sendMessage("Das Perk: " + perk.getName() + " wurde reaktivert!");
                        }
                    }
                }
            }
        }
    }
}
