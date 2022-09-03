package me.rosekingdom.rosekingdom.commands;


import me.rosekingdom.rosekingdom.Handlers.Command;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class SpawnEntity extends Command {

}

//    if(args.length == 1) {
//            List<String> Entities = new ArrayList<>();
//            for (EntityType value : EntityType.values()){
//                Entities.add(String.valueOf(value));
//            }
//            return Entities;
//        }
//        return null;


//try {
//           EntityType entity = EntityType.valueOf(args[1].toUpperCase());
//           int amount = Integer.parseInt(args[2]);
//           for(int i = 0; i < amount; i++){
//               player.getWorld().spawnEntity(player.getLocation(), entity);
//           }
//       }catch (IllegalArgumentException e){
//           player.sendMessage("§cThat is not a valid entity!");
//       }