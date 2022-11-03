package me.rosekingdom.rosekingdom.Commands;

import me.rosekingdom.rosekingdom.Handlers.CommandManager.Requirement;
import me.rosekingdom.rosekingdom.Handlers.CommandRK;
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
            player.setHealth(player.getMaxHealth());
        }else{
            sender.sendMessage("This command can be executed only by players");
        }
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
