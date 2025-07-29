package com.rosekingdom.rosekingdom.Core.NPCs;

import com.destroystokyo.paper.event.player.PlayerUseUnknownEntityEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCInteractions implements Listener {
    @EventHandler
    public void onClickedNPC(PlayerUseUnknownEntityEvent e){
        Player p = e.getPlayer();
//        if(e.isAttack()) {
//            p.sendMessage("Left");
//        }else{
//            p.sendMessage("Right");
//        }
    }
}
