package xyz.imdafatboss.uhcgrounds.player;

import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerManager {

    private static Map<String, UHCPlayer> players = new HashMap<String, UHCPlayer>();

    public static UHCPlayer addPlayer(UHCPlayer player) {

        return (UHCPlayer) players.put(player.getName(), player);

    }

    public static UHCPlayer removePlayer(UHCPlayer player) {

        return (UHCPlayer) players.remove(player.getName());

    }

    public static boolean isUHCPlayer(Player player) {

        return players.containsKey(player.getName());

    }

    public static UHCPlayer getPlayer(Player player) {

        return (UHCPlayer) players.get(player.getName());

    }

    public static UHCPlayer getPlayer(String input) {

        return (UHCPlayer) players.get(input);

    }

    public static UHCPlayer getPlayer(UUID uuid) {

        return (UHCPlayer) players.get(Bukkit.getPlayer(uuid));

    }

    public static UHCPlayer getPlayer(CommandSender sender) {

        return (sender instanceof Player) ? getPlayer((Player) sender) : null;

    }

    public static List<UHCPlayer> getPlayers(){

        return ImmutableList.copyOf(players.values());

    }

}
