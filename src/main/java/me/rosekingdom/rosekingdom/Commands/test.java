package me.rosekingdom.rosekingdom.Commands;

import me.rosekingdom.rosekingdom.Handlers.Commands.CommandRK;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;
import java.util.List;

public class test extends CommandRK {
    public test(JavaPlugin pl){
        super(pl);
        this.addAlias("test");
    }
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player player){
            Title test = Title.title(Component.text("RoseKingdom", TextColor.fromHexString("#d11515")),Component.text("Welcome to the lands!", TextColor.fromHexString("#f5931b")), Title.Times.times(Duration.ofSeconds(1),Duration.ofSeconds(5),Duration.ofSeconds(1)));
            player.showTitle(test);
        }
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
