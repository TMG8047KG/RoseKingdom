package me.rosekingdom.rosekingdom;

import me.rosekingdom.rosekingdom.Handlers.Commands.CommandManager;
import me.rosekingdom.rosekingdom.Listeners.Events.JoinLeaveListener;
import me.rosekingdom.rosekingdom.Listeners.Events.OnDead;
import me.rosekingdom.rosekingdom.Listeners.Functions.SignChangeEvent;
import me.rosekingdom.rosekingdom.Listeners.Functions.Unplaceable_Hats;
import me.rosekingdom.rosekingdom.Listeners.GUI.Home.HomeMenuListener;
import me.rosekingdom.rosekingdom.Listeners.GUI.Home.HomeSelectionMenuEvent;
import me.rosekingdom.rosekingdom.Materials.Items.Bucket_Hats;
import me.rosekingdom.rosekingdom.Materials.Items.Mushroom_Hats;
import me.rosekingdom.rosekingdom.Materials.Items.Medals;

import org.bukkit.plugin.java.JavaPlugin;

public final class RoseKingdom extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("[RoseKingdom] Loaded!");

//        getConfig().options().copyDefaults(true);
//        saveConfig();

        getServer().getPluginManager().registerEvents(new JoinLeaveListener(), this);
        getServer().getPluginManager().registerEvents(new Unplaceable_Hats(), this);
        getServer().getPluginManager().registerEvents(new OnDead(), this);
        getServer().getPluginManager().registerEvents(new HomeMenuListener(), this);
        getServer().getPluginManager().registerEvents(new HomeSelectionMenuEvent(), this);
        getServer().getPluginManager().registerEvents(new SignChangeEvent(), this);

        Medals.init();
        Bucket_Hats.init();
        Mushroom_Hats.init();

        new CommandManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("[RoseKingdom] Shutting down");
    }
}
