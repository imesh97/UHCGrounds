package xyz.imdafatboss.uhcgrounds.kits;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Kit {

    private Inventory inv;
    private ItemStack[] armor;

    public Kit(Inventory inv, ItemStack[] armor){

        this.inv = inv;
        this.armor = armor;

    }

    public Inventory getInventory(){

        return this.inv;

    }

    public void setInventory(Inventory inventory){

        this.inv = inventory;

    }

    public ItemStack[] getArmor(){

        return this.armor;

    }

    public void setArmor(ItemStack[] newArmor){

        this.armor = newArmor;

    }

}
