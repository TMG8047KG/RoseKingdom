package me.rosekingdom.rosekingdom.Listeners.GUI.Home;

import me.rosekingdom.rosekingdom.GUIs.Home.HomeConfirmationMenu;
import me.rosekingdom.rosekingdom.GUIs.Home.HomeSettingsMenu;
import me.rosekingdom.rosekingdom.GUIs.Home.Home_Menu;
import me.rosekingdom.rosekingdom.utils.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;

public class HomeMenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(e.getClickedInventory() == null){
            return;
        }
        if(e.getCurrentItem() == null){
            return;
        }
        if(e.getClickedInventory().getHolder() instanceof Home_Menu){
            PlayerData pData = new PlayerData(player.getUniqueId());
            FileConfiguration fc = pData.getConfig();

            if(!pData.isEmpty()){
                TextComponent displayName = (TextComponent) e.getCurrentItem().getItemMeta().displayName();
                if (e.getClick().isRightClick()) {
                    for (String name : fc.getConfigurationSection("locations").getKeys(false)) {
                        if (displayName != null && displayName.content().equals(name)) {
                            HomeSettingsMenu settings = new HomeSettingsMenu(player, name);
                            player.openInventory(settings.getInventory());
                        }
                    }
                }
                if (e.getClick().isLeftClick()) {
                    for (String name : fc.getConfigurationSection("locations").getKeys(false)) {
                        if (displayName != null && displayName.content().equals(name)) {
                            player.sendMessage("??6\"" + name + "\"'s coordinates: ??f" + fc.getString("locations." + name + ".coordinates"));
                            player.closeInventory();
                        }
                    }
                }
            }

            if (e.getSlot() == 13){

                int x = player.getLocation().getBlockX();
                int y = player.getLocation().getBlockY();
                int z = player.getLocation().getBlockZ();

                List<Component> lore = new ArrayList<>();
                lore.add(Component.text("Are you sure you want to set this coordinates?", TextColor.fromHexString("#0d9e2a")));
                HomeConfirmationMenu sel = new HomeConfirmationMenu(Component.text(x + " " + y + " " + z), lore);
                player.openInventory(sel.getInventory());
            }

            if(e.getSlot() == 40){
                player.closeInventory();
            }
            e.setCancelled(true);
        }
    }
}
