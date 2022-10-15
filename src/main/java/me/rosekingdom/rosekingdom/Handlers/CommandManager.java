package me.rosekingdom.rosekingdom.Handlers;

import me.rosekingdom.rosekingdom.Commands.Command;
import me.rosekingdom.rosekingdom.Commands.TestCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {

    private JavaPlugin plugin;
    private ArrayList<Command> commands;

    public CommandManager(JavaPlugin pm){
        plugin = pm;
        commands = new ArrayList<>();
        CommandList();
        RegisterCommands();
    }

    public void addCommand(Command command){
        commands.add(command);
    }

    public ArrayList<Command> getCommands(){
        return commands;
    }
    public void CommandList(){
        addCommand(new TestCommand(plugin));
    }
    public void RegisterCommands(){
        for(Command command : getCommands()){
            for(String aliases : command.getAliases()){
                plugin.getCommand(aliases).setExecutor(this);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        for(Command cm : getCommands()){
            if(cm.getAliases().contains(label.toLowerCase())){
                try{
                    cm.execute(sender, args);
                }catch (Exception e){
                    sender.sendMessage("§cSomething went wrong!");
                }
            }return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        return null;
    }
}
