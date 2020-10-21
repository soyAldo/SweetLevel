package org.kayteam.sweet.level.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerGiveExperienceEvent extends Event implements Cancellable {

    public static HandlerList handlerList = new HandlerList();
    private boolean cancel = false;

    private final Player player;
    private final double currentExperience;
    private double giveExperience;

    public PlayerGiveExperienceEvent(Player player, double currentExperience, double giveExperience) {
        this.player = player;
        this.currentExperience = currentExperience;
        this.giveExperience = giveExperience;
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

    public double getCurrentExperience() {
        return currentExperience;
    }

    public double getGiveExperience() {
        return giveExperience;
    }

    public void setGiveExperience(double giveExperience) {
        this.giveExperience = giveExperience;
    }

}

