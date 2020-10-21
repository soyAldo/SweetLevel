package org.kayteam.sweet.level.levelmanager;

import org.bukkit.entity.Player;
import org.kayteam.sweet.level.SweetLevel;
import org.kayteam.sweet.level.events.*;
import org.kayteam.sweet.level.playerdata.PlayerData;

public class LevelManager {

    private final SweetLevel sweetLevel;

    public LevelManager(SweetLevel sweetLevel) {
        this.sweetLevel = sweetLevel;
    }

    public int getLevel(Player player) {
        PlayerData playerData = sweetLevel.getPlayerDataManager().getPlayerData(player.getUniqueId());
        return playerData.getLevel();
    }

    public boolean setLevel(Player player, int level) {
        PlayerData playerData = sweetLevel.getPlayerDataManager().getPlayerData(player.getUniqueId());
        int currentLevel = playerData.getLevel();
        if (currentLevel > level) {
            return giveLevels(player, level - currentLevel);
        } else {
            return takeLevels(player, currentLevel - level);
        }
    }

    public boolean giveLevels(Player player, int levels) {
        PlayerData playerData = sweetLevel.getPlayerDataManager().getPlayerData(player.getUniqueId());
        PlayerGiveLevelEvent event = new PlayerGiveLevelEvent(player, playerData.getLevel(), levels);
        sweetLevel.getServer().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            if (event.getGivenLevels() > 0) {
                playerData.setLevel(event.getCurrentLevel() + event.getGivenLevels());
                return true;
            }
        }
        return false;
    }

    public boolean takeLevels(Player player, int levels) {
        PlayerData playerData = sweetLevel.getPlayerDataManager().getPlayerData(player.getUniqueId());
        PlayerTakeLevelEvent event = new PlayerTakeLevelEvent(player, playerData.getLevel(), levels);
        sweetLevel.getServer().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            if (event.getCurrentLevel() > event.getTakenLevel()) {
                playerData.setLevel(event.getCurrentLevel() - event.getTakenLevel());
                return true;
            }
        }
        return false;
    }

    public double getExperience(Player player) {
        PlayerData playerData = sweetLevel.getPlayerDataManager().getPlayerData(player.getUniqueId());
        return playerData.getExperience();
    }

    public boolean setExperience(Player player, double experience) {
        PlayerData playerData = sweetLevel.getPlayerDataManager().getPlayerData(player.getUniqueId());
        double currentExperience = playerData.getExperience();
        if (currentExperience > experience) {
            return takeExperience(player, currentExperience - experience);
        } else {
            return giveExperience(player, experience - currentExperience);
        }
    }

    public boolean giveExperience(Player player, double experience) {
        PlayerData playerData = sweetLevel.getPlayerDataManager().getPlayerData(player.getUniqueId());
        PlayerGiveExperienceEvent event = new PlayerGiveExperienceEvent(player, playerData.getExperience(), experience);
        sweetLevel.getServer().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            if (experience > 0) {
                playerData.setExperience(event.getCurrentExperience() + event.getGiveExperience());
                return true;
            }
        }
        return false;
    }

    public boolean takeExperience(Player player, double experience) {
        PlayerData playerData = sweetLevel.getPlayerDataManager().getPlayerData(player.getUniqueId());
        PlayerTakeExperienceEvent event = new PlayerTakeExperienceEvent(player, playerData.getExperience(), experience);
        sweetLevel.getServer().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            if (experience > 0) {
                playerData.setExperience(event.getCurrentExperience() - event.getTakeExperience());
                return true;
            }
        }
        return false;
    }

}