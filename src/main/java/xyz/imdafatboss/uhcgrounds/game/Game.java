package xyz.imdafatboss.uhcgrounds.game;

import xyz.imdafatboss.uhcgrounds.arena.Arena;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;

import java.util.List;

public class Game {

    private final int id;
    private final Arena arena;
    private boolean on;
    private boolean grace;
    private List<UHCPlayer> players;
    private UHCPlayer winner;

    public Game(Arena arena){

        this.id = arena.getID();
        this.arena = arena;
        this.on = false;
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

    public UHCPlayer getWinner(){

        return this.winner;

    }

    public void setWinner(UHCPlayer player){

        this.winner = player;

    }

}
