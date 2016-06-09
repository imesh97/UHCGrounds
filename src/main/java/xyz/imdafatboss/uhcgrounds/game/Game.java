package xyz.imdafatboss.uhcgrounds.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.arena.Arena;
import xyz.imdafatboss.uhcgrounds.config.ConfigYML;
import xyz.imdafatboss.uhcgrounds.config.Messages;
import xyz.imdafatboss.uhcgrounds.kits.KitManager;
import xyz.imdafatboss.uhcgrounds.player.PlayerManager;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;
import xyz.imdafatboss.uhcgrounds.utils.Locations;
import xyz.imdafatboss.uhcgrounds.utils.XPLevels;

import java.util.ArrayList;
import java.util.List;

public class Game {

    Home plugin;
    public Game(Home instance){

        this.plugin = instance;

    }
    Messages msg;
    ConfigYML cfg;
    KitManager km;

    private int id;
    private Arena arena;
    private boolean on;
    private boolean lobby;
    private long grace;
    private UHCPlayer winner;
    private int first;

    public Game(Arena arena){

        msg = new Messages(plugin);
        cfg = new ConfigYML(plugin);
        km = new KitManager(plugin);

        this.id = arena.getID();
        this.arena = arena;
        this.on = false;
        this.lobby = true;
        this.grace = System.currentTimeMillis();
        this.winner = null;
        this.first = 0;

    }

    public final int getID(){

        return this.id;

    }

    public final Arena getArena(){

        return this.arena;

    }

    public boolean isOn(){

        return this.on;

    }

    public void setOn(boolean b){

        this.on = b;

    }

    public boolean isLobby(){

        return this.lobby;

    }

    public void setLobby(boolean b){

        this.lobby = b;

    }

    public List<UHCPlayer> getPlayers(){

        return this.getArena().getPlayers();

    }

    public int getSize(){

        return this.getPlayers().size();

    }

    public void setPlayers(List<UHCPlayer> newPlayers){

        this.getArena().setPlayers(newPlayers);

    }

    public boolean canStart(){

        return cfg.getMinPlayers() <= this.getPlayers().size();

    }

    public void giveKit(){

        for(UHCPlayer player : this.getPlayers()){

            km.giveKit(player);

        }

    }

    public void giveXP(){

        for(UHCPlayer p : this.getPlayers()){

            XPLevels.setLevel(p.getPlayer(), 25);

        }

    }

    public void start(){

        List<UHCPlayer> started = new ArrayList<UHCPlayer>();
        int radius = cfg.getScatterRadius();
        double distance = cfg.getScatterDistance() * 1.0;
        Location l = null;

        for(UHCPlayer player : this.getPlayers()){

            Location loc = Locations.randomLocation(this.getArena().getWorld(), radius);
            l = loc;
            if(started.size() == 0){

                player.getPlayer().teleport(l);
                started.add(player);
                continue;

            }
            else{

                for(UHCPlayer st : started){

                    if(st.getPlayer().getLocation().distance(loc) <= distance){

                        l = Locations.randomLocation(this.getArena().getWorld(), radius);
                        continue;

                    }

                }

                player.getPlayer().teleport(l);

            }

            player.getPlayer().sendMessage(msg.prefix() + msg.getStarted());

            int sec = cfg.getGrace();
            long t = sec * 1000L;
            long time = System.currentTimeMillis() + t;

            this.setGrace(time);

        }

        this.setOn(true);
        this.first = this.getPlayers().size();

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

            @Override
            public void run() {

                giveKit();
                giveXP();

            }

        }, 20L);

    }

    public int getFirst(){

        return this.first;

    }

    public void hidePlayers(UHCPlayer player){

        for(UHCPlayer p : PlayerManager.getPlayers()){

            if(!this.getPlayers().contains(p)){

                player.getPlayer().hidePlayer(p.getPlayer());

            }

        }

    }

    public void addPlayer(UHCPlayer player){

        List<UHCPlayer> players = this.getPlayers();
        List<UHCPlayer> players1 = this.getArena().getPlayers();

        players.add(player);
        players1.add(player);

        this.setPlayers(players);
        this.getArena().setPlayers(players1);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

            @Override
            public void run() {

                if(canStart()){

                    if(!isOn()) {

                        start();

                    }

                }

            }

        }, 20L * 15);


    }

    public void removePlayer(UHCPlayer player){

        List<UHCPlayer> players = this.getPlayers();
        List<UHCPlayer> players1 = this.getArena().getPlayers();

        players.remove(player);
        players1.remove(player);

        this.setPlayers(players);
        this.getArena().setPlayers(players1);

        player.getPlayer().sendMessage(msg.prefix() + msg.getKicked());

    }

    public UHCPlayer getWinner(){

        return this.winner;

    }

    public void setWinner(UHCPlayer player){

        this.winner = player;

    }

    public boolean overPlayer(UHCPlayer player){

        Player p = player.getPlayer();

        for(UHCPlayer p2 : this.getPlayers()){

            Player p1 = p2.getPlayer();
            if(p.hasPermission("eximus.uhcgrounds.5")){

                if(!p1.hasPermission("eximus.uhcgrounds.5")){

                    this.removePlayer(p2);
                    return true;

                }

                else{

                    continue;

                }

            }

            else if(p.hasPermission("eximus.uhcgrounds.4")){

                if(p1.hasPermission("eximus.uhcgrounds.5") || p1.hasPermission("eximus.uhcgrounds.4")){

                    continue;

                }

                else{

                    this.removePlayer(p2);
                    return true;

                }

            }

            else if(p.hasPermission("eximus.uhcgrounds.3")){

                if(p1.hasPermission("eximus.uhcgrounds.5") || p1.hasPermission("eximus.uhcgrounds.4")
                        || p1.hasPermission("eximus.uhcgrounds.3")){

                    continue;

                }

                else{

                    this.removePlayer(p2);
                    return true;

                }

            }

            else if(p.hasPermission("eximus.uhcgrounds.2")){

                if(p1.hasPermission("eximus.uhcgrounds.5") || p1.hasPermission("eximus.uhcgrounds.4")
                        || p1.hasPermission("eximus.uhcgrounds.3") || p1.hasPermission("eximus.uhcgrounds.2")){

                    continue;

                }

                else{

                    this.removePlayer(p2);
                    return true;

                }

            }

            else if(p.hasPermission("eximus.uhcgrounds.1")){

                return false;

            }

            else{

                return false;

            }

        }

        return false;

    }

    public boolean isGrace(long time){

        return System.currentTimeMillis() < this.grace;

    }

    public void setGrace(long time){

        this.grace = time;

    }

}
