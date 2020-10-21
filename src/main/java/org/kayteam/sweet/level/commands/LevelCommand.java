package org.kayteam.sweet.level.commands;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.kayteam.sweet.level.SweetLevel;
import org.kayteam.sweet.level.commands.tabcompleter.LevelTabCompleter;
import org.kayteam.sweet.level.util.command.SimpleCommand;
import org.kayteam.sweet.level.util.message.ListMessage;
import org.kayteam.sweet.level.util.message.StringMessage;

public class LevelCommand extends SimpleCommand {

    private final SweetLevel sweetLevel;

    public LevelCommand(SweetLevel sweetLevel) {
        super(sweetLevel, "Level", "SweetLevel.Command.Level");
        this.sweetLevel = sweetLevel;
        setTabCompleter(new LevelTabCompleter(sweetLevel));
        FileConfiguration messages = sweetLevel.getMessages().getFileConfiguration();
        setNoPermissionMessage(messages.getString("level.noPermission"));
        setUseConsole(true);
        setUsePlayer(true);
    }

    @Override
    public void reloadActions() {
        FileConfiguration messages = sweetLevel.getMessages().getFileConfiguration();
        setNoPermissionMessage(messages.getString("level.noPermission"));
    }

    @Override
    public boolean onPlayerCommand(Player player, String[] args) {
        FileConfiguration messages = sweetLevel.getMessages().getFileConfiguration();
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "help":
                    ListMessage help = new ListMessage(messages.getStringList("level.help"));
                    help.addColor();
                    help.sendMessage(player);
                    break;
                case "set":
                    if (args.length > 1) {
                        if (args.length > 2) {
                            String playerName = args[1];
                            String levelString = args[2];
                            Player target = sweetLevel.getServer().getPlayerExact(playerName);
                            if (target != null) {
                                try {
                                    int level = Integer.parseInt(levelString);
                                    StringMessage trying = new StringMessage(messages.getString("level.set.trying"));
                                    trying.replaceAll("%level%", levelString);
                                    trying.replaceAll("%player%", playerName);
                                    trying.addColor();
                                    player.sendMessage(trying.getMessage());
                                    if (sweetLevel.getLevelManager().setLevel(target, level)) {
                                        StringMessage completed = new StringMessage(messages.getString("level.set.completed"));
                                        completed.replaceAll("%level%", levelString);
                                        completed.replaceAll("%player%", playerName);
                                        completed.addColor();
                                        player.sendMessage(completed.getMessage());
                                    } else {
                                        StringMessage failed = new StringMessage(messages.getString("level.set.failed"));
                                        failed.replaceAll("%level%", levelString);
                                        failed.replaceAll("%player%", playerName);
                                        failed.addColor();
                                        player.sendMessage(failed.getMessage());
                                    }
                                } catch (NumberFormatException exception) {
                                    StringMessage levelInvalid = new StringMessage(messages.getString("level.levelInvalid"));
                                    levelInvalid.replaceAll("%level%", levelString);
                                    levelInvalid.addColor();
                                    player.sendMessage(levelInvalid.getMessage());
                                }
                            } else {
                                StringMessage playerInvalid = new StringMessage(messages.getString("level.playerInvalid"));
                                playerInvalid.replaceAll("%player%", playerName);
                                playerInvalid.addColor();
                                player.sendMessage(playerInvalid.getMessage());
                            }
                        } else {
                            StringMessage levelEmpty = new StringMessage(messages.getString("level.levelEmpty"));
                            levelEmpty.addColor();
                            player.sendMessage(levelEmpty.getMessage());
                        }
                    } else {
                        StringMessage playerEmpty = new StringMessage(messages.getString("level.playerEmpty"));
                        playerEmpty.addColor();
                        player.sendMessage(playerEmpty.getMessage());
                    }
                case "give":
                    if (args.length > 1) {
                        if (args.length > 2) {
                           String playerName = args[1];
                           String levelsString = args[2];
                           Player target = sweetLevel.getServer().getPlayerExact(playerName);
                           if (target != null) {
                               try {
                                   int levels = Integer.parseInt(levelsString);
                                   StringMessage trying = new StringMessage(messages.getString("level.give.trying"));
                                   trying.replaceAll("%levels%", levelsString);
                                   trying.replaceAll("%player%", playerName);
                                   trying.addColor();
                                   player.sendMessage(trying.getMessage());
                                   if (sweetLevel.getLevelManager().giveLevels(target, levels)) {
                                        StringMessage completed = new StringMessage(messages.getString("level.give.completed"));
                                        completed.replaceAll("%levels%", levelsString);
                                        completed.replaceAll("%player%", playerName);
                                        completed.addColor();
                                        player.sendMessage(completed.getMessage());
                                   } else {
                                       StringMessage failed = new StringMessage(messages.getString("level.give.failed"));
                                       failed.replaceAll("%levels%", levelsString);
                                       failed.replaceAll("%player%", playerName);
                                       failed.addColor();
                                       player.sendMessage(failed.getMessage());
                                   }
                               } catch (NumberFormatException exception) {
                                   StringMessage levelInvalid = new StringMessage(messages.getString("level.levelInvalid"));
                                   levelInvalid.replaceAll("%level%", levelsString);
                                   levelInvalid.addColor();
                                   player.sendMessage(levelInvalid.getMessage());
                               }
                           } else {
                               StringMessage playerInvalid = new StringMessage(messages.getString("level.playerInvalid"));
                               playerInvalid.replaceAll("%player%", playerName);
                               playerInvalid.addColor();
                               player.sendMessage(playerInvalid.getMessage());
                           }
                        } else {
                            StringMessage levelEmpty = new StringMessage(messages.getString("level.levelEmpty"));
                            levelEmpty.addColor();
                            player.sendMessage(levelEmpty.getMessage());
                        }
                    } else {
                        StringMessage playerEmpty = new StringMessage(messages.getString("level.playerEmpty"));
                        playerEmpty.addColor();
                        player.sendMessage(playerEmpty.getMessage());
                    }
                case "take":

                default:
            }
        }
        return true;
    }
}
