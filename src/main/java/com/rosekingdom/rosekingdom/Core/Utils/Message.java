package com.rosekingdom.rosekingdom.Core.Utils;

import com.rosekingdom.rosekingdom.RoseKingdom;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class Message {
    protected static Plugin plugin = RoseKingdom.getPlugin(RoseKingdom.class);
    public static void Console(String text){
        plugin.getLogger().info(text);
    }

    public static void Console(Component message) {
        plugin.getComponentLogger().info(message);
    }

    public static void Exception(String error, Exception e){
        plugin.getLogger().warning(error);
        plugin.getLogger().warning(e.getMessage());
    }

    public static void Global(String text){
        Bukkit.broadcast(Component.text(text));
    }
    public static void Global(TextComponent text) {
        Bukkit.broadcast(text);
    }

    public static Component Warning(String text){
        return Component.text("\uEf10\uDB00\uDC01")
                .append(Component.text(text))
                .color(TextColor.fromHexString("#FF0000"));
    }
    public static Component Info(String text){
        return Component.text("\uEf10\uDB00\uDC01")
                .append(Component.text(text))
                .color(TextColor.fromHexString("#ebb22f"));
    }

    public static Component Text(String text, String color){
        return Component.text(text)
                .color(TextColor.fromHexString(color));
    }

    public static Component Gold(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#FFA500"));
    }
    public static Component Red(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#FF0000"));
    }
    public static Component Green(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#006400"));
    }
    public static Component Lime(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#32CD32"));
    }
    public static Component LightBlue(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#00FFFF"));
    }
    public static Component Purple(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#A020F0"));
    }
    public static Component Blue(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#0000FF"));
    }
    public static Component Orange(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#ff7d00"));
    }
    public static Component Magenta(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#FF00FF"));
    }
    public static Component Pink(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#FFc0cb"));
    }
    public static Component Bronze(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#CE8946"));
    }
    public static Component Black(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#000"));
    }
    public static Component Gray(String text){
        return Component.text(text)
                .color(TextColor.fromHexString("#808080"));
    }

    public static Component Gold(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#FFA500"));
    }
    public static Component Red(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#FF0000"));
    }
    public static Component Green(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#006400"));
    }
    public static Component Lime(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#32CD32"));
    }
    public static Component LightBlue(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#00FFFF"));
    }
    public static Component Purple(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#A020F0"));
    }
    public static Component Blue(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#0000FF"));
    }
    public static Component Orange(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#ff7d00"));
    }
    public static Component Magenta(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#FF00FF"));
    }
    public static Component Pink(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#FFc0cb"));
    }
    public static Component Bronze(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#CE8946"));
    }
    public static Component Black(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#000"));
    }
    public static Component Gray(int text){
        return Component.text(text)
                .color(TextColor.fromHexString("#808080"));
    }


}
