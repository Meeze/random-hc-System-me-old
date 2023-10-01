package de.realmeze.menu.listener;

import de.realmeze.menu.controller.PerkController;
import de.realmeze.menu.model.Perk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.ArrayList;

public class PlayerRespawnListener implements Listener {

    private PerkController perkController;

    public PlayerRespawnListener(PerkController perkController) {
        this.perkController = perkController;
    }

    public PerkController getPerkController() {
        return perkController;
    }

    public void setPerkController(PerkController perkController) {
        this.perkController = perkController;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        ArrayList<Perk> perksToActivate = getPerkController().getActivePerks(player);
        for (Perk perkToActivate: perksToActivate) {
            getPerkController().deactivatePerk(player, perkToActivate);
            getPerkController().activatePerk(player, perkToActivate);
        }
    }
}

