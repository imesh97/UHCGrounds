package xyz.imdafatboss.uhcgrounds.arena;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.config.FileManager;
import xyz.imdafatboss.uhcgrounds.player.*;
import xyz.imdafatboss.uhcgrounds.utils.Debugger;
import xyz.imdafatboss.uhcgrounds.utils.WorldManager;

import java.io.File;
import java.util.*;

public class Arena {

    Home plugin;
    public Arena(Home instance){

        this.plugin = instance;

    }
    FileManager fm;

    private int id;
    private List<UHCPlayer> players;
    private World world;

    public Arena(int id){


        fm = new FileManager(plugin);

        this.id = id;
        this.players = null;
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

        Bukkit.createWorld(new WorldCreator(s));
        Debugger.debug("Made world " + s);

        World source = Bukkit.getWorld("template");
        File sourceFolder = source.getWorldFolder();
        File targetFolder = new File(Bukkit.getWorldContainer(), s);

        WorldManager.copyWorld(sourceFolder, targetFolder);
        Debugger.debug("Copied worlds.");

    }

}
