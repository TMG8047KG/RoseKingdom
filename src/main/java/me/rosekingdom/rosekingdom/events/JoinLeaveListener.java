package me.rosekingdom.rosekingdom.events;

import me.rosekingdom.rosekingdom.items.InfoBook;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collection;

public class JoinLeaveListener implements Listener {
    private Collection playerCount;

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        RefreshTab();

        e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + player.getDisplayName());

        if(!player.hasPlayedBefore()){
            e.setJoinMessage(ChatColor.GOLD + "Welcome " + player.getDisplayName() + " to the lands of Rose Kingdom");
            player.sendTitle(ChatColor.DARK_RED + "Rose" + ChatColor.GOLD + "Kingdom",ChatColor.GOLD + "Welcome to the Kingdom",20,80,20);
            InfoBook.getBook(player);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player player = e.getPlayer();

        RefreshTab();

        e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + player.getDisplayName());
    }

    public void RefreshTab(){
        playerCount = Bukkit.getOnlinePlayers();
        for(Player player : Bukkit.getOnlinePlayers()){
            player.setPlayerListHeaderFooter("\uEff3\n\n\n\n", ChatColor.GOLD + "\nCurrently playing: " + playerCount.size() + "\n");
            //\uF821
        }
    }
}
