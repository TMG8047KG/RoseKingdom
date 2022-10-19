package me.rosekingdom.rosekingdom.Handlers;

import me.rosekingdom.rosekingdom.Commands.CommandCore;
import me.rosekingdom.rosekingdom.Commands.Coordinates_Share;
import me.rosekingdom.rosekingdom.Commands.SpawnEntity;
import me.rosekingdom.rosekingdom.Commands.SubCommands.SubCommand;
import me.rosekingdom.rosekingdom.Commands.SubCommands.Test1;
import me.rosekingdom.rosekingdom.Commands.SubCommands.Test2;
import me.rosekingdom.rosekingdom.Commands.sub;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {

    private JavaPlugin plugin;
    private ArrayList<CommandCore> commands;
    private ArrayList<SubCommand> subCommands;

    public CommandManager(JavaPlugin pm){
        plugin = pm;
        commands = new ArrayList<>();
        subCommands = new ArrayList<>();
        CommandList();
        SubCommandList();
        RegisterCommands();
    }

    public void addCommand(CommandCore command){
        commands.add(command);
    }

    public void addSubCommand(SubCommand subCommand){
        subCommands.add(subCommand);
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }

    public ArrayList<CommandCore> getCommands(){
        return commands;
    }

    public void CommandList(){
        addCommand(new Coordinates_Share(plugin));
        addCommand(new SpawnEntity(plugin));
        addCommand(new sub(plugin));
    }

    public void SubCommandList(){
        addSubCommand(new Test1(plugin));
        addSubCommand(new Test2(plugin));

    }

    public void RegisterCommands(){
        for(CommandCore command : getCommands()){
            for(String aliases : command.getAliases()){
                plugin.getCommand(aliases).setExecutor(this);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        for(CommandCore cm : getCommands()){
            if(cm.getAliases().contains(label.toLowerCase())){
                try{
                    if(!(cm.getSubCommands().isEmpty())){
                        for(SubCommand sc : getSubCommands()){
                            if((cm.getSubCommands().contains(sc.getSubSyntax())) && sc.getSubAliases().contains(label.toLowerCase())){
                                sc.execute(sender, args);
                            }
                        }
                    }
                    cm.execute(sender, args);
                }catch (Exception e){
                    sender.sendMessage("§cSomething went wrong! Report to the Owner!");
                }
            }return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        for(CommandCore cmd : getCommands()){
            if(cmd.getAliases().contains(label.toLowerCase())){
                try {
                    return cmd.tabComplete(sender, args);
                }catch (Exception e){
                    plugin.getLogger().warning("Command's TabCompletion doesn't work!");
                }
            }
        }
        return null;
    }
}
