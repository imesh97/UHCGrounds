package xyz.imdafatboss.uhcgrounds;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.imdafatboss.uhcgrounds.kits.KitManager;

public class Home extends JavaPlugin implements Listener{

    KitManager km;

    @Override
    public void onEnable(){

        this.getLogger().info("Created by imdafatboss");
        km = new KitManager(this);

        km.updateKit();

    }

}
