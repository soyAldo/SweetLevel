package org.kayteam.sweet.level.util.placeholderapi;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlaceholderAPIUtil {

    public static String addPlaceholders(Player player, String text){
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            text = PlaceholderAPI.setPlaceholders(player, text);
        }
        return text;
    }

    public static String addPlaceholders(OfflinePlayer player, String text){
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            text = PlaceholderAPI.setPlaceholders(player, text);
        }
        return text;
    }

    public static boolean registerExpansion(PlaceholderExpansion placeholderExpansion) {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            return placeholderExpansion.register();
        }
        return false;
    }

}
