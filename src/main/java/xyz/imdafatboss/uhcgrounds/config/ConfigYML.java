package xyz.imdafatboss.uhcgrounds.config;

import org.bukkit.configuration.file.YamlConfiguration;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getWinCommands(){

        return this.getConfig().getStringList("win-commands");

    }

    public List<String> winCommands(UHCPlayer player){

        List<String> cmds = new ArrayList<String>();
        for(String s : this.getWinCommands()){

            String s1 = s.replaceAll("%player%", player.getName());
            cmds.add(s1);

        }

        return cmds;

    }

    public boolean getRegen(){

        return this.getConfig().getBoolean("regen");

    }

}
