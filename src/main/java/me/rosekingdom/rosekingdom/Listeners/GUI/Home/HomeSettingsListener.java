package me.rosekingdom.rosekingdom.Listeners.GUI.Home;

import me.rosekingdom.rosekingdom.GUIs.Home.HomeConfirmationMenu;
import me.rosekingdom.rosekingdom.GUIs.Home.HomeSettingsMenu;
import me.rosekingdom.rosekingdom.utils.ItemCreator;
import me.rosekingdom.rosekingdom.utils.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class HomeSettingsListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        PlayerData pData = new PlayerData(e.getPlayer().getUniqueId());
        FileConfiguration co = pData.getConfig();
        if(e.getReason().equals(InventoryCloseEvent.Reason.PLUGIN)) return;
        if(e.getInventory().getHolder() instanceof HomeConfirmationMenu){
            if((pData.hasKey("temp")) || (pData.hasKey("delete"))){
                co.set("temp", null);
                co.set("delete", null);
                pData.save();
                e.getPlayer().sendMessage("Canceled deletion!");
                return;
            }
        }
        if (e.getInventory().getHolder() instanceof HomeSettingsMenu){
            co.set("temp", null);
            pData.save();
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        PlayerData pData = new PlayerData(player.getUniqueId());
        FileConfiguration co = pData.getConfig();
        if(e.getClickedInventory() == null){
            return;
        }
        if(e.getClickedInventory().getHolder() instanceof HomeSettingsMenu){
            ItemCreator creator = new ItemCreator();

            String path = "locations."+co.getString("temp");

            if(e.getSlot() == 10){
                ItemStack item;
                if(co.getBoolean(path + ".public")){
                    List<Component> lore = new ArrayList<>();
                    lore.add(Component.text("When private no one except you can see this location", TextColor.color(135, 214, 79)));
                    lore.add(Component.text("=====================================", TextColor.color(77, 77, 77)));
                    lore.add(Component.text("Left click this to make it public", TextColor.fromHexString("#707070")));
                    item = creator.createItem(Component.text("Private"), Material.RED_CONCRETE, lore);
                    co.set(path + ".public", false);
                }else{
                    List<Component> lore = new ArrayList<>();
                    lore.add(Component.text("When public everyone can see this location", TextColor.color(135, 214, 79)));
                    lore.add(Component.text("=====================================", TextColor.color(77, 77, 77)));
                    lore.add(Component.text("Left click this to make it private", TextColor.fromHexString("#707070")));
                    item = creator.createItem(Component.text("Public"), Material.GREEN_CONCRETE, lore);
                    co.set(path + ".public", true);
                }
                e.getCurrentItem().setItemMeta(item.getItemMeta());
                e.getCurrentItem().setType(item.getType());
                pData.save();
            }
            if(e.getSlot() == 16){
                co.set("delete", co.getString("temp"));
                pData.save();
                List<Component> lore = new ArrayList<>();
                lore.add(Component.text("Are you sure you want to delete this location", TextColor.fromHexString("#d62000")));
                HomeConfirmationMenu menu = new HomeConfirmationMenu(Component.text("Deletion", TextColor.fromHexString("#bf0000")), lore);
                player.closeInventory();
                player.openInventory(menu.getInventory());
            }
            e.setCancelled(true);
        }

        if(e.getClickedInventory().getHolder() instanceof HomeConfirmationMenu){
            if (pData.hasKey("delete")) {
                if (e.getCurrentItem() == null) {
                    return;
                }
                if (e.getCurrentItem().getType().equals(Material.GREEN_STAINED_GLASS_PANE)) {
                    player.sendMessage("Deleted");
                    co.set("locations." + co.getString("temp"), null);
                    co.set("temp", null);
                    co.set("delete", null);
                    pData.save();
                    player.closeInventory();
                }
                if (e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
                    player.sendMessage("Canceled");
                    co.set("delete", null);
                    pData.save();

                    HomeSettingsMenu settings = new HomeSettingsMenu(player, co.getString("temp"));
                    player.closeInventory();
                    player.openInventory(settings.getInventory());
                }
            }
        }
    }

}
