package xyz.imdafatboss.uhcgrounds.config;

import org.bukkit.configuration.file.YamlConfiguration;
import xyz.imdafatboss.uhcgrounds.Home;

public class ConfigYML {

    Home plugin;
    public ConfigYML(Home instance){

        this.plugin = instance;

    }
    FileManager fm;

    public YamlConfiguration getConfig(){

        fm = new FileManager(plugin);
        return fm.getConfig("config.yml").get();

    }

    public int getMaxPlayers(){

        return this.getConfig().getInt("max-players");

    }

    public int getMinPlayers(){

        return this.getConfig().getInt("min-players");

    }

    public int getScatterRadius(){

        return this.getConfig().getInt("scatter.radius");

    }

    public int getScatterDistance(){

        return this.getConfig().getInt("scatter.distance");

    }

}
