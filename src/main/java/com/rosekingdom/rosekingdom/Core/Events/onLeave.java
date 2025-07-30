package com.rosekingdom.rosekingdom.Core.Events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onLeave implements Listener {

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent e){
        Player player = e.getPlayer();
        e.quitMessage(Component.text("[", TextColor.fromHexString("#696969"))
                .append(Component.text("-", TextColor.fromHexString("#d90d12")))
                .append(Component.text("] ", TextColor.fromHexString("#696969")))
                .append(Component.text(player.getName(), TextColor.fromHexString("#7d7d7d"))));
    }
}
