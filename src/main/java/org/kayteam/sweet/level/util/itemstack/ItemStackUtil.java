package org.kayteam.sweet.level.util.itemstack;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemStackUtil {

    private static String[] colors = {"WHITE", "ORANGE", "MAGENTA", "LIGHT_BLUE", "YELLOW", "LIME", "PINK", "GRAY", "LIGHT_GRAY", "CYAN", "PURPLE", "BLUE", "BROWN", "GREEN", "RED", "BLACK"};

    public static ItemStack getStainedGlassPane(String color) {
        if (color.equals("NORMAL")) {
            if (Material.getMaterial("STAINED_GLASS_PANE") == null) {
                return new ItemStack(160);
            } else {
                return new ItemStack(Material.getMaterial("STAINED_GLASS_PANE"));
            }
        }
        if (Material.getMaterial(color + "STAINED_GLASS_PANE") == null) {
            int id = -1;
            for (int i = 0; i< colors.length;i++) {
                if (colors[i].equals(color)) {
                    id = i;
                }
            }
            return new ItemStack(160, 1, (short) 1, (byte) id);
        } else {
            return new ItemStack(Material.getMaterial("STAINED_GLASS_PANE"));
        }
    }
}
