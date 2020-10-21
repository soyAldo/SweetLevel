package org.kayteam.sweet.level;


import org.bukkit.entity.Player;

public class API {

    private final SweetLevel sweetLevel;

    public API(SweetLevel sweetLevel) {
        this.sweetLevel = sweetLevel;
    }

    public int getLevel(Player player) {
        return sweetLevel.getLevelManager().getLevel(player);
    }

    public boolean setLevel(Player player, int level) {
        return sweetLevel.getLevelManager().setLevel(player, level);
    }

    public boolean giveLevels(Player player, int levels) {
        return sweetLevel.getLevelManager().giveLevels(player, levels);
    }

    public boolean takeLevels(Player player, int levels) {
        return sweetLevel.getLevelManager().takeLevels(player, levels);
    }

    public double getExperience(Player player) {
        return sweetLevel.getLevelManager().getExperience(player);
    }

    public boolean setExperience(Player player, double experience) {
        return sweetLevel.getLevelManager().setExperience(player, experience);
    }

    public boolean giveExperience(Player player, double experience) {
        return sweetLevel.getLevelManager().giveExperience(player, experience);
    }

    public boolean takeExperience(Player player, double experience) {
        return sweetLevel.getLevelManager().takeExperience(player, experience);
    }

}
