package me.rosekingdom.rosekingdom.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpawnEntity implements TabExecutor {

    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String label,  String[] args) {
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
    public List<String> onTabComplete( CommandSender sender,  Command command,  String label,  String[] args) {
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
