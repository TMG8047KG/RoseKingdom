package me.rosekingdom.rosekingdom.Handlers.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class SubCommand {

    protected JavaPlugin plugin;

    private ArrayList<String> subAlieses = new ArrayList<>();
    private String syntax;
    private String description;

    public SubCommand(JavaPlugin pl){
        plugin = pl;
    }

    //=====================

    public String getDescription(){
        return description;
    }

    public void addDescription(String description){
        this.description = description;
    }

    //======================

    public String getSyntax(){
        return syntax;
    }

    public void setSyntax(){
        this.syntax = syntax;
    }

    //=====================

    public ArrayList<String> getAliases(){
        return subAlieses;
    }

    public void addAlias(String alias){
        subAlieses.add(alias);
    }

    public void setAliases(ArrayList<String> aliases){
        this.subAlieses = aliases;
    }

    //======================

    public abstract boolean execute(CommandSender sender, String[] args);

    public abstract List<String> subTabComplete(CommandSender sender, String[] args);
}
