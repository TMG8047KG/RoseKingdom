package me.rosekingdom.rosekingdom.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {

    protected JavaPlugin plugin;
    private ArrayList<String> aliases = new ArrayList<>();
    private String syntax;
    private String description;

    public Command(JavaPlugin pl){
        plugin = pl;
    }

    /*========================
     Command name and it's alternatives
     ========================*/

    public ArrayList<String> getAliases(){
        return aliases;
    }

    public void addAlias(String alias){
        aliases.add(alias);
    }

    public void setAliases(ArrayList<String> aliases) {
        this.aliases = aliases;
    }

    //------------------

    public void setSyntax(String syntax){
        this.syntax = syntax;
    }

    public String getSyntax() {
        return syntax;
    }

    public abstract boolean execute(CommandSender sender, String[] args);

    public abstract List<String> tabComplete(CommandSender sender, String[] args);
}
