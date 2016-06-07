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
    private World world;

    public Arena(int id, List<UHCPlayer> players){

        fm = new FileManager(plugin);

        this.id = id;
        this.players = players;
        this.world = null;

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
