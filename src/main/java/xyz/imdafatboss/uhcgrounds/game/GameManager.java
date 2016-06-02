package xyz.imdafatboss.uhcgrounds.game;

import com.google.common.collect.ImmutableList;

import java.util.*;

public class GameManager {

    private static Map<Integer, Game> games = new HashMap<Integer, Game>();

    public static Game addGame(Game game) {

        return (Game) games.put(game.getID(), game);

    }

    public static Game removeGame(Game game) {

        return (Game) games.remove(game.getID());

    }

    public static boolean isGame(Game game) {

        return games.containsKey(game.getID());

    }

    public static Game getGame(int id) {

        return (Game) games.get(id);

    }

    public static List<Game> getGames(){

        return ImmutableList.copyOf(games.values());

    }

}