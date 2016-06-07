package xyz.imdafatboss.uhcgrounds.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.config.Messages;
import xyz.imdafatboss.uhcgrounds.player.PlayerManager;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;

public class StatMessageEvent implements Listener{

    Home plugin;
    public StatMessageEvent(Home instance){

        this.plugin = instance;

    }
    Messages msg;

    @EventHandler
    public void onStatMessage(PlayerDeathEvent e){

        msg = new Messages(plugin);
        Player player = e.getEntity();
        Player killer = e.getEntity().getKiller();

        if(PlayerManager.isUHCPlayer(player)){

            final UHCPlayer p = PlayerManager.getPlayer(player);
            if(PlayerManager.isUHCPlayer(killer)){

                UHCPlayer k = PlayerManager.getPlayer(killer);
                if(p.getCurrentGame() == k.getCurrentGame()){

                    for(final UHCPlayer p1 : p.getCurrentGame().getPlayers()){

                        p1.getPlayer().sendMessage(msg.prefix() + msg.getPlayerDied(k, p));
                        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                            @Override
                            public void run() {

                                int first = p1.getCurrentGame().getFirst();
                                int now = p1.getCurrentGame().getPlayers().size();
                                p1.getPlayer().sendMessage(msg.prefix() + msg.getPlayersLeft(first, now));

                            }

                        }, 10L);

                    }

                }

            }

        }

    }

}
