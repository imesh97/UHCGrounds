package xyz.imdafatboss.uhcgrounds.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Locations {

    public static String locationToString(Location location){

        String s = location.getBlockX() + "/" + location.getBlockY() + "/" + location.getBlockZ() + "/"
                + location.getWorld().getName();

        return s;

    }

    public static Location stringToLocation(String s){

        String[] parts = s.split("/");
        Location loc = new Location(Bukkit.getServer().getWorld(parts[3]), Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));

        return loc;

    }

}