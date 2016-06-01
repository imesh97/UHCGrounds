package xyz.imdafatboss.uhcgrounds.utils;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.MaterialData;

public class ItemBuilder
{
    private final ItemStack item;

    public ItemBuilder(Material paramMaterial, int paramInt)
    {
        this.item = new ItemStack(paramMaterial, 1, (short)(byte)paramInt);
    }

    public ItemBuilder(int id, int paramInt){

        this.item = new ItemStack(id, 1, (short) (byte) paramInt);

    }

    public ItemBuilder setAmount(int paramInt) {
        this.item.setAmount(paramInt);
        return this;
    }

    public ItemBuilder setName(String paramString) {
        ItemMeta localItemMeta = this.item.getItemMeta();
        localItemMeta.setDisplayName(Msg.translate(paramString));
        this.item.setItemMeta(localItemMeta);
        return this;
    }

    public ItemBuilder setLore(List<String> paramList) {
        ItemMeta localItemMeta = this.item.getItemMeta();
        ArrayList localArrayList = new ArrayList();
        for (String str : paramList) {
            localArrayList.add(Msg.translate(str));
        }
        localItemMeta.setLore(localArrayList);
        this.item.setItemMeta(localItemMeta);
        return this;
    }

    public ItemBuilder setLore(String[] paramArrayOfString) {
        ItemMeta localItemMeta = this.item.getItemMeta();
        ArrayList localArrayList = new ArrayList();
        for (String str : paramArrayOfString) {
            localArrayList.add(Msg.translate(str));
        }
        localItemMeta.setLore(localArrayList);
        this.item.setItemMeta(localItemMeta);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment paramEnchantment, int paramInt) {
        this.item.addUnsafeEnchantment(paramEnchantment, paramInt);
        return this;
    }

    public ItemBuilder setColor(Color paramColor) {
        if ((this.item.equals(Material.LEATHER_BOOTS)) || (this.item.equals(Material.LEATHER_LEGGINGS)) || (this.item.equals(Material.LEATHER_CHESTPLATE)) ||
                (this.item
                        .equals(Material.LEATHER_HELMET)))
        {
            LeatherArmorMeta localLeatherArmorMeta = (LeatherArmorMeta)this.item.getItemMeta();
            localLeatherArmorMeta.setColor(paramColor);
            this.item.setItemMeta(localLeatherArmorMeta);
        }
        else {
            throw new IllegalArgumentException("setColor can only be used on leather armour!");
        }
        return this;
    }

    public ItemBuilder setDurability(int paramInt) {
        if ((paramInt >= -32768) && (paramInt <= 32767)) {
            this.item.setDurability((short)paramInt);
        }
        else {
            throw new IllegalArgumentException("The durability must be a short!");
        }
        return this;
    }

    public ItemBuilder setData(MaterialData paramMaterialData) {
        ItemMeta localItemMeta = this.item.getItemMeta();
        this.item.setData(paramMaterialData);
        this.item.setItemMeta(localItemMeta);
        return this;
    }

    public ItemStack getStack() {
        return this.item;
    }
}
