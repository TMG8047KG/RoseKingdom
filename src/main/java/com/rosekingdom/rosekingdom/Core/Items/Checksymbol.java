package com.rosekingdom.rosekingdom.Core.Items;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.CustomModelData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Checksymbol extends ItemStack {
    @SuppressWarnings("UnstableApiUsage")
    public Checksymbol(String title, List<Component> lore){
        setAmount(1);
        setData(DataComponentTypes.CUSTOM_MODEL_DATA, CustomModelData.customModelData().addString("checkmark"));
        ItemMeta meta = getItemMeta();
        meta.displayName(Component.text(title).decoration(TextDecoration.ITALIC, false));
        meta.lore(lore);
        setItemMeta(meta);
    }
}
