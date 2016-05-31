package xyz.imdafatboss.uhcgrounds.arena;

import com.google.common.collect.ImmutableList;

import java.util.*;

public class ArenaManager {

    private static Map<Integer, Arena> arenas = new HashMap<Integer, Arena>();

    public static Arena addArena(Arena arena) {

        return (Arena) arenas.put(arena.getID(), arena);

    }

    public static Arena removeArena(Arena arena) {

        return (Arena) arenas.remove(arena.getID());

    }

    public static boolean isArena(Arena arena) {

        return arenas.containsKey(arena.getID());

    }

    public static Arena getArena(int id) {

        return (Arena) arenas.get(id);

    }

    public static List<Arena> getArenas(){

        return ImmutableList.copyOf(arenas.values());

    }

}
