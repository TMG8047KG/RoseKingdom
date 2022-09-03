package me.rosekingdom.rosekingdom.Handlers;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {

    public abstract String getName();

    public abstract ArrayList<String> getAliases();

    public abstract String getSyntax();

    public abstract boolean execute(CommandSender sender, String args[]);

    public abstract List<String> tabSuggestion(CommandSender sender, String args[]);

    public abstract boolean consoleExecutable();

}
