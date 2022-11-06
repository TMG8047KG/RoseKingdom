package me.rosekingdom.rosekingdom.Commands.SubCommands;

import me.rosekingdom.rosekingdom.Handlers.Commands.SubCommand;
import me.rosekingdom.rosekingdom.Handlers.PlayerData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class home_reset extends SubCommand {

    public home_reset(JavaPlugin plugin){
        super(plugin);
        this.addAlias("reset");
        this.addAlias("r");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player player){
            PlayerData pData = new PlayerData(player.getUniqueId());
            pData.reset();
            player.sendMessage("§cConfig reset!");
        }
        return false;
    }

    @Override
    public List<String> subTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
