package xyz.imdafatboss.uhcgrounds.utils;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Utils {

    // Inventory
    public static String halfInv(Inventory inv){

        return Inventories.toBase64(inv);

    }

    public static String armorInv(ItemStack[] itemStacks){

        return Inventories.itemStackArrayToBase64(itemStacks);

    }

    public static ItemStack[] getArmor(String data){

        try{
            return Inventories.itemStackArrayFromBase64(data);
        }catch (Exception e){
            return null;
        }

    }

    public static Inventory getInv(String data){

        try {

            return Inventories.fromBase64(data);

        }
        catch (Exception e){

            return null;

        }

    }

    public static String getTimeLeft(long time)
    {
        long left = time - System.currentTimeMillis();
        int seconds = (int)(left / 1000L) % 60;
        int minutes = (int)(left / 60000L % 60L);
        int hours = (int)(left / 3600000L % 24L);
        int days = (int)(left / 86400000L);
        if (days > 1)
            return days + "d " + hours + "h " + minutes + "m " + seconds + "s";
        if (hours > 1)
            return hours + "h " + minutes + "m " + seconds + "s";
        if (minutes > 1) {
            return minutes + "m " + seconds + "s";
        }
        return seconds + "s";
    }

}