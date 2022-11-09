package me.rosekingdom.rosekingdom.GUIs.Home;

import me.rosekingdom.rosekingdom.Handlers.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HomeSelectionMenu implements InventoryHolder {

    private Inventory menu;

    public HomeSelectionMenu(){
        menu = Bukkit.createInventory(this, 9, "Are you sure you want to proceed");
    }

    public void setup(Player player){
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();



        ItemStack item;
        for(int k=0;k<=3; k++){
            item = createItem(Material.RED_STAINED_GLASS_PANE, "Deny" , Collections.singletonList("You don't agree to set this coordinates!"));
            menu.setItem(k, item);
        }
        item = createItem(Material.WRITTEN_BOOK, x + " " + y + " " + z , Arrays.asList("§6Do you want to set home to this coordinates", "", "§o§7You can change this later!"));
        menu.setItem(4, item);
        for(int k=5;k<9; k++){
            item = createItem(Material.GREEN_STAINED_GLASS_PANE, "Accept" , Collections.singletonList("You agree to set this coordinates!"));
            menu.setItem(k, item);
        }
    }

    private ItemStack createItem(Material mt , String name, List<String> lore){
        ItemStack item = new ItemStack(mt, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return menu;
    }
}
