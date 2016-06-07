package xyz.imdafatboss.uhcgrounds.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.entity.PlayerDeathEvent;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.config.ConfigYML;
import xyz.imdafatboss.uhcgrounds.config.Messages;
import xyz.imdafatboss.uhcgrounds.game.Spawn;
import xyz.imdafatboss.uhcgrounds.player.PlayerManager;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;

import java.util.List;

public class PlayerWinEvent implements Listener{

    Home plugin;
    public PlayerWinEvent(Home instance){

        this.plugin = instance;

    }
    ConfigYML cfg;
    Spawn spawn;
    Messages msg;

    @EventHandler
    public void onPlayerWin(PlayerDeathEvent e){

        spawn = new Spawn(plugin);
        cfg = new ConfigYML(plugin);
        msg = new Messages(plugin);
        final Player p = e.getEntity().getKiller();
        if(PlayerManager.isUHCPlayer(p)){

            final UHCPlayer player = PlayerManager.getPlayer(p);
            if(player.isInGame()){

                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                    @Override
                    public void run() {

                        if(player.getCurrentGame().getSize() == 1){

                            for(String s : cfg.winCommands(player)){

                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s);
                                Location loc = spawn.getSpawn();
                                p.teleport(loc);

                                List<UHCPlayer> l = player.getCurrentGame().getPlayers();
                                l.remove(player);
                                player.getCurrentGame().setPlayers(l);

                                player.getCurrentGame().setWinner(player);
                                p.sendMessage(msg.prefix() + msg.getYouWon());
                                player.setInGame(false);

                            }

                        }

                    }

                }, 40L);

            }

        }

    }

}
