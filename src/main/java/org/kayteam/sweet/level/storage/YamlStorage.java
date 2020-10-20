package org.kayteam.sweet.level.storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.kayteam.sweet.level.SweetLevel;
import org.kayteam.sweet.level.storage.enums.StorageType;
import org.kayteam.sweet.level.util.yaml.Yaml;

import java.util.UUID;

public class YamlStorage extends Storage{

    private final SweetLevel sweetLevel;

    public YamlStorage(SweetLevel sweetLevel) {
        super(StorageType.YAML);
        this.sweetLevel = sweetLevel;
    }

    @Override
    public int getLevel(UUID uuid) {
        Yaml yaml = new Yaml(sweetLevel, "storage", uuid + ".yml");
        yaml.registerFileConfiguration();
        FileConfiguration fileConfiguration = yaml.getFileConfiguration();
        if (fileConfiguration.contains("Level")) {
            if (fileConfiguration.isInt("Level")) {
                return fileConfiguration.getInt("Level");
            }
        }
        return 0;
    }

    @Override
    public void setLevel(UUID uuid, int level) {
        Yaml yaml = new Yaml(sweetLevel, "storage", uuid + ".yml");
        yaml.registerFileConfiguration();
        FileConfiguration fileConfiguration = yaml.getFileConfiguration();
        fileConfiguration.set("Level", level);
        yaml.saveFileConfiguration();
    }

    @Override
    public double getExperience(UUID uuid) {
        Yaml yaml = new Yaml(sweetLevel, "storage", uuid + ".yml");
        yaml.registerFileConfiguration();
        FileConfiguration fileConfiguration = yaml.getFileConfiguration();
        if (fileConfiguration.contains("Experience")) {
            if (fileConfiguration.isDouble("Experience")) {
                return fileConfiguration.getDouble("Experience");
            }
        }
        return 0;
    }

    @Override
    public void setExperience(UUID uuid, double experience) {
        Yaml yaml = new Yaml(sweetLevel, "storage", uuid + ".yml");
        yaml.registerFileConfiguration();
        FileConfiguration fileConfiguration = yaml.getFileConfiguration();
        fileConfiguration.set("Experience", experience);
        yaml.saveFileConfiguration();
    }
}
