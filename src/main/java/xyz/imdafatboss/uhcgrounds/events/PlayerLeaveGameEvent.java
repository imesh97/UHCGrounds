package xyz.imdafatboss.uhcgrounds.events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.config.Messages;
import xyz.imdafatboss.uhcgrounds.game.Spawn;
import xyz.imdafatboss.uhcgrounds.player.PlayerManager;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;

import java.util.*;

public class PlayerLeaveGameEvent implements Listener{

    Home plugin;
    public PlayerLeaveGameEvent(Home instance){

        this.plugin = instance;

    }
    Spawn spawn;
    Messages msg;

    @EventHandler
    public void onPlayerLeaveGame(PlayerMoveEvent e){

        spawn = new Spawn(plugin);
        msg = new Messages(plugin);

        Player player = e.getPlayer();
        if(PlayerManager.isUHCPlayer(player)){

            UHCPlayer p = PlayerManager.getPlayer(player);
            if(p.isInGame()){

                World world = p.getCurrentGame().getArena().getWorld();
                if(e.getTo().getWorld() == world){

                    player.getInventory().clear();
                    player.teleport(spawn.getSpawn());

                    for(final UHCPlayer p1 : p.getCurrentGame().getPlayers()){

                        p1.getPlayer().sendMessage(msg.prefix() + msg.getPlayerLeft(p));
                        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                            @Override
                            public void run() {

                                int first = p1.getCurrentGame().getFirst();
                                int now = p1.getCurrentGame().getPlayers().size();
                                p1.getPlayer().sendMessage(msg.prefix() + msg.getPlayersLeft(first - 1, now));

                            }

                        }, 10L);

                    }

                    List<UHCPlayer> l = p.getCurrentGame().getPlayers();
                    l.remove(p);
                    p.getCurrentGame().setPlayers(l);

                    p.setInGame(false);
                    p.setCurrentGame(null);
                    p.setDeaths(p.getDeaths() + 1);

                }

            }

        }

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){

        spawn = new Spawn(plugin);
        msg = new Messages(plugin);

        Player player = e.getPlayer();
        if(PlayerManager.isUHCPlayer(player)){

            UHCPlayer p = PlayerManager.getPlayer(player);
            if(p.isInGame()){

                player.getInventory().clear();

                for(final UHCPlayer p1 : p.getCurrentGame().getPlayers()){

                    p1.getPlayer().sendMessage(msg.prefix() + msg.getPlayerLeft(p));
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {

                            int first = p1.getCurrentGame().getFirst();
                            int now = p1.getCurrentGame().getPlayers().size();
                            p1.getPlayer().sendMessage(msg.prefix() + msg.getPlayersLeft(first - 1, now));

                        }

                    }, 10L);

                }

                List<UHCPlayer> l = p.getCurrentGame().getPlayers();
                l.remove(p);
                p.getCurrentGame().setPlayers(l);

                p.setInGame(false);
                p.setCurrentGame(null);
                p.setDeaths(p.getDeaths() + 1);

            }

        }

    }

}
