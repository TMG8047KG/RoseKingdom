package me.rosekingdom.rosekingdom.GUIs.Home;

import com.destroystokyo.paper.profile.PlayerProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerTextures;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class HomeTypeSelectionMenu implements InventoryHolder {

    private Inventory menu;

    public HomeTypeSelectionMenu(){
        menu = Bukkit.createInventory(this, 9, Component.text("Choose type of the location"));
        setup();
    }

    public void setup() {
        int[] empty = {0,1,3,4,5,7,8};
        ItemStack item;
        for(int index : empty){
            item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
            menu.setItem(index, item);
        }
        item = getHead("http://textures.minecraft.net/texture/cf5921c93a006caa574c3a642be43172d354d763127356e3e41179ea7385254c");
        menu.setItem(2,item);
        item = getHead("https://textures.minecraft.net/texture/2c915db3fc40a79b63c2c453f0c490981e5227c5027501283272138533dea519");
        menu.setItem(6, item);
    }

    private ItemStack getHead(String url){
        try {
            ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            Server server = Bukkit.getServer();
            PlayerProfile profile = server.createProfile(UUID.randomUUID());
            PlayerTextures textures = profile.getTextures();
            textures.setSkin(new URL(url));
            profile.setTextures(textures);
            meta.setOwnerProfile(profile);
            item.setItemMeta(meta);
            return item;
        }catch (MalformedURLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public @NotNull Inventory getInventory() {
        return menu;
    }
}
