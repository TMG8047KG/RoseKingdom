package me.rosekingdom.rosekingdom.Commands.SubCommands;

import me.rosekingdom.rosekingdom.Handlers.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Test1 extends SubCommand {

    public Test1(JavaPlugin plugin) {
        super(plugin);
        this.addAlias("test1");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage("§cTest1 Success");
        return true;
    }

    @Override
    public List<String> subTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
