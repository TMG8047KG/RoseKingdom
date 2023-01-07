package me.rosekingdom.rosekingdom.Handlers.Commands;

import me.rosekingdom.rosekingdom.Commands.*;
import me.rosekingdom.rosekingdom.Commands.SubCommands.home_reset;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {

    private JavaPlugin plugin;
    private ArrayList<CommandRK> commands;
    private ArrayList<SubCommand> subCommands;

    public CommandManager(JavaPlugin pm){
        plugin = pm;
        commands = new ArrayList<>();
        subCommands = new ArrayList<>();
        CommandList();
        SubCommandList();
        RegisterCommands();
    }

    public void addCommand(CommandRK command){
        commands.add(command);
    }
    public void addSubCommand(SubCommand subCommand){
        subCommands.add(subCommand);
    }
    public ArrayList<CommandRK> getCommands(){
        return commands;
    }
    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }

    public void CommandList(){
        addCommand(new Coordinates_Share(plugin));
        addCommand(new SpawnEntity(plugin));
        addCommand(new Heal(plugin));
        addCommand(new Home(plugin));
        addCommand(new test(plugin));
    }

    public void SubCommandList(){
        addSubCommand(new home_reset(plugin));
    }

    public void RegisterCommands(){
        int registeredCommands = 0;
        for(CommandRK command : getCommands()){
            try {
                for(String aliases : command.getAliases()){
                    plugin.getCommand(aliases).setExecutor(this);

                }
                registeredCommands++;
            }catch (Exception e){
                plugin.getLogger().warning("Exception: " + e);
                plugin.getLogger().warning("Name of the command: " + command.getAliases() + ", " + command );
            }
        }
        if (registeredCommands == commands.size()) {
            plugin.getLogger().info("Commands Loaded!");
        } else {
            plugin.getLogger().warning("Missing Commands!");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        for(CommandRK cm : getCommands()){
            if(cm.getAliases().contains(label.toLowerCase())){
                try{
                    if(cm.hasSubCommands() && args.length > 0){
                        for(SubCommand sub : getSubCommands()){
                            for(String aliases : sub.getAliases()){
                                if(args[0].equalsIgnoreCase(aliases)){
                                    sub.execute(sender, args);
                                    return true;
                                }
                            }
                        }
                    }
                    cm.execute(sender, args);
                    return true;
                }catch (Exception e){
                    plugin.getLogger().warning(sender.getName() + "'s execution of " + command + " did not work!");
                    sender.sendMessage("§cSomething went wrong! Report to the Owner!");
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        List<String> list;
        for(CommandRK cmd : getCommands()){
            if(cmd.getAliases().contains(label.toLowerCase())){
                try {
                    list = cmd.tabComplete(sender, args);
                    if(list == null){
                        return List.of();
                    }else{
                        return list;
                    }
                }catch (Exception e){
                    plugin.getLogger().warning("§c"+ sender + " tab completion for the " + label + " doesn't work!");
                    plugin.getLogger().warning("Error: " + e);
                }
            }
        }
        return null;
    }

    //TODO find how to use permissions and how to make them
//    public enum Requirement{
//        PLAYER, DEFAULT, ADMIN, MODERATOR, HELPER, OP
//    }
}
