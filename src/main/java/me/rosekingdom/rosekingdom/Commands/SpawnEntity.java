package me.rosekingdom.rosekingdom.Commands;

import me.rosekingdom.rosekingdom.Handlers.CommandRK;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class SpawnEntity extends CommandRK {

    public SpawnEntity(JavaPlugin plugin){
        super(plugin);
        this.addAlias("spawnentity");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        try {
            EntityType entity = EntityType.valueOf(args[0].toUpperCase());
            int amount = Integer.parseInt(args[1]);
            for(int i = 0; i < amount; i++){
                player.getWorld().spawnEntity(player.getLocation(), entity);
            }
        }catch (IllegalArgumentException e){
            player.sendMessage("§cThat is not a valid entity!");
        }
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if(args.length == 1) {
            List<String> Entities = new ArrayList<>();
            for (EntityType value : EntityType.values()) {
                Entities.add(String.valueOf(value));
            }
            return Entities;
        }
        if(args.length > 1){
            return List.of();
        }
        return null;
    }
}
