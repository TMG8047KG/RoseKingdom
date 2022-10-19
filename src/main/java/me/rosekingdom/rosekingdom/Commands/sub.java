package me.rosekingdom.rosekingdom.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class sub extends CommandCore{

    public sub(JavaPlugin pl){
        super(pl);
        this.addSubCommand("test1");
        this.addSubCommand("test2");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if(args.length == 1){
            List<String> things = new ArrayList<>();
            things.add("test1");
            things.add("test2");
            return things;
        }
        return null;
    }
}
