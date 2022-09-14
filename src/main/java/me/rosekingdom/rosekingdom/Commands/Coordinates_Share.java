package me.rosekingdom.rosekingdom.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Coordinates_Share implements TabExecutor {

    public int x;
    public int y;
    public int z;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            if(args.length > 0){
                Player target = Bukkit.getPlayer(args[0]);
                x = p.getLocation().getBlockX();
                y = p.getLocation().getBlockY();
                z = p.getLocation().getBlockZ();

                target.sendMessage("§e" + p.getName() + " send you his coordinates §6X:§r " + x + "§6 Y:§r " + y + "§6 Z:§r " + z);
                p.sendMessage("§eYou send your coordinates to " + target.getName());
            }else{
                p.sendMessage("§cSelect a player");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> playerNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            for (int i = 0; i < players.length; i++) {
                playerNames.add(players[i].getName());
            }
            return playerNames;
        }
        if(args.length > 1){
            return List.of();
        }
        return null;
    }
}
