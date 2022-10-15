package me.rosekingdom.rosekingdom.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class TestCommand extends Command {

    public TestCommand(JavaPlugin pl){
        super(pl);
        this.addAlias("test");
        this.setSyntax("/test");
    }
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player p){
            p.sendMessage("§2Success!");
        }
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if(args.length == 1){
            List<String> things = new ArrayList<>();
            things.add("test");
            return things;
        }
        if(args.length > 1){
            return List.of();
        }
        return null;
    }
}
