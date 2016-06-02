package xyz.imdafatboss.uhcgrounds.utils;

import org.bukkit.entity.Player;

public class XPLevels {

    public static int getLevel(Player player){

        return player.getLevel();

    }

    public static void setLevel(Player player, int i){

        player.setLevel(i);

    }

    public static void addLevels(Player player, int i){

        setLevel(player, getLevel(player) + i);

    }

    public static void removeLevels(Player player, int i){

        setLevel(player, getLevel(player) - i);

    }

}
