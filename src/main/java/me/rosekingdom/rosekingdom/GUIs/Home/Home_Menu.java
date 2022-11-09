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
import java.util.List;

public class Home_Menu implements InventoryHolder {


    private Inventory menu;

    public Home_Menu(Player player) {
        menu = Bukkit.createInventory(this, 36, "Home Menu");
        setup(player);
    }

    private void setup(Player player) {
        PlayerData data = new PlayerData(player.getUniqueId());
        FileConfiguration co = data.getConfig();

        ItemStack item;

        int[] border = {0, 1, 2, 3, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35};
        for (int br : border) {
            item = createItem(null, Material.GRAY_STAINED_GLASS_PANE, null, 0);
            menu.setItem(br, item);
        }

        int[] soon = {10,11,12,14,15,16};
        for (int s : soon) {
            item = createItem("§cSOON", Material.ORANGE_STAINED_GLASS_PANE, null, 0);
            menu.setItem(s, item);
        }

        int sp_x = player.getWorld().getSpawnLocation().getBlockX();
        int sp_y = player.getWorld().getSpawnLocation().getBlockY();
        int sp_z = player.getWorld().getSpawnLocation().getBlockZ();

        item = createItem("§dSpawn", Material.POPPY, Arrays.asList(
                "§7===============",
                "§6Coordinates:",
                "§f" + sp_x + " " + sp_y + " " + sp_z,
                "§7---------------",
                "§7Overworld Spawn Coordinates",
                "§7==============="
        ), 0);
        menu.setItem(4, item);

        item = createItem("§6Create new location", Material.WRITABLE_BOOK, Arrays.asList(
                "§7===============",
                "§aAdds new location coordinates to the list",
                "§7==============="
        ),0);
        menu.setItem(13, item);

        try{
            if(!(data.isEmpty())){
                for (String lc : co.getConfigurationSection("locations").getKeys(false)) {
                    item = createItem("§6"+ lc, Material.GRASS_BLOCK, Arrays.asList(
                            "§7===============",
                            "§6Coordinates:",
                            "§f" + co.getString("locations." + lc + ".coordinates"),
                            "§7---------------",
                            "§6Date of Creation",
                            "§2" + co.getString("locations." + lc + ".date"),
                            "§7==============="
                    ), 0);
                    menu.addItem(item);
                }
            }
        }catch (Exception e){
            player.sendMessage("Error: " + e);
        }
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
