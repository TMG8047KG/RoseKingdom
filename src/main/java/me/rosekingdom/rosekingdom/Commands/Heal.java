package me.rosekingdom.rosekingdom.Commands;

import me.rosekingdom.rosekingdom.Handlers.Commands.CommandRK;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Heal extends CommandRK {

    public Heal(JavaPlugin plugin){
        super(plugin);
        this.addAlias("heal");
//        this.addRequirement(Requirement.OP);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player player){
            if(player.isOp()){
                player.setHealth(player.getMaxHealth());
                player.setFoodLevel(20);
                player.sendMessage("§aHealed!");
            }else {
                player.sendMessage("§cYou don't have permissions!");
            }
        }else{
            sender.sendMessage("§cOnly players can run this command!");
        }
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
