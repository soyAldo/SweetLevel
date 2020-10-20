package org.kayteam.sweet.level.util.command;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.sweet.level.util.chatcolor.ChatColorUtil;
import org.kayteam.sweet.level.util.message.StringMessage;

public class SimpleCommand implements CommandExecutor{

    private final JavaPlugin javaPlugin;

    private final String command;
    private final String permission;
    private TabCompleter tabCompleter;

    private boolean useConsole = false;
    private boolean usePlayer = false;

    private String consoleNoCanUseMessage = "&cOnly can execute this command from Console.";
    private String playerNoCanUseMessage = "&cOnly can execute this command from Players.";
    private String noPermissionMessage = "&cYou do not have permission to execute this command.";

    public SimpleCommand(JavaPlugin javaPlugin, String command, String permission) {
        this.javaPlugin = javaPlugin;
        this.command = command;
        this.permission = permission;
    }

    public void reloadActions(){}

    public void reloadCommand() {
        reloadActions();
        PluginCommand pluginCommand = javaPlugin.getCommand(command);
        pluginCommand.setPermission(permission);
        pluginCommand.setTabCompleter(tabCompleter);
        pluginCommand.setPermissionMessage(ChatColorUtil.addColor(noPermissionMessage));
    }

    /**
     * Get the command.
     * @return The Command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Get the permission of this command.
     * @return The permission.
     */
    public String getPermission() {
        return permission;
    }

    public void setTabCompleter(TabCompleter tabCompleter) {
        this.tabCompleter = tabCompleter;
    }

    public TabCompleter getTabCompleter() {
        return tabCompleter;
    }

    /**
     * Register the command.
     */
    public void registerCommand(){
        PluginCommand pluginCommand = javaPlugin.getCommand(command);
        if (pluginCommand != null) {
            pluginCommand.setExecutor(this);
            pluginCommand.setTabCompleter(tabCompleter);
            String message_commandRegistered = "&6The command &f%command% &6has been registered correctly.";
            StringMessage commandRegisteredMessage = new StringMessage(message_commandRegistered);
            commandRegisteredMessage.replaceAll("%command%", command);
            javaPlugin.getServer().getConsoleSender().sendMessage(commandRegisteredMessage.addColor());
        } else {
            // Messages
            String registerError = "&cThe command &f%command% &cno found in the plugin.yml!";
            StringMessage registerErrorMessage = new StringMessage(registerError);
            registerErrorMessage.replaceAll("%command%", command);
            javaPlugin.getServer().getConsoleSender().sendMessage(registerErrorMessage.addColor());
        }
    }

    public void setNoPermissionMessage(String message_noPermission) {
        this.noPermissionMessage = message_noPermission;
    }

    public void setConsoleNoCanUseMessage(String message_onlyConsole) {
        this.consoleNoCanUseMessage = message_onlyConsole;
    }

    public void setPlayerNoCanUseMessage(String message_onlyPlayer) {
        this.playerNoCanUseMessage = message_onlyPlayer;
    }

    public void setUseConsole(boolean useConsole) {
        this.useConsole = useConsole;
    }

    public void setUsePlayer(boolean usePlayer) {
        this.usePlayer = usePlayer;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (useConsole) {
            if (sender instanceof ConsoleCommandSender) {
                return onConsoleCommand((ConsoleCommandSender) sender, args);
            } else if (!usePlayer){
                StringMessage onlyConsoleMessage = new StringMessage(consoleNoCanUseMessage);
                sender.sendMessage(onlyConsoleMessage.addColor());
                return true;
            }
        }
        if (usePlayer) {
            if (sender instanceof Player) {
                if (sender.hasPermission(permission)) {
                    return onPlayerCommand((Player) sender, args);
                } else {
                    StringMessage noPermissionMessage = new StringMessage(this.noPermissionMessage);
                    sender.sendMessage(noPermissionMessage.addColor());
                }
            } else {
                StringMessage onlyPlayerMessage = new StringMessage(playerNoCanUseMessage);
                sender.sendMessage(onlyPlayerMessage.addColor());
                return true;
            }
        }
        return true;
    }

    /**
     * This method is used when the one that sent the command is the console.
     * @param console - The console.
     * @param args - The arguments.
     * @return true if the command has been used correctly if no is false.
     */
    public boolean onConsoleCommand(ConsoleCommandSender console, String[] args) {
        return false;
    }

    /**
     * This method is used when the one that sent the command is a player.
     * @param player - The player.
     * @param args - The arguments.
     * @return true if the command has been used correctly if no is false.
     */
    public boolean onPlayerCommand(Player player, String[] args){
        return false;
    }
}
