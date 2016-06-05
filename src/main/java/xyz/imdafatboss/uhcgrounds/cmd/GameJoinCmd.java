package xyz.imdafatboss.uhcgrounds.cmd;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.cmd.api.CommandFactory;
import xyz.imdafatboss.uhcgrounds.config.ConfigYML;
import xyz.imdafatboss.uhcgrounds.config.Messages;
import xyz.imdafatboss.uhcgrounds.game.*;
import xyz.imdafatboss.uhcgrounds.player.*;

public class GameJoinCmd extends CommandFactory{

    Home plugin;
    JavaPlugin jp;
    public GameJoinCmd(JavaPlugin instance){

        super("uhgjoin", false);
        this.jp = instance;

    }
    Messages msg;
    ConfigYML cfg;
    Lobby lobby;

    public void execute(CommandSender sender, String[] args){

        msg = new Messages(plugin);
        cfg = new ConfigYML(plugin);
        lobby = new Lobby(plugin);
        Player p = (Player) sender;
        if(PlayerManager.isUHCPlayer(p)){

            UHCPlayer player = PlayerManager.getPlayer(p);
            if(player.isInGame()){

                p.sendMessage(msg.getPrefix() + msg.getAlreadyInGame());
                return;

            }

            for(Game game : GameManager.getGames()){

                if(game.isOn()){

                    continue;

                }

                if(!game.isOn()){

                    if(game.isLobby()){

                        if(game.getSize() <= cfg.getMaxPlayers()){

                            if(game.overPlayer(player)){

                                game.addPlayer(player);
                                p.sendMessage(msg.prefix() + msg.getJoinedLobby());
                                p.teleport(lobby.getSpawn());

                            }

                            else{

                                continue;

                            }

                        }

                    }

                }

            }

        }

        else{

            UHCPlayer player = new UHCPlayer(p);
            PlayerManager.addPlayer(player);
            p.chat("uhgjoin");
            return;

        }

    }

}
