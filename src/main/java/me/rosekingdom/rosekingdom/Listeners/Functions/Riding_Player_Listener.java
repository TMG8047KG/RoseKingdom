package me.rosekingdom.rosekingdom.Listeners.Functions;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.spigotmc.event.entity.EntityDismountEvent;


public class Riding_Player_Listener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e) {
        if(!(e.getRightClicked() instanceof Player)){
            return;
        }
        Player player = e.getPlayer();
        Player target = (Player) e.getRightClicked();
        ArmorStand seat = addSeat(target.getLocation());
        seat.addPassenger(player);
        while (!(seat.isEmpty())){
            seat.teleport(target.getLocation());
        }
    }

    @EventHandler
    public void onDismount(EntityDismountEvent e){
        Entity seat = e.getDismounted();

        if(seat instanceof ArmorStand){
            seat.remove();
        }
    }

    private ArmorStand addSeat(Location location){
        ArmorStand as = location.getWorld().spawn(location, ArmorStand.class);

        as.setVisible(false);
        as.setInvulnerable(false);
        as.setGravity(false);

        return as;
    }
}
