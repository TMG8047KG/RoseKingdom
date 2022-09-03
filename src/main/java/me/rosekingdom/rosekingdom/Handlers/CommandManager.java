package me.rosekingdom.rosekingdom.Handlers;

import me.rosekingdom.rosekingdom.commands.SpawnEntity;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {
    private ArrayList<Command> commands = new ArrayList<>();
    private JavaPlugin plugin;


    //List with all commands
    public void Commands(){
        addCommand(new SpawnEntity());
    }

    public void addCommand(Command command){
        commands.add(command);
    }

    public ArrayList<Command> getCommands(){
        return commands;
    }

    public void registerCommands() {
        for (Command c : this.getCommands()){
            for(String i : c.getAliases()){
                plugin.getCommand(i).setExecutor(this);
            }
        }
    }

    //Main function
    public CommandManager(JavaPlugin pl){
        plugin = pl;
        Commands();
        registerCommands();
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {



        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        return null;
    }
}
