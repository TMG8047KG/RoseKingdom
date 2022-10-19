package me.rosekingdom.rosekingdom.Commands.SubCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Test2 extends SubCommand{

    public Test2(JavaPlugin plugin) {
        super(plugin);
        this.addSubAlias("test2");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        sender.sendMessage("§cTest2 Success");
        return false;
    }

    @Override
    public List<String> subTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
