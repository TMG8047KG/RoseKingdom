package me.rosekingdom.rosekingdom.Listeners.GUI.Home;

import me.rosekingdom.rosekingdom.GUIs.Home.HomeConfirmationMenu;
import me.rosekingdom.rosekingdom.GUIs.Home.HomeTypeSelectionMenu;
import me.rosekingdom.rosekingdom.GUIs.Home.Home_Menu;
import me.rosekingdom.rosekingdom.utils.PlayerData;
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


public class HomeConfirmationEvent implements Listener {

    public String name;

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getClickedInventory() == null){
            return;
        }
        if(e.getClickedInventory().getHolder() instanceof HomeConfirmationMenu){
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();

            Home_Menu menu = new Home_Menu(player);

            int x = player.getLocation().getBlockX();
            int y = player.getLocation().getBlockY();
            int z = player.getLocation().getBlockZ();

//            if((e.getSlot()==3) && e.getCurrentItem().displayName().equals(Component.text(x+ " " + y + " " + z))) {
//                Location location = new Location(player.getWorld(), x, -64, z);
//                player.getWorld().setBlockData(location, Material.SPRUCE_SIGN.createBlockData());
//                Sign sign = (Sign) player.getWorld().getBlockAt(location).getState();
//                setSign(sign);
//                //getLogger().info(sign.lines().toString());
//
//                if (e.getCurrentItem() == null) {
//                    return;
//                }
//                if (e.getCurrentItem().getType().equals(Material.GREEN_STAINED_GLASS_PANE)) {
//                    player.openSign(sign);
//                }
//                if (e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)) {
//                    deleteSign(player, location);
//                    player.openInventory(menu.getInventory());
//                }
//            }
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

        int z = player.getLocation().getBlockZ();
        Location location = new Location(player.getWorld(), x, -64, z);

        if(location.getBlock().equals(event.getBlock())){
            TextComponent text = (TextComponent) event.line(0);
            name = text.content();
            name = name.replaceAll(" ", "_");

            if(!name.isEmpty()){
                if(!pData.isEmpty()){
                    for (String names : pcd.getConfigurationSection("locations").getKeys(false)) {
                        if(name.equals(names)){
                            player.sendMessage("§cYou already have \""+ name + "\"");
                            return;
                        }
                    }
                }
                pcd.set("temp",name);
                pData.save();
                HomeTypeSelectionMenu menu = new HomeTypeSelectionMenu();
                player.openInventory(menu.getInventory());
            }else {
                player.sendMessage("§cPlease enter valid name!");
            }
            deleteSign(player,location);
        }
    }
}
