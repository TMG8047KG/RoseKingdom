package me.rosekingdom.rosekingdom.Listeners.GUI.Home;

import me.rosekingdom.rosekingdom.GUIs.Home.HomeSelectionMenu;
import me.rosekingdom.rosekingdom.GUIs.Home.Home_Menu;
import me.rosekingdom.rosekingdom.Handlers.PlayerData;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.time.LocalDate;

public class HomeSelectionMenuEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getClickedInventory() == null){
            return;
        }
        if(e.getClickedInventory().getHolder() instanceof HomeSelectionMenu){
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            PlayerData pData = new PlayerData(player.getUniqueId());
            FileConfiguration pcd = pData.getConfig();

            Home_Menu menu = new Home_Menu(player);

            int x = player.getLocation().getBlockX();
            int y = player.getLocation().getBlockY();
            int z = player.getLocation().getBlockZ();


            if(e.getCurrentItem() == null){
                return;
            }
            if(e.getCurrentItem().getType().equals(Material.GREEN_STAINED_GLASS_PANE)){
                pcd.set("locations.home.coordinates", x + " " + y + " " + z);
                pcd.set("locations.home.date", LocalDate.now().toString());
                pData.save();
                //TODO remove
                player.sendMessage("§aAgreed");
                player.closeInventory();
            }
            if(e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)){
                //TODO remove
                player.sendMessage("§cDenied");
                player.openInventory(menu.getInventory());
            }
        }

    }
}
