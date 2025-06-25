package com.rosekingdom.rosekingdom.Core.Events;

import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.Items.GuideBook;
import com.rosekingdom.rosekingdom.Core.NPCs.NPC;
import com.rosekingdom.rosekingdom.Core.NPCs.NPCHandler;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Core.Utils.ResourcePackLoader;
import com.rosekingdom.rosekingdom.Economy.Statements.EconomyStatement;
import com.rosekingdom.rosekingdom.Graves.Grave;
import com.rosekingdom.rosekingdom.Graves.GraveHandler;
import com.rosekingdom.rosekingdom.Graves.Statements.DeathStatement;
import com.rosekingdom.rosekingdom.Moderation.Utils.vanish;
import com.rosekingdom.rosekingdom.RoseKingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandler;
import com.rosekingdom.rosekingdom.Tab.Tab;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ServerLinks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class onJoin implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoinEvent(PlayerJoinEvent e){
        Player player = e.getPlayer();
        ResourcePackLoader.setResourcePack(player);

        if(UserStatement.isVanished(player)){
            vanish.changePlayerVisibility(player, true);
            e.joinMessage(Component.empty());
            player.sendMessage(Component.text("You are vanished!", TextColor.fromHexString("#04cfde")));
        }

        //Join Message
        e.joinMessage(Component.text("[", TextColor.fromHexString("#696969"))
                .append(Component.text("+", TextColor.fromHexString("#3fd951"))
                .append(Component.text("] ", TextColor.fromHexString("#696969")))
                .append(player.displayName().color(TextColor.fromHexString("#7d7d7d")))));

        Tab.display(player);

        //Database things
        if(!UserStatement.exists(player.getUniqueId())) {
            UserStatement.insert(player.getName(), player.getUniqueId().toString());
            EconomyStatement.insert(player);
            player.getInventory().addItem(new GuideBook());
        }

        //Rank
        Tab.join(player);
        Kingdom kingdom = KingdomHandler.getKingdom(player);
        if(kingdom != null && kingdom.getInChat().contains(player.getUniqueId())){
            player.sendMessage(Component.text("You are currently chatting with " + kingdom.getName() + "'s members.", TextColor.fromHexString("#5ae630")));
        }

        sendServerLinks(player);

        List<NPC> npcList = NPCHandler.getNPCs();
        npcList.removeAll(KingdomHandler.getSeparators());
        for(NPC npc : npcList){
            npc.spawn();
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

    @SuppressWarnings({"Experimental", "UnstableApiUsage"})
    private void sendServerLinks(Player player){
        ServerLinks serverLinks = Bukkit.getServerLinks();
        try {
            serverLinks.addLink(Component.text("Discord"), new URI("https://discord.gg/WCPcF8zbus"));
            serverLinks.addLink(ServerLinks.Type.REPORT_BUG, URI.create("https://github.com/RozeWorld/RoseKingdom/issues"));
            serverLinks.addLink(ServerLinks.Type.FEEDBACK, URI.create("https://github.com/RozeWorld/RoseKingdom/issues"));
            player.sendLinks(serverLinks);
        } catch (URISyntaxException e) {
            Message.Exception("A link is not working", e);
        }
    }
}
