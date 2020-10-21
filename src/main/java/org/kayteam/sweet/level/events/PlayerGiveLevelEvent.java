package org.kayteam.sweet.level.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.kayteam.sweet.level.playerdata.PlayerData;

public class PlayerGiveLevelEvent extends Event implements Cancellable {

    public static HandlerList handlerList = new HandlerList();
    private boolean cancel = false;

    private final Player player;
    private final int currentLevel;
    private int givenLevels;

    public PlayerGiveLevelEvent(Player player, int currentLevel, int givenLevels) {
        this.player = player;
        this.currentLevel = currentLevel;
        this.givenLevels = givenLevels;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    public Player getPlayer() {
        return player;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getGivenLevels() {
        return givenLevels;
    }

    public void setGivenLevels(int givenLevels) {
        this.givenLevels = givenLevels;
    }
}
