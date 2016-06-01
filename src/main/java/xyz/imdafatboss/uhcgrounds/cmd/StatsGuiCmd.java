package xyz.imdafatboss.uhcgrounds.cmd;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.cmd.api.CommandFactory;
import xyz.imdafatboss.uhcgrounds.config.FileManager;
import xyz.imdafatboss.uhcgrounds.config.Messages;
import xyz.imdafatboss.uhcgrounds.gui.OfflineStatsGUI;
import xyz.imdafatboss.uhcgrounds.gui.StatsGUI;
import xyz.imdafatboss.uhcgrounds.player.OfflineUHCPlayer;
import xyz.imdafatboss.uhcgrounds.player.PlayerManager;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;

public class StatsGuiCmd extends CommandFactory{

    Home plugin;
    JavaPlugin jp;
    public StatsGuiCmd(JavaPlugin instance){

        super("stats", false);
        this.jp = instance;

    }
    FileManager fm;
    StatsGUI gui;
    OfflineStatsGUI ogui;
    Messages msg;
    OfflineUHCPlayer ohc;

    public void execute(CommandSender sender, String[] args){

        fm = new FileManager(plugin);
        gui = new StatsGUI(plugin);
        ogui = new OfflineStatsGUI(plugin);
        msg = new Messages(plugin);
        ohc = new OfflineUHCPlayer(plugin);
        Player player = (Player) sender;

        if(PlayerManager.isUHCPlayer(player)){

            UHCPlayer p = PlayerManager.getPlayer(player);

            if(args.length == 0){

                p.openInventory(gui.getStatsGUI(p));

            }

            else if(args.length >= 1){

                String a1 = args[0];

                Player targetPlayer = Bukkit.getPlayer(a1);

                if(targetPlayer != null){

                    if(PlayerManager.isUHCPlayer(targetPlayer)){

                        UHCPlayer tar = PlayerManager.getPlayer(targetPlayer);
                        p.openInventory(gui.getStatsGUI(tar));

                    }

                    else {

                        player.sendMessage(msg.prefix() + msg.getHasntPlayed());

                    }

                }

                else if(targetPlayer == null){

                    OfflinePlayer tar1 = Bukkit.getOfflinePlayer(a1);

                    if(tar1 != null){

                        p.openInventory(ogui.getStatsGUI(tar1));

                    }

                    else{

                        sender.sendMessage(msg.prefix() + msg.getPlayerNotExist());

                    }

                }

            }

        }

    }

}
