package xyz.imdafatboss.uhcgrounds.game;

import org.bukkit.Location;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.config.FileManager;
import xyz.imdafatboss.uhcgrounds.utils.Locations;

public class Lobby {

    Home plugin;
    public Lobby(Home instance){

        this.plugin = instance;

    }
    FileManager fm;

    private Location spawn;

    public Location getSpawn(){

        return this.spawn;

    }

    public void setSpawn(Location loc){

        this.spawn = loc;

    }

    public void loadData(){

        fm = new FileManager(plugin);

        this.spawn = Locations.stringToLocation(fm.getConfig("data.yml").get().getString("lobby-spawn"));

    }

    public void saveData(){

        fm = new FileManager(plugin);

        fm.getConfig("data.yml").get().set("lobby-spawn", Locations.locationToString(this.spawn));

    }

}
