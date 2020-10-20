package org.kayteam.sweet.level;

import org.bukkit.entity.Player;
import org.kayteam.sweet.level.events.ExperienceChangeEvent;
import org.kayteam.sweet.level.events.LevelChangeEvent;
import org.kayteam.sweet.level.playerdata.PlayerData;

public class SweetLevelAPI {

    private static SweetLevel sweetLevel;

    public SweetLevelAPI(SweetLevel sweetLevel) {
        SweetLevelAPI.sweetLevel = sweetLevel;
    }

    /**
     * Get a player's level.
     * @param player - The player whose level you want to know.
     * @return The level;
     */
    public static int getLevel(Player player) {
        PlayerData playerData = sweetLevel.getPlayerDataManager().getPlayerData(player.getUniqueId());
        return playerData.getLevel();
    }

    /**
     * Set the level of a player.
     * @param player - The player whose level will be changed.
     * @param level - The new level.
     * @return true if the level was set or false if not.
     */
    public static boolean setLevel(Player player, int level) {
        PlayerData playerData = sweetLevel.getPlayerDataManager().getPlayerData(player.getUniqueId());
        LevelChangeEvent levelChangeEvent = new LevelChangeEvent(player, playerData.getLevel(), level);
        if (levelChangeEvent.isCancelled()) {
            return false;
        }
        playerData.setLevel(levelChangeEvent.getNewLevel());
        return true;
    }

    /**
     * Get the experience of a player.
     * @param player - The player we want to know about the experience.
     * @return The experience.
     */
    public static double getExperience(Player player) {
        PlayerData playerData = sweetLevel.getPlayerDataManager().getPlayerData(player.getUniqueId());
        return playerData.getExperience();
    }

    /**
     * Set the new single player experience.
     * @param player - The player whose experience will be changed.
     * @param experience - The new experience.
     * @return true if the experience was established or false if not.
     */
    public static boolean setExperience(Player player, double experience) {
        PlayerData playerData = sweetLevel.getPlayerDataManager().getPlayerData(player.getUniqueId());
        ExperienceChangeEvent levelChangeEvent = new ExperienceChangeEvent(player, playerData.getExperience(), experience);
        if (levelChangeEvent.isCancelled()) {
            return false;
        }
        playerData.setExperience(levelChangeEvent.getNewExperience());
        return true;
    }
}
