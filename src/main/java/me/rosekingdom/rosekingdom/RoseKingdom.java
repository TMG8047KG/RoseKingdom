package me.rosekingdom.rosekingdom;

import me.rosekingdom.rosekingdom.Handlers.CommandManager;
import me.rosekingdom.rosekingdom.commands.Coordinates_Share;
import me.rosekingdom.rosekingdom.commands.temp_bm;
import me.rosekingdom.rosekingdom.events.JoinLeaveListener;
import me.rosekingdom.rosekingdom.items.Medals;
import org.bukkit.plugin.java.JavaPlugin;


public final class RoseKingdom extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("[RoseKingdom] Loaded!");
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(), this);

        Medals.init();
        Commands();
        new CommandManager(this);
    }

    public void Commands(){
        getCommand("temp_bm").setExecutor(new temp_bm());
        getCommand("coords").setExecutor(new Coordinates_Share());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("[RoseKingdom] Shutting down");
    }
}
