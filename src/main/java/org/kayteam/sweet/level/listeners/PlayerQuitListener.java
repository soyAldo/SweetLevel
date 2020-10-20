package org.kayteam.sweet.level.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.kayteam.sweet.level.SweetLevel;

import java.util.UUID;

public class PlayerQuitListener implements Listener {

    private final SweetLevel sweetLevel;

    public PlayerQuitListener(SweetLevel sweetLevel) {
        this.sweetLevel = sweetLevel;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        sweetLevel.getPlayerDataManager().unloadPlayerData(uuid);
    }
}
