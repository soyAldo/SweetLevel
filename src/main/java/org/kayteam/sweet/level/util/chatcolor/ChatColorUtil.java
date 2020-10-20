package org.kayteam.sweet.level.util.chatcolor;

import org.bukkit.ChatColor;

public class ChatColorUtil {

    private static final String[] colors = {"dark_blue", "dark_green", "dark_aqua", "dark_red", "dark_purple", "gold", "gray",
            "dark_gray", "blue", "black", "green", "aqua", "red", "light_purple", "yellow", "white"};
    private static final String[] codes = {"&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9", "&0", "&a", "&b", "&c", "&d", "&e", "&f"};

    /*
    &k : magic
    &l : bold
    &m : strike
    &n : underline
    &o : itallic
    &r : reset
     */
    private static final String[] formats = {"&k", "&l", "&m", "&n", "&o", "r"};

    /**
     * Get the color code from color name.
     * @param color - The color name.
     * @return code - The color code.
     */
    public static String getCode(String color) {
        for (int i = 0; i < colors.length; i ++) {
            if (color.equals(colors[i])) {
                return codes[i];
            }
        }
        return null;
    }

    public static boolean isCode(String code) {
        for (String c:codes) {
            if (code.equals(c)) {
                return true;
            }
        }
        return false;
    }

    public static String getColor(String code) {
        for (int i = 0; i < codes.length; i ++) {
            if (code.equals(codes[i])) {
                return colors[i];
            }
        }
        return null;
    }

    public static boolean isColor(String color) {
        for (String c:colors) {
            if (color.equals(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if a text contain color codes.
     * @param text - The text to evaluate.
     * @return true if the text contain color codes or false if no.
     */
    public static boolean containColorCodes(String text) {
        for (String code : codes) {
            if (text.contains(code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if a text contain format codes.
     * @param text - The text to evaluate.
     * @return true if contain format codes or false if no.
     */
    public static boolean containFormatCodes(String text) {
        for (String format : formats) {
            if (text.contains(format)) {
                return true;
            }
        }
        return false;
    }

    public static String addColorOnlyColors(String text) {
        for (String color : colors) {
            text = text.replaceAll(color, ChatColor.translateAlternateColorCodes('&', color));
        }
        return text;
    }

    public static String addColorOnlyFormats(String text) {
        for (String format : formats) {
            text = text.replaceAll(format, ChatColor.translateAlternateColorCodes('&', format));
        }
        return text;
    }

    public static String addColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String removeColor(String text) {
        return ChatColor.stripColor(text);
    }
}
