package xyz.imdafatboss.uhcgrounds.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.entity.PlayerDeathEvent;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.player.PlayerManager;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;

public class StatsEvents implements Listener{

    Home plugin;
    public StatsEvents(Home instance){

        this.plugin = instance;

    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent e){

        Player p = e.getEntity().getKiller();
        if(PlayerManager.isUHCPlayer(p)){

            UHCPlayer player = PlayerManager.getPlayer(p);
            if(player.isInGame()){

                player.addKill();

            }

        }

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){

        Player p = e.getEntity();
        if(PlayerManager.isUHCPlayer(p)){

            UHCPlayer player = PlayerManager.getPlayer(p);
            if(player.isInGame()){

                player.addDeath();

            }

        }

    }

    @EventHandler
    public void onPlayerWin(PlayerDeathEvent e){

        Player p = e.getEntity().getKiller();
        if(PlayerManager.isUHCPlayer(p)){

            final UHCPlayer player = PlayerManager.getPlayer(p);
            if(player.isInGame()){

                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                    @Override
                    public void run() {

                        if(player.getCurrentGame().getSize() == 1){

                            player.addWin();

                        }

                    }

                }, 40L);

            }

        }

    }

}
