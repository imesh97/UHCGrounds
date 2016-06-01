package xyz.imdafatboss.uhcgrounds.player;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.arena.Arena;
import xyz.imdafatboss.uhcgrounds.config.FileManager;

import java.util.UUID;

public class UHCPlayer {

    Home plugin;
    FileManager fm;

    private final YamlConfiguration config;
    private final Player player;
    private final String name;
    private final UUID uuid;
    private int wins;
    private int kills;
    private int deaths;
    private boolean ingame;

    public UHCPlayer(Player player){

        fm = new FileManager(plugin);
        config = fm.getConfig("data.yml").get();

        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.ingame = false;

        if(config.isConfigurationSection(player.getUniqueId().toString())){

            String path = player.getUniqueId().toString() + ".";
            this.wins = config.getInt(path + "wins");
            this.kills = config.getInt(path + "kills");
            this.deaths = config.getInt(path + "deaths");

        }

        else{

            this.wins = 0;
            this.kills = 0;
            this.deaths = 0;

        }

    }

    public final Player getPlayer(){

        return this.player;

    }

    public final String getName(){

        return this.name;

    }

    public final UUID getUUID(){

        return this.uuid;

    }

    public int getWins(){

        return this.wins;

    }

    public void setWins(int i){

        this.wins = i;

    }

    public void addWin(){

        this.setWins(this.getWins() + 1);

    }

    public int getKills(){

        return this.kills;

    }

    public void setKills(int i){

        this.kills = i;

    }

    public void addKill(){

        this.setKills(this.getKills() + 1);

    }

    public int getDeaths(){

        return this.deaths;

    }

    public void setDeaths(int i){

        this.deaths = i;

    }

    public void addDeath(){

        this.setDeaths(this.getDeaths() + 1);

    }

    public void openInventory(Inventory inv){

        this.getPlayer().openInventory(inv);

    }

    public boolean isOnline(){

        for(Player p : Bukkit.getOnlinePlayers()){

            if(p.getName().equals(this.getName())){

                return true;

            }

        }

        return false;

    }

    public boolean isInGame(){

        return this.ingame;

    }

    public void setInGame(boolean b){

        this.ingame = b;

    }

    public boolean isInGame(Arena arena){

        for(UHCPlayer player : arena.getPlayers()){

            if(this.getPlayer().getName().equals(player.getName())){

                return true;

            }

        }

        return false;

    }

}
