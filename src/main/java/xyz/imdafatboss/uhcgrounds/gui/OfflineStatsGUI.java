package xyz.imdafatboss.uhcgrounds.gui;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.config.FileManager;
import xyz.imdafatboss.uhcgrounds.config.Messages;
import xyz.imdafatboss.uhcgrounds.utils.ItemBuilder;
import xyz.imdafatboss.uhcgrounds.utils.Msg;

import java.util.*;

public class OfflineStatsGUI {

    Home plugin;
    public OfflineStatsGUI(Home instance){

        this.plugin = instance;

    }
    FileManager fm;
    Messages msg;

    String PATH = "statsgui.";

    public YamlConfiguration getConfig(){

        fm = new FileManager(plugin);
        return fm.getConfig("config.yml").get();

    }

    public String getInventoryTitle(String name){

        msg = new Messages(plugin);

        String s = msg.getStatsGUITitle();
        String s1 = s.replaceAll("%player%", name);

        return Msg.translate(s1);

    }

    public ItemStack getKillsItem(OfflinePlayer player){

        fm = new FileManager(plugin);
        String name = this.getConfig().getString(PATH + "kills.name");
        int item = this.getConfig().getInt(PATH + "kills.item");
        List<String> lore = this.getConfig().getStringList(PATH + "kills.lore");
        List<String> newLore = new ArrayList<String>();

        for(String s : lore){

            String uuid = player.getUniqueId().toString();
            String s1 = s.replaceAll("%kills%", fm.getConfig("data.yml").get().getInt(uuid + ".kills") + "");
            String s2 = Msg.translate(s1);

            newLore.add(s2);

        }

        ItemBuilder is = new ItemBuilder(item, 0)
                .setName(name)
                .setLore(newLore);
        ItemStack itemStack = is.getStack();

        return itemStack;

    }

    public int getKillsSlot(){

        return this.getConfig().getInt(PATH + "kills.slot");

    }

    public ItemStack getDeathsItem(OfflinePlayer player){

        fm = new FileManager(plugin);
        String name = this.getConfig().getString(PATH + "deaths.name");
        int item = this.getConfig().getInt(PATH + "deaths.item");
        List<String> lore = this.getConfig().getStringList(PATH + "deaths.lore");
        List<String> newLore = new ArrayList<String>();

        for(String s : lore){

            String uuid = player.getUniqueId().toString();
            String s1 = s.replaceAll("%deaths%", fm.getConfig("data.yml").get().getInt(uuid + ".deaths") + "");
            String s2 = Msg.translate(s1);

            newLore.add(s2);

        }

        ItemBuilder is = new ItemBuilder(item, 0)
                .setName(name)
                .setLore(newLore);
        ItemStack itemStack = is.getStack();

        return itemStack;

    }

    public int getDeathsSlot(){

        return this.getConfig().getInt(PATH + "deaths.slot");

    }

    public ItemStack getWinsItem(OfflinePlayer player){

        fm = new FileManager(plugin);
        String name = this.getConfig().getString(PATH + "wins.name");
        int item = this.getConfig().getInt(PATH + "wins.item");
        List<String> lore = this.getConfig().getStringList(PATH + "wins.lore");
        List<String> newLore = new ArrayList<String>();

        for(String s : lore){

            String uuid = player.getUniqueId().toString();
            String s1 = s.replaceAll("%wins%", fm.getConfig("data.yml").get().getInt(uuid + ".wins") + "");
            String s2 = Msg.translate(s1);

            newLore.add(s2);

        }

        ItemBuilder is = new ItemBuilder(item, 0)
                .setName(name)
                .setLore(newLore);
        ItemStack itemStack = is.getStack();

        return itemStack;

    }

    public int getWinsSlot(){

        return this.getConfig().getInt(PATH + "wins.slot");

    }

    public Inventory getStatsGUI(OfflinePlayer player){

        Inventory inv = Bukkit.createInventory(null, 27, this.getInventoryTitle(player.getName()));

        inv.setItem(this.getKillsSlot(), this.getKillsItem(player));
        inv.setItem(this.getDeathsSlot(), this.getDeathsItem(player));
        inv.setItem(this.getWinsSlot(), this.getWinsItem(player));

        return inv;

    }

}
