package me.rosekingdom.rosekingdom.Handlers;

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
        }
    }

    public boolean isEmpty(){
        return !file.exists();
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

    public void reset(){
        file.delete();
        createPlayerConfig();
    }
}
