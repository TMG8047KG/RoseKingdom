package me.rosekingdom.rosekingdom.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemCreator {

    public ItemStack createItem(String name, String url, Material material, List<String> lore, int CMD){
        CustomPlayerHeads heads = new CustomPlayerHeads();
        ItemStack item = new ItemStack(material, 1);
        if(url != null){
            item = heads.getHead(url);
        }
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        if(CMD != 0){
            meta.setCustomModelData(CMD);
        }
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createItem(TextComponent name, String url, Material material, List<String> lore){
        CustomPlayerHeads heads = new CustomPlayerHeads();
        ItemStack item = new ItemStack(material, 1);
        if(url != null){
            item = heads.getHead(url);
        }
        ItemMeta meta = item.getItemMeta();
        meta.displayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createItem(String name, Material material, List<String> lore){
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createItem(TextComponent name, Material material, List<Component> lore){
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(name);
        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createItem(TextComponent name, Material material, List<Component> lore, int cmd){
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(name);
        meta.lore(lore);
        meta.setCustomModelData(cmd);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createItem(TextComponent name, Material material){
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(name);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createItem(Material material){
        return new ItemStack(material, 1);
    }

    public ItemStack setItem(ItemStack item, TextComponent name, List<Component> lore){
        ItemMeta meta = item.getItemMeta();
        meta.displayName(name);
        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
