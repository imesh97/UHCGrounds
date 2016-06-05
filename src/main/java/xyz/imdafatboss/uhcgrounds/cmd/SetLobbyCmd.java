package xyz.imdafatboss.uhcgrounds.cmd;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.cmd.api.CommandFactory;
import xyz.imdafatboss.uhcgrounds.config.Messages;
import xyz.imdafatboss.uhcgrounds.game.Lobby;

public class SetLobbyCmd extends CommandFactory {

    Home plugin;
    JavaPlugin jp;
    public SetLobbyCmd(JavaPlugin instance){

        super("setlobby", "eximus.uhcgrounds.setlobby", false);
        this.jp = instance;

    }
    Lobby lobby;
    Messages msg;

    @Override
    public void execute(CommandSender sender, String[] args) {

        lobby = new Lobby(plugin);
        msg = new Messages(plugin);
        Player p = (Player) sender;

        Location loc = p.getLocation();
        lobby.setSpawn(loc);

        p.sendMessage(msg.prefix() + msg.getSetLobby());

    }
}

