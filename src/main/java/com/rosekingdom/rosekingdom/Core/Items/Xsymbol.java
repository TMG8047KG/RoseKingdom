package com.rosekingdom.rosekingdom.Core.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Xsymbol extends ItemStack {
    public Xsymbol(String title, List<Component> lore){
        super(Material.STICK);
        setAmount(1);
        ItemMeta meta = getItemMeta();
        meta.setCustomModelData(2);
        meta.displayName(Component.text(title).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        setItemMeta(meta);
    }
}
