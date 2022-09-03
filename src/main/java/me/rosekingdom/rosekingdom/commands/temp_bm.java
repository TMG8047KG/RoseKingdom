package me.rosekingdom.rosekingdom.commands;

import me.rosekingdom.rosekingdom.items.Medals;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class temp_bm implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("This command can be executed only by players!");
            return true;
        }
        Player player = (Player) sender;

        if(command.getName().equalsIgnoreCase("medal")){
            player.getInventory().addItem(Medals.bronze_medal);
        }

        return true;
    }
}
