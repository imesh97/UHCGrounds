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
        pm.registerEvents(new DeathEvent(jp), plugin);
        pm.registerEvents(new MakeUHCPlayerEvent(jp), plugin);
        pm.registerEvents(new PlayerLeaveGameEvent(jp), plugin);
        pm.registerEvents(new PlayerWinEvent(jp), plugin);
        pm.registerEvents(new RegenEvent(jp), plugin);
        pm.registerEvents(new StatMessageEvent(jp), plugin);

    }

}
