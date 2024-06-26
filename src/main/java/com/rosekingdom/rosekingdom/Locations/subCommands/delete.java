package com.rosekingdom.rosekingdom.Locations.subCommands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Locations.Statements.LocationStatement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class delete extends subCommandRK {
        public delete(int arg){
            super(arg);
            setName("delete");
            addAlias("remove");
        }

        @Override
        public void executeSub(CommandSender sender, String[] args) {
            if(!(sender instanceof Player player)){
                return;
            }
            int id = UserStatement.getId(player.getUniqueId());
            if(args.length==2 && LocationStatement.exists(id, args[1])){
                LocationStatement.deleteLocation(id, args[1]);
                player.sendMessage(Component.text("Location " + args[1] + " was deleted!", TextColor.fromHexString("#6be649")));
            }else{
                player.sendMessage(Message.Warning("Missing arguments!"));
            }
        }
    }
