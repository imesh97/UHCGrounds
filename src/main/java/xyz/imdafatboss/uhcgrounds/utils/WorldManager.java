package xyz.imdafatboss.uhcgrounds.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.*;
import java.util.*;

public class WorldManager {

    public static void copyWorlds(String source, String target){

        Bukkit.createWorld(new WorldCreator(target));

        World s = Bukkit.getWorld(source);
        File sourceFolder = s.getWorldFolder();
        File targetFolder = new File(Bukkit.getWorldContainer(),target);

        copyWorld(sourceFolder, targetFolder);

    }

    public static void copyWorld(File source, File target) {
        try {
            ArrayList<String> ignore = new ArrayList<String>(Arrays.asList("uid.dat", "session.dat"));
            if (!ignore.contains(source.getName())) {
                if (source.isDirectory()) {
                    if (!target.exists())
                        target.mkdirs();
                    String files[] = source.list();
                    for (String file : files) {
                        File srcFile = new File(source, file);
                        File destFile = new File(target, file);
                        copyWorld(srcFile, destFile);
                    }
                } else {
                    InputStream in = new FileInputStream(source);
                    OutputStream out = new FileOutputStream(target);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0)
                        out.write(buffer, 0, length);
                    in.close();
                    out.close();
                }
            }
        } catch (IOException e) {

        }
    }

    public static void deleteWorld(String name){

        World delete = Bukkit.getWorld(name);
        File deleteFolder = delete.getWorldFolder();

        deleteWorlds(deleteFolder);

    }

    public static boolean deleteWorlds(File path) {

        if (path.exists()) {

            File files[] = path.listFiles();
            for (int i = 0; i < files.length; i++) {

                if (files[i].isDirectory()) {

                    deleteWorlds(files[i]);
                } else {

                    files[i].delete();

                }

            }

        }

        return (path.delete());

    }

    public static void unloadWorld(String name){

        World world = Bukkit.getWorld(name);
        unloadWorld(world);

    }

    public static void unloadWorld(World world) {

        Bukkit.getServer().unloadWorld(world, true);

    }

}
