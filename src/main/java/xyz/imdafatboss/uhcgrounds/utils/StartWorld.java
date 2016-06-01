package xyz.imdafatboss.uhcgrounds.utils;

import xyz.imdafatboss.uhcgrounds.arena.*;

public class StartWorld {

    public static int latestArena(){

        return ArenaManager.getArenas().size();

    }

    public static void startWorld(){

        int i = latestArena();
        String source = "template";
        String target = "temp" + i;

        WorldManager.copyWorlds(source, target);

    }

    public static void stopWorld(String name){

        WorldManager.unloadWorld(name);
        WorldManager.deleteWorld(name);

    }

}
