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
public class InvsibleItem extends ItemStack {
    public InvsibleItem(Component name) {
        super(Material.PAPER);
        setData(DataComponentTypes.CUSTOM_MODEL_DATA, CustomModelData.customModelData().addString("invisible"));
        ItemMeta meta = getItemMeta();
        meta.displayName(name.decoration(TextDecoration.ITALIC, false));
        setItemMeta(meta);
    }

    public InvsibleItem(Component name, List<Component> lore) {
        super(Material.PAPER);
        setData(DataComponentTypes.CUSTOM_MODEL_DATA, CustomModelData.customModelData().addString("invisible"));
        ItemMeta meta = getItemMeta();
        meta.displayName(name.decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        setItemMeta(meta);
    }
}
