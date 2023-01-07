package me.rosekingdom.rosekingdom.Events;

import me.rosekingdom.rosekingdom.utils.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

import java.time.Duration;

public class JoinLeaveListener implements Listener {

    private int playerCount = Bukkit.getOnlinePlayers().size();

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        playerCount++;
        RefreshTab();

        player.setResourcePack("https://www.dropbox.com/s/tc18x6ed5hrlgue/RoseKingdom.zip?dl=1", "d35b9749aa7afa6d2c48f61316db0aab6f5586e8");

        e.joinMessage(Component.text("[", TextColor.fromHexString("#696969")).append(Component.text("+", TextColor.fromHexString("#3fd951")).append(Component.text("] ", TextColor.fromHexString("#696969"))).append(player.displayName().color(TextColor.fromHexString("#7d7d7d")))));

        PlayerData playerData = new PlayerData(player.getUniqueId());
        playerData.createPlayerConfig();
        playerData.save();

        if(!player.hasPlayedBefore()){
            e.joinMessage(Component.text("Welcome, ",TextColor.fromHexString("#f5a51b")).append(player.displayName().color(TextColor.fromHexString("#f5a51b"))).append(Component.text(" to the lands!",TextColor.fromHexString("#f5a51b"))));
        }
    }

    @EventHandler
    public void PlayerResourcePackStatusEvent(PlayerResourcePackStatusEvent e){
        Player player = e.getPlayer();
        if(e.getStatus() == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED){
            if(!player.hasPlayedBefore()) {
                Title test = Title.title(Component.text("RoseKingdom", TextColor.fromHexString("#d11515")), Component.text("Welcome to the lands!", TextColor.fromHexString("#f5a51b")), Title.Times.times(Duration.ofSeconds(2), Duration.ofSeconds(5), Duration.ofSeconds(1)));
                player.showTitle(test);
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player player = e.getPlayer();

        playerCount--;
        RefreshTab();

        e.quitMessage(Component.text("[", TextColor.fromHexString("#696969")).append(Component.text("-", TextColor.fromHexString("#d12424")).append(Component.text("] ", TextColor.fromHexString("#696969"))).append(player.displayName().color(TextColor.fromHexString("#7d7d7d")))));
    }

    public void RefreshTab(){
        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendPlayerListHeaderAndFooter(Component.text("\uEff3\n\n\n\n"), Component.text("\nCurrently playing: ", TextColor.fromHexString("#cc0c6c")).append(Component.text(playerCount).color(TextColor.fromHexString("#cc0c6c"))).append(Component.text("\n")));
        }
    }
}
