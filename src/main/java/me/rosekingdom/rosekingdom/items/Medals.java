package me.rosekingdom.rosekingdom.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class Medals {

    public static ItemStack bronze_medal;

    public static void init() {
        bronze_Medal();
    }

    private static void bronze_Medal(){
        ItemStack item = new ItemStack(Material.COPPER_INGOT, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Bronze Medal");
        meta.setCustomModelData(3001);

        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§6§lRight Click to set the Medal");
        lore.add("§7You set the name of the game that is played");
        meta.setLore(lore);
        item.setItemMeta(meta);
        bronze_medal = item;
    }
}
