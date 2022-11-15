package me.rosekingdom.rosekingdom.Commands;

import me.rosekingdom.rosekingdom.GUIs.Home.Home_Menu;
import me.rosekingdom.rosekingdom.Handlers.Commands.CommandRK;
import me.rosekingdom.rosekingdom.Handlers.PlayerData;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Home extends CommandRK {

    public Home(JavaPlugin plugin){
        super(plugin);
        this.addAlias("home");
        this.addAlias("h");
        this.addSubCommand("help");
        this.addSubCommand("reset");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)){
           sender.sendMessage("This command can be executed only by players!");
        }
        Player p = (Player) sender;
        PlayerData data = new PlayerData(p.getUniqueId());
        FileConfiguration co = data.getConfig();

        if(args.length > 0){
            for(String location : co.getConfigurationSection("locations").getKeys(false)){
                if(args[0].equals(location)){
                    p.sendMessage("§6"+ location + ": §f" + co.getString("locations." + location + ".coordinates"));
                    return true;
                }
            }
        }

        Home_Menu gui = new Home_Menu(p);
        p.openInventory(gui.getInventory());

        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if(args.length == 1){
            List<String> list = new ArrayList<>();
            list.add("reset");
            list.add("help");

            Player p = (Player) sender;
            PlayerData data = new PlayerData(p.getUniqueId());
            if(!data.isEmpty()){
                list.addAll(data.getConfig().getConfigurationSection("locations").getKeys(false));
            }
            return list;
        }
        if(args.length > 1){
            return List.of();
        }
        return null;
    }
}
