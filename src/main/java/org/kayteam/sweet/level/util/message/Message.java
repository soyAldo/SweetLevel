package org.kayteam.sweet.level.util.message;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Message {

    private final Player player;
    private final FileConfiguration fileConfiguration;
    private final String path;

    public Message(Player player, FileConfiguration fileConfiguration, String path) {
        this.player = player;
        this.fileConfiguration = fileConfiguration;
        this.path = path;
    }

    public void addColor() {

    }
}
