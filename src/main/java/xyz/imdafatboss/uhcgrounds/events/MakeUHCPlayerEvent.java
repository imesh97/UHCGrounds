package xyz.imdafatboss.uhcgrounds.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.player.PlayerManager;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;

public class MakeUHCPlayerEvent implements Listener{

    Home plugin;
    public MakeUHCPlayerEvent(Home instance){

        this.plugin = instance;

    }

    @EventHandler
    public void onMakeUHCPlayer(PlayerJoinEvent e){

        if(!PlayerManager.isUHCPlayer(e.getPlayer())){

            UHCPlayer p = new UHCPlayer(e.getPlayer());
            PlayerManager.addPlayer(p);

        }

    }

}
