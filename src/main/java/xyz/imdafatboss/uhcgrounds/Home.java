package xyz.imdafatboss.uhcgrounds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.imdafatboss.uhcgrounds.arena.Arena;
import xyz.imdafatboss.uhcgrounds.arena.ArenaManager;
import xyz.imdafatboss.uhcgrounds.cmd.api.CommandFactory;
import xyz.imdafatboss.uhcgrounds.cmd.api.CommandManager;
import xyz.imdafatboss.uhcgrounds.config.FileManager;
import xyz.imdafatboss.uhcgrounds.events.Events;
import xyz.imdafatboss.uhcgrounds.game.Game;
import xyz.imdafatboss.uhcgrounds.game.GameManager;
import xyz.imdafatboss.uhcgrounds.game.Lobby;
import xyz.imdafatboss.uhcgrounds.game.Spawn;
import xyz.imdafatboss.uhcgrounds.kits.KitManager;
import xyz.imdafatboss.uhcgrounds.player.PlayerManager;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;

public class Home extends JavaPlugin implements Listener{

    FileManager fm = new FileManager(this);
    KitManager km;
    Events evt;
    Lobby lobby;
    Spawn spawn;

    @Override
    public void onEnable(){

        this.getLogger().info("Created by imdafatboss");
        km = new KitManager(this);
        evt = new Events(this);
        lobby = new Lobby(this);
        spawn = new Spawn(this);

        new CommandManager(this);

        fm.getConfig("config.yml").saveDefaultConfig();
        fm.getConfig("data.yml").saveDefaultConfig();
        fm.getConfig("messages.yml").saveDefaultConfig();
        fm.getConfig("kit.yml").saveDefaultConfig();

        km.updateKit();
        evt.registerEvents(this);
        lobby.loadData();
        spawn.loadData();
        this.registerCommands();

        Arena newArena = new Arena(1, null);
        ArenaManager.addArena(newArena)
                .makeWorld("temp1");
        ArenaManager.getArena(newArena.getID())
                .setWorld(Bukkit.getWorld("temp1"));

        Game game = new Game(ArenaManager.getArena(newArena.getID()));
        GameManager.addGame(game);

        for(Player p : Bukkit.getOnlinePlayers()){

            UHCPlayer player = new UHCPlayer(p);
            PlayerManager.addPlayer(player);

        }

    }

    @Override
    public void onDisable(){

        km = new KitManager(this);
        lobby = new Lobby(this);
        spawn = new Spawn(this);

        km.saveKit();
        lobby.saveData();
        spawn.saveData();
        this.savePlayerData();

    }

    public void savePlayerData(){

        for(UHCPlayer p : PlayerManager.getPlayers()){

            String path = p.getUUID().toString() + ".";

            fm.getConfig("data.yml").get().set(path + "wins", p.getWins());
            fm.getConfig("data.yml").get().set(path + "kills", p.getKills());
            fm.getConfig("data.yml").get().set(path + "deaths", p.getDeaths());
            fm.getConfig("data.yml").save();

        }

    }

    public void registerCommands(){

        for(CommandFactory cmd : CommandManager.getCommands()){

            this.getCommand(cmd.getName()).setExecutor(this);
            this.getCommand(cmd.getName()).setPermission(cmd.getPermission());

        }

    }

}
