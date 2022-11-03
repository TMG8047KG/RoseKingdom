package me.rosekingdom.rosekingdom;

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

//        getConfig().options().copyDefaults(true);
//        saveConfig();

        getServer().getPluginManager().registerEvents(new JoinLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new Unplaceable_Hats(), this);
        getServer().getPluginManager().registerEvents(new OnDead(), this);

        Medals.init();
        Bucket_Hats.init();
        Mushroom_Hats.init();

        //TODO
        // Make a perPlayerDataSaver
        //createPlayersConfigFile();

        new CommandManager(this);
    }

    public FileConfiguration getPlayerConfig(){
        return this.playerConfig;
    }

    //TODO
    // Finish this down here (perPlayerData file)
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

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("[RoseKingdom] Shutting down");
    }
}
