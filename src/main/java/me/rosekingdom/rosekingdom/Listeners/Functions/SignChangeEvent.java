package me.rosekingdom.rosekingdom.Listeners.Functions;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignChangeEvent implements Listener {

    @EventHandler
    public void onSignClick(PlayerInteractEvent e){
        if(e.getPlayer().isSneaking()){
            if(e.getClickedBlock().getState() instanceof Sign sign){
                e.getPlayer().openSign(sign);
            }
        }
    }
}
