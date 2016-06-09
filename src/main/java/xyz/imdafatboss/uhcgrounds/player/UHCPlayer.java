package xyz.imdafatboss.uhcgrounds.player;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.arena.Arena;
import xyz.imdafatboss.uhcgrounds.config.FileManager;
import xyz.imdafatboss.uhcgrounds.game.Game;
import xyz.imdafatboss.uhcgrounds.utils.Debugger;

import java.util.Set;
import java.util.UUID;

public class UHCPlayer {

    Home plugin;
    public UHCPlayer(Home instance){

        this.plugin = instance;

    }
    FileManager fm;

    private Player player;
    private String name;
    private UUID uuid;
    private int wins;
    private int kills;
    private int deaths;
    private boolean ingame;
    private long enderpearl;
    private Game game;

    public UHCPlayer(Player player){

        fm = new FileManager(plugin);
        if(player != null) {
            if (fm.getConfig("data.yml") != null) {
                FileManager.Config config = fm.getConfig("data.yml");

                this.player = player;
                this.name = player.getName();
                this.uuid = player.getUniqueId();
                this.ingame = false;
                this.enderpearl = System.currentTimeMillis();
                this.game = null;

                String key = player.getUniqueId().toString();

                if (config.get().contains(key)) {

                    String path = key + ".";
                    this.wins = config.get().getInt(path + "wins");
                    this.kills = config.get().getInt(path + "kills");
                    this.deaths = config.get().getInt(path + "deaths");

                } else {

                    this.wins = 0;
                    this.kills = 0;
                    this.deaths = 0;

                }

            }
            Debugger.debug("Unable to create player data.");
            return;
        }
        Debugger.debug("Unable to create player data.");
        return;
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

    public long getEnderpearl(){

        return this.enderpearl;

    }

    public void setEnderpearl(long time){

        this.enderpearl = time;

    }

    public boolean canEnderpearl(){

        return System.currentTimeMillis() > getEnderpearl();

    }

    public Game getCurrentGame(){

        return this.game;

    }

    public void setCurrentGame(Game game){

        this.game = game;

    }

}
