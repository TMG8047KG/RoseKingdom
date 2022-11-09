package me.rosekingdom.rosekingdom.Listeners.GUI.Home;

import me.rosekingdom.rosekingdom.GUIs.Home.HomeSelectionMenu;
import me.rosekingdom.rosekingdom.GUIs.Home.Home_Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class HomeMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(e.getClickedInventory() == null){
            return;
        }
        if(e.getClickedInventory().getHolder() instanceof Home_Menu){
            if(e.getCurrentItem() == null){
                return;
            }
            if (e.getSlot() == 13){
                HomeSelectionMenu sel = new HomeSelectionMenu();
                sel.setup(player);
                player.openInventory(sel.getInventory());
            }

            e.setCancelled(true);
        }
    }
}
