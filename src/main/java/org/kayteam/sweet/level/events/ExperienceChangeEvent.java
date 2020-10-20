package org.kayteam.sweet.level.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ExperienceChangeEvent extends Event implements Cancellable {

    public static HandlerList handlerList = new HandlerList();
    private boolean cancel;

    private final Player player;
    private final double currentExperience;
    private final double newExperience;

    public ExperienceChangeEvent(Player player, double currentExperience, double newExperience) {
        this.player = player;
        this.currentExperience = currentExperience;
        this.newExperience = newExperience;
    }

    public Player getPlayer() {
        return player;
    }

    public double getCurrentExperience() {
        return currentExperience;
    }

    public double getNewExperience() {
        return newExperience;
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
}
