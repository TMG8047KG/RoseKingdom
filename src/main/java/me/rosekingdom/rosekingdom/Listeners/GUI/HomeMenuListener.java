package me.rosekingdom.rosekingdom.Listeners.GUI;

import me.rosekingdom.rosekingdom.GUIs.Home_Menu;
import me.rosekingdom.rosekingdom.Handlers.PlayerData;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HomeMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getClickedInventory() == null){
            return;
        }
        if(e.getClickedInventory().getHolder() instanceof Home_Menu){
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            PlayerData playerData = new PlayerData(player.getUniqueId());
            FileConfiguration pd = playerData.getConfig();

            int x = player.getLocation().getBlockX();
            int y = player.getLocation().getBlockY();
            int z = player.getLocation().getBlockZ();

            if(e.getCurrentItem() == null){
                return;
            }
            if (e.getSlot() == 4 && e.getCurrentItem().getType().equals(Material.WRITABLE_BOOK)){
                ItemStack item = e.getCurrentItem();
                item.setType(Material.MAP);
                ItemMeta meta = e.getCurrentItem().getItemMeta();
                meta.setDisplayName("Home: "+ x + " " + y + " " + z);
                List<String> lore = new ArrayList<>();
                lore.add("The coordinates of you're home");
                lore.add("Date of Creation: " + LocalDate.now());
                meta.setLore(lore);
                e.getCurrentItem().setItemMeta(meta);
                pd.set("home.coordinates", x + " " + y + " " + z);
                pd.set("home.date", LocalDate.now().toString());
                playerData.save();
            }
        }
    }
}
