package xyz.imdafatboss.uhcgrounds.arena;

import org.bukkit.World;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.config.FileManager;
import xyz.imdafatboss.uhcgrounds.player.*;
import xyz.imdafatboss.uhcgrounds.utils.WorldManager;

import java.util.*;

public class Arena {

    Home plugin;
    FileManager fm;

    private final int id;
    private List<UHCPlayer> players;
    private UHCPlayer winner;
    private final int maxplayers;
    private final int minplayers;
    private World world;

    public Arena(int id, List<UHCPlayer> players){

        fm = new FileManager(plugin);
        FileManager.Config config = fm.getConfig("config.yml");

        this.id = id;
        this.players = players;
        this.winner = null;
        this.world = null;

        if(config.get().isConfigurationSection("max-players")  && config.get().isConfigurationSection("min-players")){

            this.maxplayers = config.get().getInt("max-players");
            this.minplayers = config.get().getInt("min-players");

        }

        else{

            this.maxplayers = 12;
            this.minplayers = 6;

        }

    }

    public final int getID(){

        return this.id;

    }

    public List<UHCPlayer> getPlayers(){

        return this.players;

    }

    public void setPlayers(List<UHCPlayer> list){

        this.players = list;

    }

    public UHCPlayer getWinner(){

        return this.winner;

    }

    public void setWinner(UHCPlayer player){

        this.winner = player;

    }

    public World getWorld(){

        return this.world;

    }

    public void setWorld(World newWorld){

        this.world = newWorld;

    }

    public void makeWorld(String s){

        WorldManager.copyWorlds("template", s);

    }

}
