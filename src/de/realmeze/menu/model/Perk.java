package de.realmeze.menu.model;

import org.bukkit.potion.PotionEffectType;

public class Perk {
    private String name;
    private int modifier;
    private long buyPrice;
    private PotionEffectType effectType;

    public Perk(String name, int modifier, long buyPrice, PotionEffectType effectType) {
        this.name = name;
        this.modifier = modifier;
        this.buyPrice = buyPrice;
        this.effectType = effectType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public long getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(long buyPrice) {
        this.buyPrice = buyPrice;
    }

    public PotionEffectType getEffectType() {
        return effectType;
    }

    public void setEffectType(PotionEffectType effectType) {
        this.effectType = effectType;
    }
}
