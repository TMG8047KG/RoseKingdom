package me.rosekingdom.rosekingdom.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerData {
    UUID uuid;
    File file;
    FileConfiguration configFile;

    public PlayerData(UUID uuid){
        this.uuid = uuid;
        file = new File("plugins/RoseKingdom/PlayerData/" + uuid + ".yml");
        configFile = YamlConfiguration.loadConfiguration(file);
    }

    public void createPlayerConfig(){
        try {
            file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
            throw new SecurityException(e);
        }
    }

    public boolean isEmpty(){
        return getConfig().getKeys(true).size() == 0;
    }

    public FileConfiguration getConfig(){
        return configFile;
    }

    public void save(){
        try {
            getConfig().save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean hasKey(String name){
        return configFile.contains(name);
    }

    public void reset(){
        try {
            file.delete();
            createPlayerConfig();
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }
}
