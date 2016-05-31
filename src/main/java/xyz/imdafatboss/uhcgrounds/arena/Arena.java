package xyz.imdafatboss.uhcgrounds.arena;

import org.bukkit.configuration.file.YamlConfiguration;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.config.FileManager;
import xyz.imdafatboss.uhcgrounds.player.*;

import java.util.*;

public class Arena {

    Home plugin;
    FileManager fm;

    private final YamlConfiguration config;
    private final int id;
    private List<UHCPlayer> players;
    private UHCPlayer winner;
    private final int maxplayers;
    private final int minplayers;

    public Arena(int id, List<UHCPlayer> players){

        fm = new FileManager(plugin);
        this.config = fm.getConfig("config.yml").get();

        this.id = id;
        this.players = players;
        this.winner = null;

        if(this.config.isConfigurationSection("max-players")){

            this.maxplayers = this.config.getInt("max-players");
            this.minplayers = this.config.getInt("min-players");

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

}
