package me.rosekingdom.rosekingdom.Listeners;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;


public class Unplaceable_Hats implements Listener {

    @EventHandler
    public void place(BlockPlaceEvent e){
        Block block = e.getBlock();
        if(block.getType().equals(Material.CARVED_PUMPKIN) && e.getItemInHand().getItemMeta().hasCustomModelData()){
            e.setCancelled(true);
        }
    }
}
