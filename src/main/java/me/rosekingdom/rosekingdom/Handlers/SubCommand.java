package me.rosekingdom.rosekingdom.Handlers;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class SubCommand {

    protected JavaPlugin plugin;
    private ArrayList<String> аliases = new ArrayList<>();
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
        return аliases;
    }

    public void addAlias(String alias){
        аliases.add(alias);
    }

    public void setAliases(ArrayList<String> аliases){
        this.аliases = аliases;
    }

    //======================

    public abstract boolean execute(CommandSender sender, String[] args);

    public abstract List<String> subTabComplete(CommandSender sender, String[] args);
}
