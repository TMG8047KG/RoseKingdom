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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Home_Menu implements InventoryHolder {


    private Inventory menu;

    public Home_Menu(Player player) {
        menu = Bukkit.createInventory(this, 36, "Home Menu");
        init(player);
    }

    private void init(Player player) {
        PlayerData data = new PlayerData(player.getUniqueId());
        FileConfiguration co = data.getConfig();
        ItemStack item;
        item = createItem("Set Home", Material.WRITABLE_BOOK, Collections.singletonList("Test"), 0);
        if(data.getConfig().get("home") == null){
            menu.setItem(4, item);
        }else{
            item = createItem("Home: " + co.getString("home.coordinates"), Material.FILLED_MAP, Arrays.asList("The coordinates of you're home", "Date of Creation: "+ co.getString("home.date")), 0);
            menu.setItem(4, item);
        }
        int sp_x = player.getWorld().getSpawnLocation().getBlockX();
        int sp_y = player.getWorld().getSpawnLocation().getBlockY();
        int sp_z = player.getWorld().getSpawnLocation().getBlockZ();

        item = createItem("Spawn: " + sp_x + " " + sp_y + " " + sp_z, Material.FILLED_MAP, null, 0);
        menu.setItem(2,item);
    }

    private ItemStack createItem(String name, Material material, List<String> lore, int CMD){
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        if(CMD != 0){
            meta.setCustomModelData(CMD);
        }
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public Inventory getInventory() {
        return menu;
    }
}
