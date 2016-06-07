package xyz.imdafatboss.uhcgrounds.game;

import org.bukkit.Location;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.config.FileManager;
import xyz.imdafatboss.uhcgrounds.utils.Locations;

public class Spawn {

    Home plugin;
    public Spawn(Home instance){

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

        this.spawn = Locations.stringToLocation(fm.getConfig("data.yml").get().getString("spawn-loc"));

    }

    public void saveData(){

        fm = new FileManager(plugin);

        fm.getConfig("data.yml").get().set("spawn-loc", Locations.locationToString(this.spawn));

    }

}
