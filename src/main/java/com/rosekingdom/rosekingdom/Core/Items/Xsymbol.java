package com.rosekingdom.rosekingdom.Core.Items;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.CustomModelData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class Xsymbol extends ItemStack {
    public Xsymbol(String title, List<Component> lore){
        super(Material.STICK);
        setAmount(1);
        ItemMeta meta = getItemMeta();
        setData(DataComponentTypes.CUSTOM_MODEL_DATA, CustomModelData.customModelData().addString("cross"));
        meta.displayName(Component.text(title).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        setItemMeta(meta);
    }
}
