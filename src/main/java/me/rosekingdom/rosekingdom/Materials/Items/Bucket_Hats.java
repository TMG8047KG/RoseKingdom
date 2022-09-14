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

public class Bucket_Hats {

    public static ItemStack purple_bucket_hat;

    public static void init(){
        bucket_hats();
    }

    private static void bucket_hats(){
        ItemStack item = new ItemStack(Material.CARVED_PUMPKIN, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(5001);
        meta.setDisplayName("§dPurple Bucket Hat");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Made by TMG8047KG inspired by tofifiii");
        meta.setLore(lore);
        AttributeModifier attribute = new AttributeModifier(UUID.randomUUID(), "generic.armor", 2 , AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, attribute);
        item.setItemMeta(meta);
        purple_bucket_hat = item;

        ShapelessRecipe recipe = new ShapelessRecipe(NamespacedKey.minecraft("purple_bucket_hat_shapeless"), item);
        recipe.addIngredient(1, Material.IRON_HELMET);
        recipe.addIngredient(1, Material.PURPLE_DYE);
        Bukkit.getServer().addRecipe(recipe);
    }
}
