package xyz.imdafatboss.uhcgrounds.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.items.GoldenHead;
import xyz.imdafatboss.uhcgrounds.player.PlayerManager;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;

public class GoldenHeadEvents implements Listener{

    Home plugin;
    public GoldenHeadEvents(Home instance){

        this.plugin = instance;

    }

    @EventHandler
    public void onSpawnGoldenHead(PlayerDeathEvent e){

        Player p = e.getEntity();
        Player k = e.getEntity().getKiller();
        if(PlayerManager.isUHCPlayer(p)){

            if(PlayerManager.isUHCPlayer(k)) {

                UHCPlayer player = PlayerManager.getPlayer(p);

                ItemStack is = GoldenHead.getGoldenHead();
                World world = player.getPlayer().getWorld();
                Location loc = player.getPlayer().getLocation();

                world.dropItem(loc, is);

            }

        }

    }

}
