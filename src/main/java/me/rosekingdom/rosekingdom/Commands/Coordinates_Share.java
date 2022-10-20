package me.rosekingdom.rosekingdom.Commands;

import me.rosekingdom.rosekingdom.Handlers.CommandRK;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class Coordinates_Share extends CommandRK {

    public int x;
    public int y;
    public int z;
    public World.Environment dimension;
    private boolean nonPlayer;

    public Coordinates_Share(JavaPlugin plugin){
        super(plugin);
        this.addName("coords");
        this.addAlias("xyz");
        this.addAlias("crd");
        this.addAlias("cd");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player p){

            if(args.length > 0){
                Player target;
                x = p.getLocation().getBlockX();
                y = p.getLocation().getBlockY();
                z = p.getLocation().getBlockZ();

                //TODO:
                // Add Dimension to the output message
                dimension = p.getWorld().getEnvironment();

                Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                Bukkit.getServer().getOnlinePlayers().toArray(players);

                try{
                    if(args[0].contains("all")){
                        for (Player player : players) {
                            if (!(player.getName().equals(p.getName()))) {
                                player.sendMessage("§e" + p.getName() + " send his (current) coordinates to everyone §6X:§r " + x + "§6 Y:§r " + y + "§6 Z:§r " + z);
                            }
                        }
                    }else{

                        for(Player player : players){
                            if(args[0].equals(player.getName())){
                                nonPlayer = false;
                                break;
                            }else {
                                nonPlayer = true;
                            }
                        }
                        if(nonPlayer){
                            p.sendMessage("§cPlayer not found!");
                            return true;
                        }
                        target = Bukkit.getPlayer(args[0]);
                        if(target.getName().equals(p.getName())){
                            p.sendMessage("§cYou can't send your coordinates to yourself!");
                            return true;
                        }

                        target.sendMessage("§e" + p.getName() + " send his (current) coordinates to you§6X:§r " + x + "§6 Y:§r " + y + "§6 Z:§r " + z);
                        p.sendMessage("§eYou send your coordinates to " + target.getName());
                    }
                }catch (Exception e){
                    getLogger().warning("Error: " + e);
                }
            }else{
                p.sendMessage("§cSelect someone by typing their username!");
            }
        }
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            Player player = (Player) sender;
            List<String> playerNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            playerNames.add("all");
            for (Player value : players) {
                if (!(value.getName().equals(player.getName()))) {
                    playerNames.add(value.getName());
                }
            }
            return playerNames;
        }
        if(args.length > 1){
            return List.of();
        }
        return null;
    }
}
