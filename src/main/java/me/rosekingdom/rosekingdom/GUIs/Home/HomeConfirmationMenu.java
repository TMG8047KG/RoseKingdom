package me.rosekingdom.rosekingdom.GUIs.Home;

import me.rosekingdom.rosekingdom.utils.ItemCreator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class HomeConfirmationMenu implements InventoryHolder {

    private Inventory menu;

    public HomeConfirmationMenu(TextComponent name, List<Component> lore){
        menu = Bukkit.createInventory(this, 9, Component.text("Are you sure you want to proceed"));
        setup(name, lore);
    }

    public void setup(TextComponent name, List<Component> lore){

        ItemCreator creator = new ItemCreator();
        ItemStack item;
        for(int k=0;k<=3; k++){
            item = creator.createItem("Deny", Material.RED_STAINED_GLASS_PANE, Collections.singletonList("You cancel the operation!"));
            menu.setItem(k, item);
        }
        item = creator.createItem(name, Material.WRITTEN_BOOK, lore);
        menu.setItem(4, item);
        for(int k=5;k<9; k++){
            item = creator.createItem("Accept", Material.GREEN_STAINED_GLASS_PANE , Collections.singletonList("You allow the operation to proceed!"));
            menu.setItem(k, item);
        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        return menu;
    }
}
