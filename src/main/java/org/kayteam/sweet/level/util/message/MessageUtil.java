package org.kayteam.sweet.level.util.message;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class MessageUtil {

    public static void sendMessage(Player player, FileConfiguration fileConfiguration, String path) {
        if (fileConfiguration.contains(path)) {
            if (fileConfiguration.isString(path)) {
                StringMessage message = new StringMessage(fileConfiguration.getString(path));
                message.addColor();
                player.sendMessage(message.getMessage());
            } else if (fileConfiguration.isList(path)) {
                ListMessage message = new ListMessage(fileConfiguration.getStringList(path));
                message.addColor();
                message.sendMessage(player);
            }
        }
    }
}
