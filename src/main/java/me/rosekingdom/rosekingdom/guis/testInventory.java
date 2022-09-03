package me.rosekingdom.rosekingdom.guis;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class testInventory implements InventoryHolder {

    private Inventory inv;

    public testInventory(){
        inv = Bukkit.createInventory(this,54, "\uF808ㇺ");
    }

    @Override
    public Inventory getInventory(){
        return inv;
    }
}
