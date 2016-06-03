package xyz.imdafatboss.uhcgrounds.events;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import xyz.imdafatboss.uhcgrounds.Home;

public class Events {

    Home plugin;
    public Events(Home instance){

        this.plugin = instance;

    }

    public void registerEvents(Home jp){

        PluginManager pm = Bukkit.getServer().getPluginManager();

        pm.registerEvents(new GoldenHeadEvents(jp), plugin);
        pm.registerEvents(new EnderpearlEvents(jp), plugin);
        pm.registerEvents(new StatsEvents(jp), plugin);

    }

}
