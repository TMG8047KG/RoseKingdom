package me.rosekingdom.rosekingdom;

import me.rosekingdom.rosekingdom.Commands.Coordinates_Share;
import me.rosekingdom.rosekingdom.Commands.SpawnEntity;
import me.rosekingdom.rosekingdom.Handlers.CommandManager;
import me.rosekingdom.rosekingdom.Listeners.*;
import me.rosekingdom.rosekingdom.Materials.Items.Bucket_Hats;
import me.rosekingdom.rosekingdom.Materials.Items.Mushroom_Hats;
import me.rosekingdom.rosekingdom.Materials.Items.Medals;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;


public final class RoseKingdom extends JavaPlugin {

    private File playersConfigFile;
    private FileConfiguration playerConfig;
    private String file = "playersData.yml";

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("[RoseKingdom] Loaded!");
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new Unplaceable_Hats(), this);
//        getServer().getPluginManager().registerEvents(new Riding_Player_Listener(), this);
//        getServer().getPluginManager().registerEvents(new Chair(), this);
//        getServer().getPluginManager().registerEvents(new Sign_Rewrite(), this);

        new CommandManager(this);

        Medals.init();
        Bucket_Hats.init();
        Mushroom_Hats.init();
        Commands();

        //createPlayersConfigFile();
    }

    public FileConfiguration getPlayerConfig(){
        return this.playerConfig;
    }

    private void createPlayersConfigFile(){
        playersConfigFile = new File(getDataFolder(), file);
        if(!playersConfigFile.exists()){
            playersConfigFile.getParentFile().mkdir();
            saveResource(file, false);
        }

        playerConfig = new YamlConfiguration();
        try {
            playerConfig.load(playersConfigFile);
        }catch (IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }

    }

    public void Commands(){
        getCommand("coords").setExecutor(new Coordinates_Share());
        getCommand("spawnentity").setExecutor(new SpawnEntity());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("[RoseKingdom] Shutting down");
    }
}
