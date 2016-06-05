package xyz.imdafatboss.uhcgrounds;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.imdafatboss.uhcgrounds.cmd.api.CommandManager;
import xyz.imdafatboss.uhcgrounds.config.FileManager;
import xyz.imdafatboss.uhcgrounds.events.Events;
import xyz.imdafatboss.uhcgrounds.game.Lobby;
import xyz.imdafatboss.uhcgrounds.kits.KitManager;

public class Home extends JavaPlugin implements Listener{

    FileManager fm = new FileManager(this);
    KitManager km;
    Events evt;
    Lobby lobby;
    CommandManager cmd;

    @Override
    public void onEnable(){

        this.getLogger().info("Created by imdafatboss");
        km = new KitManager(this);
        evt = new Events(this);
        lobby = new Lobby(this);
        cmd = new CommandManager(this);

        fm.getConfig("config.yml").saveDefaultConfig();
        fm.getConfig("data.yml").saveDefaultConfig();
        fm.getConfig("kit.yml").saveDefaultConfig();

        km.updateKit();
        evt.registerEvents(this);
        lobby.loadData();


    }

    @Override
    public void onDisable(){

        km = new KitManager(this);
        lobby = new Lobby(this);

        km.saveKit();
        lobby.saveData();

    }

}
