package xyz.imdafatboss.uhcgrounds.game;

import org.bukkit.entity.Player;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.arena.Arena;
import xyz.imdafatboss.uhcgrounds.config.Messages;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;

import java.util.List;

public class Game {

    Home plugin;
    Messages msg;

    private final int id;
    private final Arena arena;
    private boolean on;
    private boolean lobby;
    private boolean grace;
    private List<UHCPlayer> players;
    private UHCPlayer winner;

    public Game(Arena arena){

        msg = new Messages(plugin);

        this.id = arena.getID();
        this.arena = arena;
        this.on = false;
        this.lobby = true;
        this.grace = false;
        this.players = null;
        this.winner = null;

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

    public void addPlayer(UHCPlayer player){

        List<UHCPlayer> players = this.getPlayers();
        List<UHCPlayer> players1 = this.getArena().getPlayers();

        players.add(player);
        players1.add(player);

        this.setPlayers(players);
        this.getArena().setPlayers(players1);

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

}
