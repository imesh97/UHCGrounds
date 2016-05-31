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

}