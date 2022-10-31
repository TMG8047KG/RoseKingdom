package me.rosekingdom.rosekingdom.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static org.bukkit.Bukkit.getLogger;

public class OnDead implements Listener {

    @EventHandler
    public void OnPlayerDead(PlayerDeathEvent e){
        Player p = e.getPlayer();

        int x = p.getLocation().getBlockX();
        int y = p.getLocation().getBlockY();
        int z = p.getLocation().getBlockZ();

        p.sendMessage("You've died on: " + x + " " + y + " " + z);
        getLogger().info(p.getName()+"'s died on: " + x + " " + y + " "+z);
    }
}
