package me.rosekingdom.rosekingdom.Listeners.GUI.Home;

import me.rosekingdom.rosekingdom.GUIs.Home.HomeTypeSelectionMenu;
import me.rosekingdom.rosekingdom.utils.CustomPlayerHeads;
import me.rosekingdom.rosekingdom.utils.PlayerData;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.meta.SkullMeta;

import java.time.LocalDate;

public class HomeTypeSelectionEvent implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e){
        Player player = (Player) e.getPlayer();
        PlayerData pData = new PlayerData(player.getUniqueId());
        FileConfiguration pcd = pData.getConfig();
        if(e.getInventory().getHolder() instanceof HomeTypeSelectionMenu){
            if(pcd.contains("temp")){
                pcd.set("temp", null);
                player.sendMessage("§cAdding new location was canceled!");
                pData.save();
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(e.getClickedInventory() == null){
            return;
        }
        if(e.getClickedInventory().getHolder() instanceof HomeTypeSelectionMenu){
            e.setCancelled(true);
            PlayerData pData = new PlayerData(player.getUniqueId());
            FileConfiguration pcd = pData.getConfig();

            if(e.getCurrentItem()==null){
                return;
            }
            if(e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)){
                CustomPlayerHeads heads = new CustomPlayerHeads();
                String name = (String) pcd.get("temp");
                String path = "locations." + name;
                SkullMeta meta = (SkullMeta) e.getCurrentItem().getItemMeta();

                int x = player.getLocation().getBlockX();
                int y = player.getLocation().getBlockY();
                int z = player.getLocation().getBlockZ();

                pcd.set("temp", null);
                pcd.set(path + ".coordinates", x + " " + y + " " + z);
                pcd.set(path + ".date", LocalDate.now().toString());
                pcd.set(path + ".item", heads.setUrl(meta));
                pcd.set(path + ".public" , false);
                pData.save();
                player.sendMessage("§aSuccessfully added new location \""+ name +"\"");
                player.closeInventory();
            }
        }
    }
}
