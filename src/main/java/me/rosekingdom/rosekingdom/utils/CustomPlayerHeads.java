package me.rosekingdom.rosekingdom.utils;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;


public class CustomPlayerHeads {

    String base64;
    String url;

    public ItemStack getHead(){
        ItemStack item;
        try {
            item = new ItemStack(Material.PLAYER_HEAD, 1);
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

    public ItemStack getHead(String url){
        setUrl(url);
        ItemStack item;
        try {
            item = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            Server server = Bukkit.getServer();

            PlayerProfile profile = server.createProfile(UUID.randomUUID());

            PlayerTextures textures = profile.getTextures();
            textures.setSkin(new URL(this.url));
            profile.setTextures(textures);

            meta.setOwnerProfile(profile);
            item.setItemMeta(meta);
            return item;
        }catch (MalformedURLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void setUrl(URL url){
        this.url = url.toString();
    }

    public String setUrl(SkullMeta meta){
        try {
            url = meta.getPlayerProfile().getTextures().getSkin().toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    public String getUrl(){
        return url;
    }

    public String getUrl(String base64){
        try {
            url = Arrays.toString(Base64.getUrlDecoder().decode(base64));
        }catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    public String getBase64(){
        base64 = Base64.getUrlEncoder().encodeToString(url.getBytes());
        return base64;
    }
}
