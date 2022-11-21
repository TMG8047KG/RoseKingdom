package me.rosekingdom.rosekingdom.GUIs.Home;

import me.rosekingdom.rosekingdom.utils.ItemCreator;
import me.rosekingdom.rosekingdom.utils.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeSettingsMenu implements InventoryHolder {

    private final Inventory menu;

    public HomeSettingsMenu(Player player, String name){
        menu = Bukkit.createInventory(this, 27, Component.text("Settings"));
        setup(player, name);
    }

    private void setup(Player player, String name) {
        ItemCreator creator = new ItemCreator();
        PlayerData data = new PlayerData(player.getUniqueId());
        FileConfiguration co = data.getConfig();
        co.set("temp", name);
        data.save();

        ItemStack item;

        int[] border = {10,12,14,16};
        for(int k=0;k<27;k++){
            for(int s : border){
                if(k==s) {
                    k++;
                    break;
                }
            }
            item = creator.createItem(Material.GRAY_STAINED_GLASS_PANE);
            menu.setItem(k, item);
        }

        if(!co.getBoolean("locations." + name + ".public")){
            List<Component> lore = new ArrayList<>();
            lore.add(Component.text("When private no one except you can see this location", TextColor.color(135, 214, 79)));
            lore.add(Component.text("====================================", TextColor.color(77, 77, 77)));
            lore.add(Component.text("Left click this to make it public", TextColor.fromHexString("#707070")));
            item = creator.createItem(Component.text("Private"), Material.RED_CONCRETE, lore);
        }else{
            List<Component> lore = new ArrayList<>();
            lore.add(Component.text("When public everyone can see this location", TextColor.color(135, 214, 79)));
            lore.add(Component.text("====================================", TextColor.color(77, 77, 77)));
            lore.add(Component.text("Left click this to make it private", TextColor.fromHexString("#707070")));
            item = creator.createItem(Component.text("Public"), Material.GREEN_CONCRETE, lore);
        }
        menu.setItem(10, item);

        item = creator.createItem(Component.text("Rename"), Material.NAME_TAG);
        menu.setItem(12, item);

        item = creator.createItem(Component.text("Reset Coordinates"), Material.MAP);
        menu.setItem(14, item);

        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("Deletes this location!", TextColor.fromHexString("#9e0000")));
        lore.add(Component.text("====================================",TextColor.fromHexString("#4a4a4a")));
        lore.add(Component.text("Location name: ", TextColor.fromHexString("#d9a90d"))
                .append(Component.text(name, TextColor.fromHexString("#fff4cc"))));
        lore.add(Component.text("Location coordinates:", TextColor.fromHexString("#d9a90d"))
                .append(Component.text(" " + co.getString("locations." + name + ".coordinates"), TextColor.fromHexString("#fff4cc"))));
        item = creator.createItem(Component.text("Delete",TextColor.fromHexString("#bf0000")), Material.BARRIER, lore);
        menu.setItem(16, item);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return menu;
    }
}
