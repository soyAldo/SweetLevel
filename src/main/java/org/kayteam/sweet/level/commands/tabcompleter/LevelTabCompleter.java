package org.kayteam.sweet.level.commands.tabcompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.kayteam.sweet.level.SweetLevel;

import java.util.ArrayList;
import java.util.List;

public class LevelTabCompleter implements TabCompleter {

    private final SweetLevel sweetLevel;

    public LevelTabCompleter(SweetLevel sweetLevel) {
        this.sweetLevel = sweetLevel;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 0) {
            List<String> names = new ArrayList<>();
            for (Player player:sweetLevel.getServer().getOnlinePlayers()) {
                names.add(player.getName());
            }
            return names;
        }
        return new ArrayList<>();
    }

}
