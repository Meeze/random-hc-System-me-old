package de.realmeze.menu.controller;

import de.pooky.MySQL.Coin_API;
import de.realmeze.menu.model.ExtraCmd;
import de.realmeze.menu.model.Perk;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.HashMap;

public class ExtraController {

    private HashMap<String, ExtraCmd> extraCommands;

    public ExtraController(HashMap<String, ExtraCmd> extraCommands) {
        this.extraCommands = extraCommands;
        registerExtraCommands();
    }

    public HashMap<String, ExtraCmd> getExtraCommands() {
        return extraCommands;
    }

    public void setExtraCommands(HashMap<String, ExtraCmd> extraCommands) {
        this.extraCommands = extraCommands;
    }

    private void registerExtraCommand(String key, ExtraCmd extraCmd) {
        getExtraCommands().put(key, extraCmd);
    }

    private void registerExtraCommands() {
        ExtraCmd goldswitch = new ExtraCmd("goldswitch", 0, "lionpvp.extracmd.goldswitch");
        ExtraCmd fill = new ExtraCmd("fill", 0, "lionpvp.extracmd.fill");
        ExtraCmd enchant = new ExtraCmd("enchant", 0, "lionpvp.extracmd.enchant");
        registerExtraCommand(goldswitch.getName(), goldswitch);
        registerExtraCommand(fill.getName(), fill);
        registerExtraCommand(enchant.getName(), enchant);
    }

    public boolean existExtraCommand(String extraKey) {
        return getExtraCommands().containsKey(extraKey);
    }

    public ExtraCmd getCommand(String cmdName) {
        return getExtraCommands().get(cmdName);
    }

    public boolean purchaseCommand(Player player, ExtraCmd extraCmd){
        if (Coin_API.canPurchase(player, (int) extraCmd.getBuyPrice())){
            Coin_API.removeCoins(player, (int) extraCmd.getBuyPrice());
            PermissionUser user = PermissionsEx.getUser(player);
            user.addPermission("lionpvp.perk." + extraCmd.getName());
            return true;
        } else {
            return false;
        }
    }

}
