package me.rosekingdom.rosekingdom.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;

public class InfoBook {
    public static void getBook(Player player){
        String nl = "\n";
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);

        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        bookMeta.setAuthor("The RoseKingdom Lord");
        bookMeta.setTitle(ChatColor.DARK_RED + "Rose" + ChatColor.GOLD + "Kingdom" + ChatColor.GRAY + " " + ChatColor.UNDERLINE + "Manual");

        ArrayList<String> pages = new ArrayList<>();

        pages.add(0, ChatColor.DARK_RED + "       Rose" + ChatColor.GOLD + "Kingdom" + nl + nl + "            Info");
        bookMeta.setPages(pages);

        book.setItemMeta(bookMeta);
        player.getInventory().addItem(book);
    }
}
