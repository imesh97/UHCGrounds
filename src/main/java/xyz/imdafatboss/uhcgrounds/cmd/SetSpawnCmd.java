package xyz.imdafatboss.uhcgrounds.cmd;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.cmd.api.CommandFactory;
import xyz.imdafatboss.uhcgrounds.config.Messages;
import xyz.imdafatboss.uhcgrounds.game.Spawn;

public class SetSpawnCmd extends CommandFactory{

    Home plugin;
    JavaPlugin jp;
    public SetSpawnCmd(JavaPlugin instance){

        super("uhgsetspawn", "eximus.uhcgrounds.setspawn", false);
        this.jp = instance;

    }
    Messages msg;
    Spawn spawn;

    @Override
    public void execute(CommandSender sender, String[] args) {

        msg = new Messages(plugin);
        spawn = new Spawn(plugin);
        Player p = (Player) sender;

        Location loc = p.getLocation();
        spawn.setSpawn(loc);
        spawn.saveData();

        p.sendMessage(msg.prefix() + msg.getSetSpawn());

    }
}
