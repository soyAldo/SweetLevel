package org.kayteam.sweet.level.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerTakeLevelEvent extends Event implements Cancellable {

    public static HandlerList handlerList = new HandlerList();
    private boolean cancel = false;

    private final Player player;
    private final int currentLevel;
    private int takenLevel;

    public PlayerTakeLevelEvent(Player player, int currentLevel, int takenLevel) {
        this.player = player;
        this.currentLevel = currentLevel;
        this.takenLevel = takenLevel;

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

    public int getTakenLevel() {
        return takenLevel;
    }

    public void setTakenLevel(int takenLevel) {
        this.takenLevel = takenLevel;
    }
}
