/*
 * Copyright (C) 2020 sir_oswaldo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kayteam.sweet.level.util.yaml;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class Yaml {

    private final JavaPlugin javaPlugin;
    private final String dir;
    private final String name;
    private File file;
    private FileConfiguration fileConfiguration;

    public Yaml(JavaPlugin javaPlugin, String name) {
        this.javaPlugin = javaPlugin;
        this.dir = javaPlugin.getDataFolder().getPath();
        this.name = name;
    }

    public Yaml(JavaPlugin javaPlugin, String dir, String name){
        this.javaPlugin = javaPlugin;
        this.dir = javaPlugin.getDataFolder().getPath() + File.separator + dir;
        this.name = name;
    }


    public boolean existFileConfiguration(){
        file = new File(dir, name);
        return file.exists();
    }

    /**
     * Borrar el archivo
     */
    public void deleteFileConfiguration(){
        file = new File(dir, name);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Obtener el archivo de configuracion
     * @return fileConfiguration
     */
    public FileConfiguration getFileConfiguration() {
        if (fileConfiguration == null) {
            reloadFileConfiguration();
        }
        return fileConfiguration;
    }
    
    /**
     * Recarga el archivo
     */
    public void reloadFileConfiguration() {
        if (fileConfiguration == null) {
            file = new File(dir, name);
            File dirFile = new File(dir);
            if (dirFile.exists()){
                dirFile.mkdir();
                try{
                    file.createNewFile();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (javaPlugin.getResource(name) != null){
            Reader defConfigStream = new InputStreamReader(javaPlugin.getResource(name), StandardCharsets.UTF_8);
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            fileConfiguration.setDefaults(defConfig);
        }
    }

    /**
     * Guarda el archivo
     */
    public void saveFileConfiguration() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            javaPlugin.getLogger().log(Level.SEVERE, "No se pudo guardar el archivo.");
        }
    }

    /**
     * Registra el archivo
     */
    public void registerFileConfiguration() {
        file = new File(dir, name);
        if (!file.exists()) {
            getFileConfiguration().options().copyDefaults(true);
            saveFileConfiguration();
        }
    }
}

