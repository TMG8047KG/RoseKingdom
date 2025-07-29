package com.rosekingdom.rosekingdom.Core.Events;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.Items.GuideBook;
import com.rosekingdom.rosekingdom.Core.Utils.ResourcePackLoader;
import com.rosekingdom.rosekingdom.Economy.Statements.EconomyStatement;
import com.rosekingdom.rosekingdom.Graves.Grave;
import com.rosekingdom.rosekingdom.Graves.GraveHandler;
import com.rosekingdom.rosekingdom.Graves.Statements.DeathStatement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoinEvent(PlayerJoinEvent e){
        Player player = e.getPlayer();
        ResourcePackLoader.setResourcePack(player);

        //Join Message
        e.joinMessage(Component.text("[", TextColor.fromHexString("#696969"))
                .append(Component.text("+", TextColor.fromHexString("#3fd951"))
                .append(Component.text("] ", TextColor.fromHexString("#696969")))
                .append(player.displayName().color(TextColor.fromHexString("#7d7d7d")))));

        //Database things
        if(!UserStatement.exists(player.getUniqueId())) {
            UserStatement.insert(player.getName(), player.getUniqueId().toString());
            EconomyStatement.insert(player);
            player.getInventory().addItem(new GuideBook());
        }

        //Load Graves (if any)
        int id = UserStatement.getId(player);
        if(DeathStatement.hasGraves(id)){
            for(Grave grave : GraveHandler.getGraves(id)){
                grave.setPlayer(player);
                grave.showPlayerGrave();
            }
        }
    }
}
