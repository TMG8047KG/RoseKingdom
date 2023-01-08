package me.rosekingdom.rosekingdom.GUIs.Home;

import me.rosekingdom.rosekingdom.utils.CustomPlayerHeads;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class HomeTypeSelectionMenu implements InventoryHolder {

    private Inventory menu;

    public HomeTypeSelectionMenu(){
        menu = Bukkit.createInventory(this, 9, Component.text("Choose type of the location"));
        setup();
    }

    public void setup() {
        int[] empty = {0,1,3,4,5,7,8};
        ItemStack item;
        for(int index : empty){
            item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
            menu.setItem(index, item);
        }
        CustomPlayerHeads heads = new CustomPlayerHeads();
        item = heads.getHead("http://textures.minecraft.net/texture/cf5921c93a006caa574c3a642be43172d354d763127356e3e41179ea7385254c");
        menu.setItem(2,item);
        item = heads.getHead("https://textures.minecraft.net/texture/2c915db3fc40a79b63c2c453f0c490981e5227c5027501283272138533dea519");
        menu.setItem(6, item);
    }


    @Override
    public @NotNull Inventory getInventory() {
        return menu;
    }
}
