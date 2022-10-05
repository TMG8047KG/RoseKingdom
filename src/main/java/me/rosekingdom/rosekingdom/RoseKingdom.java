package me.rosekingdom.rosekingdom;

import me.rosekingdom.rosekingdom.Commands.Coordinates_Share;
import me.rosekingdom.rosekingdom.Commands.SpawnEntity;
import me.rosekingdom.rosekingdom.Listeners.*;
import me.rosekingdom.rosekingdom.Materials.Items.Bucket_Hats;
import me.rosekingdom.rosekingdom.Materials.Items.Mushroom_Hats;
import me.rosekingdom.rosekingdom.Materials.Items.Medals;
import org.bukkit.plugin.java.JavaPlugin;


public final class RoseKingdom extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("[RoseKingdom] Loaded!");
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new Unplaceable_Hats(), this);
//        getServer().getPluginManager().registerEvents(new Riding_Player_Listener(), this);
//        getServer().getPluginManager().registerEvents(new Chair(), this);
//        getServer().getPluginManager().registerEvents(new Sign_Rewrite(), this);

        Medals.init();
        Bucket_Hats.init();
        Mushroom_Hats.init();
        Commands();
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
