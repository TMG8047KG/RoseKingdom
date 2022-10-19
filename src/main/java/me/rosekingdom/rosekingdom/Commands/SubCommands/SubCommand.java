package me.rosekingdom.rosekingdom.Commands.SubCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class SubCommand {

    protected JavaPlugin plugin;

    private ArrayList<String> subAliases = new ArrayList<>();

    private String syntax;

    private String description;

    public SubCommand(JavaPlugin pl){
        plugin = pl;
    }

    //=====================

    public String getSubDescription(){
        return description;
    }

    public void addSubDescription(String description){
        this.description = description;
    }

    //======================

    public String getSubSyntax(){
        return syntax;
    }

    public void setSubSyntax(){
        this.syntax = syntax;
    }

    //=====================

    public ArrayList<String> getSubAliases(){
        return subAliases;
    }

    public void addSubAlias(String alias){
        subAliases.add(alias);
    }

    public void setSubAliases(ArrayList<String> subAliases){
        this.subAliases = subAliases;
    }

    //======================

    public abstract boolean execute(CommandSender sender, String[] args);

    public abstract List<String> subTabComplete(CommandSender sender, String[] args);
}
