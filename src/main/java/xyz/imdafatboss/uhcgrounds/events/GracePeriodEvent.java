package xyz.imdafatboss.uhcgrounds.events;

import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.config.Messages;
import xyz.imdafatboss.uhcgrounds.player.PlayerManager;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;

public class GracePeriodEvent implements Listener{

    Home plugin;
    public GracePeriodEvent(Home instance){

        this.plugin = instance;

    }
    Messages msg;

    @EventHandler
    public void onGrace(EntityDamageByEntityEvent e){

        msg = new Messages(plugin);
        if(e.getDamager() instanceof Player){

            Player player = (Player) e.getDamager();
            if(PlayerManager.isUHCPlayer(player)){

                UHCPlayer p = PlayerManager.getPlayer(player);
                if(p.isInGame()){

                    if(p.getCurrentGame().isGrace(System.currentTimeMillis())){

                        e.setCancelled(true);
                        player.sendMessage(msg.prefix() + msg.getCurrentlyGrace());

                    }

                }

            }

        }

    }

}
