package me.rosekingdom.rosekingdom.Listeners.GUI.Home;

import me.rosekingdom.rosekingdom.GUIs.Home.HomeSelectionMenu;
import me.rosekingdom.rosekingdom.GUIs.Home.Home_Menu;
import me.rosekingdom.rosekingdom.Handlers.PlayerData;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;


import java.time.LocalDate;

import static org.bukkit.Bukkit.getLogger;


public class HomeSelectionMenuEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getClickedInventory() == null){
            return;
        }
        if(e.getClickedInventory().getHolder() instanceof HomeSelectionMenu){
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();

            Home_Menu menu = new Home_Menu(player);

            int x = player.getLocation().getBlockX();
            int z = player.getLocation().getBlockZ();

            Location location = new Location(player.getWorld(), x, -64, z);
            player.getWorld().setBlockData(location, Material.SPRUCE_SIGN.createBlockData());
            Sign sign = (Sign) player.getWorld().getBlockAt(location).getState();
            setSign(sign);
            getLogger().info(sign.lines().toString());

            if(e.getCurrentItem() == null){
                return;
            }
            if(e.getCurrentItem().getType().equals(Material.GREEN_STAINED_GLASS_PANE)){
                player.openSign(sign);
            }
            if(e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)){
                //TODO remove
                player.sendMessage("§cDenied");
                deleteSign(player, location);
                player.openInventory(menu.getInventory());
            }
        }
    }

    private void deleteSign(Player player, Location location){
        player.getWorld().setBlockData(location, Material.BEDROCK.createBlockData());
    }

    private void setSign(Sign s){
        Sign sign;
        sign = s;
        sign.setEditable(true);
        sign.line(0, Component.text(""));
        sign.line(1, Component.text("^^^^^^^^^^"));
        sign.line(2, Component.text("Name"));
        sign.line(3, Component.text("=========="));
        sign.update(true);
    }

    @EventHandler
    public void singChange(SignChangeEvent event){
        Player player = event.getPlayer();
        PlayerData pData = new PlayerData(player.getUniqueId());
        FileConfiguration pcd = pData.getConfig();

        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        Location location = new Location(player.getWorld(), x, -64, z);

        if(location.getBlock().equals(event.getBlock())){
            TextComponent text = (TextComponent) event.line(0);
            String name = text.content();

            if(!name.isEmpty()){
                if(!pData.isEmpty()){
                    for (String names : pcd.getConfigurationSection("locations").getKeys(false)) {
                        if(name.equals(names)){
                            player.sendMessage("§cYou already have \""+ name + "\"");
                            return;
                        }
                    }
                }
                pcd.set("locations." + name + ".coordinates", x + " " + y + " " + z);
                pcd.set("locations." + name + ".date", LocalDate.now().toString());
                pData.save();
                player.sendMessage("§aSuccessfully created new location \""+ name + "\"");
            }else {
                player.sendMessage("§cPlease enter valid name!");
            }
            deleteSign(player,location);
        }
    }
}
