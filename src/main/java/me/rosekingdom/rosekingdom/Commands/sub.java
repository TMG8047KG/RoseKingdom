package me.rosekingdom.rosekingdom.Commands;

import me.rosekingdom.rosekingdom.Handlers.CommandRK;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class sub extends CommandRK {

    public sub(JavaPlugin plugin){
        super(plugin);
        this.addAlias("sub");
        this.addSubCommand("test1");
        this.addSubCommand("test2");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player player){
            player.sendMessage("/sub <subcom>");
            for(String com : this.getSubCommands()){
                player.sendMessage(com);
            }
            return true;
        }
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if(args.length == 1){
            List<String> things = new ArrayList<>();
            things.add("test1");
            things.add("test2");
            return things;
        }
        if(args.length > 1){
            return List.of();
        }
        return null;
    }
}
