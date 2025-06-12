package com.rosekingdom.rosekingdom.Profiles.Items;

import com.rosekingdom.rosekingdom.Profiles.Statements.ProfileStatement;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.CustomModelData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class StreakScore extends ItemStack {
    public StreakScore(OfflinePlayer player){
        super(Material.PAPER);
        setAmount(1);
        setData(DataComponentTypes.CUSTOM_MODEL_DATA, CustomModelData.customModelData().addString("pf_streak"));
        ItemMeta meta = getItemMeta();
        meta.displayName(Component.text("Daily Streak: " + ProfileStatement.getStreak(player), TextColor.fromHexString("#fa9e14"))
                .decoration(TextDecoration.ITALIC, false));
        meta.lore(List.of(Component.text("Daily Streak Highscore: " + ProfileStatement.getHighscore(player), TextColor.fromHexString("#fa9e14"))
                .decoration(TextDecoration.ITALIC, false)));
        setItemMeta(meta);
    }
}
