package xyz.imdafatboss.uhcgrounds.events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.config.Messages;
import xyz.imdafatboss.uhcgrounds.game.Spawn;
import xyz.imdafatboss.uhcgrounds.player.PlayerManager;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;

import java.util.List;

public class DeathEvent implements Listener{

    Home plugin;
    public DeathEvent(Home instance){

        this.plugin = instance;

    }
    Spawn spawn;
    Messages msg;

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){

        spawn = new Spawn(plugin);
        msg = new Messages(plugin);

        Player player = e.getEntity();
        Player killer = e.getEntity().getKiller();
        if(PlayerManager.isUHCPlayer(player)){

            UHCPlayer p = PlayerManager.getPlayer(player);
            if(PlayerManager.isUHCPlayer(killer)) {

                UHCPlayer k = PlayerManager.getPlayer(killer);

                if (p.isInGame()) {

                    player.getInventory().clear();
                    player.teleport(spawn.getSpawn());

                    for (final UHCPlayer p1 : p.getCurrentGame().getPlayers()) {

                        p1.getPlayer().sendMessage(msg.prefix() + msg.getPlayerDied(k, p));
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

}
