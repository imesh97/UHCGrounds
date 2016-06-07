package xyz.imdafatboss.uhcgrounds;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.imdafatboss.uhcgrounds.cmd.api.CommandManager;
import xyz.imdafatboss.uhcgrounds.config.FileManager;
import xyz.imdafatboss.uhcgrounds.events.Events;
import xyz.imdafatboss.uhcgrounds.game.Lobby;
import xyz.imdafatboss.uhcgrounds.kits.KitManager;
import xyz.imdafatboss.uhcgrounds.player.PlayerManager;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;

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
        this.savePlayerData();

    }

    public void savePlayerData(){

        for(UHCPlayer p : PlayerManager.getPlayers()){

            String path = p.getUUID().toString() + ".";

            fm.getConfig("data.yml").get().set(path + "wins", p.getWins());
            fm.getConfig("data.yml").get().set(path + "kills", p.getKills());
            fm.getConfig("data.yml").get().set(path + "deaths", p.getDeaths());
            fm.getConfig("data.yml").save();

        }

    }

}
