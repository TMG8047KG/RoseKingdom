package me.rosekingdom.rosekingdom.GUIs.Home;

import me.rosekingdom.rosekingdom.utils.ItemCreator;
import me.rosekingdom.rosekingdom.utils.PlayerData;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Home_Menu implements InventoryHolder {


    private final Inventory menu;

    public Home_Menu(Player player) {
        menu = Bukkit.createInventory(this, 45, Component.text("Home Menu"));
        setup(player);
    }

    private void setup(Player player) {
        ItemCreator creator = new ItemCreator();
        PlayerData data = new PlayerData(player.getUniqueId());
        FileConfiguration co = data.getConfig();

        ItemStack item;

        int[] border = {0, 1, 2, 3, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35,36,37,38,39,41,42,43,44};
        for (int br : border) {
            item = creator.createItem(Material.GRAY_STAINED_GLASS_PANE);
            menu.setItem(br, item);
        }

        int[] soon = {10,11,12,14,15,16};
        for (int s : soon) {
            item = creator.createItem(Component.text("§cSOON"), Material.ORANGE_STAINED_GLASS_PANE);
            menu.setItem(s, item);
        }

        int sp_x = player.getWorld().getSpawnLocation().getBlockX();
        int sp_y = player.getWorld().getSpawnLocation().getBlockY();
        int sp_z = player.getWorld().getSpawnLocation().getBlockZ();

        item = creator.createItem("§dSpawn", Material.POPPY, Arrays.asList(
                "§7===============",
                "§6Coordinates:",
                "§f" + sp_x + " " + sp_y + " " + sp_z,
                "§7---------------",
                "§7Overworld Spawn Coordinates",
                "§7==============="
        ));
        menu.setItem(4, item);

        item = creator.createItem("§6Create new location", Material.WRITABLE_BOOK, Arrays.asList(
                "§7===============",
                "§aAdds new location coordinates to the list",
                "§7==============="
        ));
        menu.setItem(13, item);

        try{
            if(!(data.isEmpty())){
                for (String lc : co.getConfigurationSection("locations").getKeys(false)) {
                    String url = co.getString("locations." + lc + ".item");
                    item = creator.createItem(Component.text("§6"+ lc), url, Material.GRASS_BLOCK, Arrays.asList(
                            "§7===============",
                            "§6Coordinates:",
                            "§f" + co.getString("locations." + lc + ".coordinates"),
                            "§7---------------",
                            "§6Date of Creation",
                            "§2" + co.getString("locations." + lc + ".date"),
                            "§7==============="
                    ));
                    menu.addItem(item);
                }
            }
        }catch (NullPointerException e){
            player.sendMessage("Error: " + e);
        }

        item = creator.createItem(Component.text("§cClose"),  Material.BARRIER);
        menu.setItem(40, item);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return menu;
    }
}
