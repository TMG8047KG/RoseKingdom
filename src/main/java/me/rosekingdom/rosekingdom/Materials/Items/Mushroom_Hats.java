package me.rosekingdom.rosekingdom.Materials.Items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public class Mushroom_Hats {
    public static ItemStack red_mushroom_hat;

    public static void init(){
        mushroom_hats();
    }

    private static void mushroom_hats(){
        ItemStack item = new ItemStack(Material.CARVED_PUMPKIN, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(5002);
        meta.setDisplayName("§cRed Mushroom Hat");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Made by TMG8047KG inspired by Sirince");
        meta.setLore(lore);
        AttributeModifier attribute = new AttributeModifier(UUID.randomUUID(), "generic.armor", 2 , AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, attribute);
        item.setItemMeta(meta);
        red_mushroom_hat = item;

        ShapelessRecipe recipe = new ShapelessRecipe(NamespacedKey.minecraft("red_mushroom_hat_shapeless"), item);
        recipe.addIngredient(1, Material.IRON_HELMET);
        recipe.addIngredient(1, Material.RED_MUSHROOM);
        Bukkit.getServer().addRecipe(recipe);
    }
}
