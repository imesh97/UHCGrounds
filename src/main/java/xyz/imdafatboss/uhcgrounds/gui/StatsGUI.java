package xyz.imdafatboss.uhcgrounds.gui;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.config.*;
import xyz.imdafatboss.uhcgrounds.player.*;
import xyz.imdafatboss.uhcgrounds.utils.ItemBuilder;
import xyz.imdafatboss.uhcgrounds.utils.Msg;

import java.util.*;

public class StatsGUI {

    Home plugin;
    public StatsGUI(Home instance){

        this.plugin = instance;

    }
    FileManager fm;
    Messages msg;

    String PATH = "statsgui.";

    public YamlConfiguration getConfig(){

        fm = new FileManager(plugin);
        return fm.getConfig("config.yml").get();

    }

    public String getInventoryTitle(UHCPlayer player){

        msg = new Messages(plugin);
        String name = player.getName();

        String s = msg.getStatsGUITitle();
        String s1 = s.replaceAll("%player%", name);

        return Msg.translate(s1);

    }

    public ItemStack getKillsItem(UHCPlayer player){

        String name = this.getConfig().getString(PATH + "kills.name");
        int item = this.getConfig().getInt(PATH + "kills.item");
        List<String> lore = this.getConfig().getStringList(PATH + "kills.lore");
        List<String> newLore = new ArrayList<String>();

        for(String s : lore){

            String s1 = s.replaceAll("%kills%", player.getKills() + "");
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

    public ItemStack getDeathsItem(UHCPlayer player){

        String name = this.getConfig().getString(PATH + "deaths.name");
        int item = this.getConfig().getInt(PATH + "deaths.item");
        List<String> lore = this.getConfig().getStringList(PATH + "deaths.lore");
        List<String> newLore = new ArrayList<String>();

        for(String s : lore){

            String s1 = s.replaceAll("%deaths%", player.getDeaths() + "");
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

    public ItemStack getWinsItem(UHCPlayer player){

        String name = this.getConfig().getString(PATH + "wins.name");
        int item = this.getConfig().getInt(PATH + "wins.item");
        List<String> lore = this.getConfig().getStringList(PATH + "wins.lore");
        List<String> newLore = new ArrayList<String>();

        for(String s : lore){

            String s1 = s.replaceAll("%wins%", player.getWins() + "");
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

    public Inventory getStatsGUI(UHCPlayer player){

        Inventory inv = Bukkit.createInventory(null, 27, this.getInventoryTitle(player));

        inv.setItem(this.getKillsSlot(), this.getKillsItem(player));
        inv.setItem(this.getDeathsSlot(), this.getDeathsItem(player));
        inv.setItem(this.getWinsSlot(), this.getWinsItem(player));

        return inv;

    }

}
