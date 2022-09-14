package me.rosekingdom.rosekingdom.Listeners;

import me.rosekingdom.rosekingdom.Materials.Items.InfoBook;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {
    private int playerCount;

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        playerCount++;
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

        playerCount--;
        RefreshTab();

        e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + player.getDisplayName());
    }

    public void RefreshTab(){
        for(Player player : Bukkit.getOnlinePlayers()){
            player.setPlayerListHeaderFooter("\uEff3\n\n\n\n", ChatColor.GOLD + "\nCurrently playing: " + playerCount + "\n");
            //\uF821
        }
    }
}
