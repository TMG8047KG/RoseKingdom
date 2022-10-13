package me.rosekingdom.rosekingdom.Handlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {

    private JavaPlugin plugin;
    private ArrayList<Command> commands;

    public CommandManager(JavaPlugin pm){
        plugin = pm;
        commands = new ArrayList<>();
        RegisterCommands();
    }

    public void RegisterCommands(){
        for(Command command : getCommands()){
            plugin.getCommand(command.getName()).setExecutor(this);
        }
    }

    public void CommandList(){
        //addCommand();
    }

    public ArrayList<Command> getCommands(){
        return commands;
    }

    public void addCommand(Command command){
        commands.add(command);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
