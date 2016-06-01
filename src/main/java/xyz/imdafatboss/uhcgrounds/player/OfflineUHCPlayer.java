package xyz.imdafatboss.uhcgrounds.player;

import org.bukkit.configuration.file.YamlConfiguration;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.config.FileManager;

public class OfflineUHCPlayer {

    Home plugin;
    public OfflineUHCPlayer(Home instance){

        this.plugin = instance;

    }
    FileManager fm;

    public YamlConfiguration getData(){

        fm = new FileManager(plugin);
        return fm.getConfig("data.yml").get();

    }

    public int getKills(String uuid){

        return this.getData().getInt(uuid + ".kills");

    }

    public int getDeaths(String uuid){

        return this.getData().getInt(uuid + ".deaths");

    }

    public int getWins(String uuid){

        return this.getData().getInt(uuid + ".wins");

    }

}
