package xyz.imdafatboss.uhcgrounds.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import xyz.imdafatboss.uhcgrounds.arena.*;

import java.io.File;

public class StartWorld {

    public static int latestArena(){

        return ArenaManager.getArenas().size();

    }

    public static void startWorld(){

        String s = "temp" + latestArena();

        Bukkit.createWorld(new WorldCreator(s));
        Debugger.debug("Made world " + s);

        World source = Bukkit.getWorld("template");
        File sourceFolder = source.getWorldFolder();
        File targetFolder = new File(Bukkit.getWorldContainer(), s);

        WorldManager.copyWorld(sourceFolder, targetFolder);
        Debugger.debug("Copied worlds.");

    }

    public static void stopWorld(String name){

        WorldManager.unloadWorld(name);
        WorldManager.deleteWorld(name);

    }

}
