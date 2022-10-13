package me.rosekingdom.rosekingdom.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {

    protected JavaPlugin plugin;

    private ArrayList<String> aliases = new ArrayList<>();
    private String Command;
    private String Syntax;

    public Command(JavaPlugin pl){
        plugin = pl;
    }

    public void addCommand(String command){
        this.Command = command;
    }

    public String getCommnad(){
        return Command;
    }

    public void setSyntax(String syntax){
        this.Syntax = syntax;
    }

    public String getSyntax() {
        return Syntax;
    }

    public abstract boolean execute(CommandSender sender, String[] args);

    public abstract List<String> tabComplete(CommandSender sender, String[] args);
}
