package org.kayteam.sweet.level.util.plugin;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.sweet.level.util.command.SimpleCommand;
import org.kayteam.sweet.level.util.yaml.Yaml;

import java.util.HashMap;

public class SimplePlugin extends JavaPlugin {

    private final HashMap<String, SimpleCommand> commands = new HashMap<>();
    private final HashMap<String, Yaml> yamls = new HashMap<>();

    public SimplePlugin() {
    }

    private void sendMessage(String message){
        getServer().getConsoleSender().sendMessage(message);
    }

    public void addCommand(SimpleCommand simpleCommand){
        commands.put(simpleCommand.getCommand(), simpleCommand);
    }
    public void registerCommands(){
        for (String command:commands.keySet()) {
            (commands.get(command)).registerCommand();
        }
    }

    public void registerListener(Listener listener){
        getServer().getPluginManager().registerEvents(listener, this);
    }
    public void registerYaml(String name, Yaml yaml){
        yamls.put(name, yaml);
        yaml.registerFileConfiguration();
    }
    public Yaml getYaml(String name){
        if (yamls.containsKey(name)){
            return yamls.get(name);
        }
        sendMessage("No se pudo obtener el archivo : " + name + ".yml");
        return null;
    }
}
