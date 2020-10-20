package org.kayteam.sweet.level.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.kayteam.sweet.level.SweetLevel;

import java.util.UUID;

public class PlayerJoinListener implements Listener {

    private final SweetLevel sweetLevel;

    public PlayerJoinListener(SweetLevel sweetLevel) {
        this.sweetLevel = sweetLevel;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        sweetLevel.getPlayerDataManager().loadPlayerData(uuid);
    }
}
