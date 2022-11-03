package me.rosekingdom.rosekingdom.Handlers;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import me.rosekingdom.rosekingdom.Handlers.CommandManager.Requirement;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandRK {

    protected JavaPlugin plugin;
    private ArrayList<String> aliases = new ArrayList<>();
    private ArrayList<String> subCommands = new ArrayList<>();
    private ArrayList<Requirement> requirements = new ArrayList<>();
    private String syntax;
    private String description;

    public CommandRK(JavaPlugin pl){
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

    //-----------------------

    public ArrayList<String> getSubCommands(){
        return subCommands;
    }

    public void addSubCommand(String subCommand){
        subCommands.add(subCommand);
    }

    public void setSubCommands(ArrayList<String> subCommands){
        this.subCommands = subCommands;
    }

    public boolean hasSubCommands(){
        return subCommands.size() != 0;
    }

    //------------------
    //Additional Command Info
    //------------------

    public void setSyntax(String syntax){
        this.syntax = syntax;
    }

    public String getSyntax() {
        return syntax;
    }

    //-------------------

    public void addDescription(String description){
        this.description =  description;
    }

    public String getDescription(){
        return description;
    }

    //Main Methods
    public abstract boolean execute(CommandSender sender, String[] args);

    public abstract List<String> tabComplete(CommandSender sender, String[] args);

    //--------------------

    public ArrayList<Requirement> getRequirements() {
        return requirements;
    }

    public void addRequirement(Requirement requirement) {
        requirements.add(requirement);
    }

    public void setRequirements(ArrayList<Requirement> requirements) {
        this.requirements = requirements;
    }


    public boolean hasRequirement(CommandSender sender, Requirement requirement){
        switch (requirement) {
            case PLAYER:
                if (!(sender instanceof Player)) {
                    return false;
                }
                break;
            case OP:
                if (!(sender instanceof Player)) {
                    if (!sender.isOp()) {
                        return false;
                    }
                }
                break;
        }return true;
    }
}
