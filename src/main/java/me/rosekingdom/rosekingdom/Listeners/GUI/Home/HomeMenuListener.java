package me.rosekingdom.rosekingdom.Listeners.GUI.Home;

import me.rosekingdom.rosekingdom.GUIs.Home.HomeSelectionMenu;
import me.rosekingdom.rosekingdom.GUIs.Home.Home_Menu;
import me.rosekingdom.rosekingdom.Handlers.PlayerData;
import org.bukkit.configuration.file.FileConfiguration;
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
            PlayerData pData = new PlayerData(player.getUniqueId());
            FileConfiguration fc = pData.getConfig();

            if(e.getCurrentItem() == null){
                return;
            }else{
                for (String name : fc.getConfigurationSection("locations").getKeys(false)) {
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6"+name)){
                        player.sendMessage("§6\"" + name + "\"'s coordinates: §f" + fc.getString("locations."+name+".coordinates"));
                        player.closeInventory();
                    }
                }
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
