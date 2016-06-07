package xyz.imdafatboss.uhcgrounds.events;

import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerTeleportEvent;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.config.FileManager;
import xyz.imdafatboss.uhcgrounds.config.Messages;
import xyz.imdafatboss.uhcgrounds.player.PlayerManager;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;
import xyz.imdafatboss.uhcgrounds.utils.Msg;
import xyz.imdafatboss.uhcgrounds.utils.Utils;

public class EnderpearlEvents implements Listener{

    Home plugin;
    public EnderpearlEvents(Home instance){

        this.plugin = instance;

    }
    FileManager fm;
    Messages msg;

    @EventHandler
    public void onEnderpearl(PlayerTeleportEvent e){

        if(e.getCause() != PlayerTeleportEvent.TeleportCause.ENDER_PEARL){

            return;

        }

        fm = new FileManager(plugin);
        msg = new Messages(plugin);
        Player p = e.getPlayer();
        if(PlayerManager.isUHCPlayer(p)){

            UHCPlayer player = PlayerManager.getPlayer(p);
            if(player.canEnderpearl()){

                long now = System.currentTimeMillis();
                int sec = fm.getConfig("config.yml").get().getInt("enderpearl-cooldown");
                long t = sec * 1000L;
                long time = now + t;

                player.setEnderpearl(time);

            }

            else{

                String s = msg.getEnderpearlCooldown();
                String s1 = s.replaceAll("%time%", Utils.getTimeLeft(player.getEnderpearl()));
                String s2 = Msg.translate(s1);

                player.getPlayer().sendMessage(msg.prefix() + s2);

            }

        }

    }

}
