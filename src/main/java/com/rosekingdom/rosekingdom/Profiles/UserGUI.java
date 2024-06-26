package com.rosekingdom.rosekingdom.Profiles;

import com.rosekingdom.rosekingdom.Core.Items.InvsibleItem;
import com.rosekingdom.rosekingdom.Profiles.Items.ActivityIndicator;
import com.rosekingdom.rosekingdom.Profiles.Items.PlayerHead;
import com.rosekingdom.rosekingdom.Profiles.Items.ProfilePlayTime;
import com.rosekingdom.rosekingdom.Profiles.Items.StreakScore;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class UserGUI implements InventoryHolder{

    OfflinePlayer player;

    public UserGUI(OfflinePlayer player){
        this.player = player;
    }

    @Override
    public @NotNull Inventory getInventory() {
        Inventory profile = Bukkit.createInventory(this, InventoryType.CHEST, Component.text("\u00A7f\uDAFF\uDFF8\uEFB0"));
        int[] playerHead = {0,1,2,9,10,11,18,19,20};
        for(int i : playerHead){
            profile.setItem(i, new InvsibleItem(Component.text(player.getName(), TextColor.fromHexString("#9c9c9c"))));
        }
        profile.setItem(10, new PlayerHead(player));
        profile.setItem(21, new ProfilePlayTime(player));
        profile.setItem(8, new ActivityIndicator(player));
        profile.setItem(22, new StreakScore(player));
        return profile;
    }
}
