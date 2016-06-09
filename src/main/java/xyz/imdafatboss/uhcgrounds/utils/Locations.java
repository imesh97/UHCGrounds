package xyz.imdafatboss.uhcgrounds.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Locations {

    public static String locationToString(Location location){

        if(location != null) {
            String s = location.getBlockX() + "/" + location.getBlockY() + "/" + location.getBlockZ() + "/"
                    + location.getWorld().getName();
            return s;
        }
        return "9/66/126/world";

    }

    public static Location stringToLocation(String s){

        String[] parts = s.split("/");
        Location loc = new Location(Bukkit.getServer().getWorld(parts[3]), Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));

        return loc;

    }

    public static Location randomLocation(World world, int radius){

        double xRandom = ((Math.random() * 3000)) - radius;
        double zRandom = ((Math.random() * 3000)) - radius;
        double yRandom = world.getHighestBlockYAt( (int) xRandom, (int) zRandom);
        Location randomLocation = new Location(world, xRandom, yRandom, zRandom);

        return randomLocation;

    }

}