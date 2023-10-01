package de.realmeze.menu.model;

public class ExtraCmd {

    private String name;
    private long buyPrice;
    private String permission;

    public ExtraCmd(String name, long buyPrice, String permission) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(long buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
