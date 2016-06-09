package xyz.imdafatboss.uhcgrounds.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.config.ConfigYML;
import xyz.imdafatboss.uhcgrounds.player.PlayerManager;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;

public class RegenEvent implements Listener{

    Home plugin;
    public RegenEvent(Home instance){

        this.plugin = instance;

    }
    ConfigYML cfg;

    @EventHandler
    public void onPlayerRegen(EntityRegainHealthEvent e){

        cfg = new ConfigYML(plugin);
        if(e.getEntityType() == EntityType.PLAYER){

            Player player = (Player) e.getEntity();
            if(PlayerManager.isUHCPlayer(player)){

                UHCPlayer p = PlayerManager.getPlayer(player);
                if(p.isInGame()) {
                    if (!cfg.getRegen()) {

                        e.setCancelled(true);

                    }

                }

            }

        }

    }

}
