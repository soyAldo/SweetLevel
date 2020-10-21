package org.kayteam.sweet.level;

import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.sweet.level.levelmanager.LevelManager;
import org.kayteam.sweet.level.listeners.PlayerJoinListener;
import org.kayteam.sweet.level.listeners.PlayerQuitListener;
import org.kayteam.sweet.level.playerdata.PlayerDataManager;
import org.kayteam.sweet.level.storage.MySqlStorage;
import org.kayteam.sweet.level.storage.Storage;
import org.kayteam.sweet.level.storage.YamlStorage;
import org.kayteam.sweet.level.util.yaml.Yaml;

public class SweetLevel extends JavaPlugin {


    Yaml settings = new Yaml(this, "settings.yml");
    Yaml messages = new Yaml(this, "messages.yml");

    private Storage storage;

    private final PlayerDataManager playerDataManager = new PlayerDataManager(this);

    private final LevelManager levelManager = new LevelManager(this);

    public static API api;

    // Demons Kings
    @Override
    public void onEnable() {
        registerFiles();
        registerCommands();
        registerListeners();
        String storageType = settings.getFileConfiguration().getString("storageType");
        if (storageType.equals("MYSQL")){
            storage = new MySqlStorage(this);
            ((MySqlStorage) storage).setupMySQL();
        } else if (storageType.equals("YAML")){
            storage = new YamlStorage(this);
        } else {
            storage = new YamlStorage(this);
        }
        api = new API(this);
    }

    public void reload() {
        settings.reloadFileConfiguration();
        messages.reloadFileConfiguration();
    }

    private void registerFiles() {
        settings.registerFileConfiguration();
        messages.registerFileConfiguration();
    }

    private void registerCommands() {

    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
    }

    public Yaml getSettings() {
        return settings;
    }

    public Yaml getMessages() {
        return messages;
    }

    public Storage getStorage() {
        return storage;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    public static API getApi() {
        return api;
    }
}
