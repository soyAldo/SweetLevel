package org.kayteam.sweet.level.storage;

import org.kayteam.sweet.level.storage.enums.StorageType;
import java.util.UUID;

public abstract class Storage {

    private final StorageType storageType;

    public Storage(StorageType storageType) {
        this.storageType = storageType;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    public abstract int getLevel(UUID uuid);

    public abstract void setLevel(UUID uuid, int level);

    public abstract double getExperience(UUID uuid);

    public abstract void setExperience(UUID uuid, double experience);
}
