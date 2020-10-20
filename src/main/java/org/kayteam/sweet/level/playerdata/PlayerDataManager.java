package org.kayteam.sweet.level.playerdata;

import org.kayteam.sweet.level.SweetLevel;
import org.kayteam.sweet.level.storage.Storage;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataManager {

    private final SweetLevel sweetLevel;
    private final HashMap<UUID, PlayerData> dataHashMap = new HashMap<>();

    public PlayerDataManager(SweetLevel sweetLevel) {
        this.sweetLevel = sweetLevel;
    }

    public void loadPlayerData(UUID uuid) {
        Storage storage = sweetLevel.getStorage();
        PlayerData playerData = new PlayerData(uuid);
        playerData.setLevel(storage.getLevel(uuid));
        playerData.setExperience(storage.getExperience(uuid));
        dataHashMap.put(uuid, playerData);
    }

    public void savePlayerData(UUID uuid) {
        Storage storage = sweetLevel.getStorage();
        PlayerData playerData = dataHashMap.get(uuid);
        storage.setLevel(uuid, playerData.getLevel());
        storage.setExperience(uuid, playerData.getExperience());
    }

    public void unloadPlayerData(UUID uuid) {
        savePlayerData(uuid);
        dataHashMap.remove(uuid);
    }

    public PlayerData getPlayerData(UUID uuid) {
        if (!dataHashMap.containsKey(uuid)) {
            loadPlayerData(uuid);
        }
        return dataHashMap.get(uuid);
    }

}
