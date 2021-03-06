package xyz.imdafatboss.uhcgrounds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.imdafatboss.uhcgrounds.arena.*;
import xyz.imdafatboss.uhcgrounds.cmd.api.*;
import xyz.imdafatboss.uhcgrounds.config.*;
import xyz.imdafatboss.uhcgrounds.events.*;
import xyz.imdafatboss.uhcgrounds.game.*;
import xyz.imdafatboss.uhcgrounds.kits.*;
import xyz.imdafatboss.uhcgrounds.player.*;
import xyz.imdafatboss.uhcgrounds.utils.*;

public class Home extends JavaPlugin implements Listener{

    FileManager fm;
    CommandManager cmds;
    UHCPlayer up;
    Arena ar;
    Game g;
    OfflineUHCPlayer oup;
    KitManager km;
    Events evt;
    Lobby lobby;
    Spawn spawn;

    @Override
    public void onEnable(){

        fm = new FileManager(this);
        cmds = new CommandManager(this);
        up = new UHCPlayer(this);
        ar = new Arena(this);
        g = new Game(this);
        oup = new OfflineUHCPlayer(this);
        km = new KitManager(this);
        evt = new Events(this);
        lobby = new Lobby(this);
        spawn = new Spawn(this);

        fm.getConfig("config.yml").saveDefaultConfig();
        fm.getConfig("data.yml").saveDefaultConfig();
        fm.getConfig("messages.yml").saveDefaultConfig();
        fm.getConfig("kit.yml").saveDefaultConfig();

        this.getLogger().info("Created by imdafatboss");

        km.updateKit();
        evt.registerEvents(this);
        lobby.loadData();
        spawn.loadData();
        this.registerCommands();

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {

                if(Bukkit.getWorld("template") != null) {

                    Arena newArena = new Arena(1);
                    ArenaManager.addArena(newArena)
                            .makeWorld("temp1");
                    ArenaManager.getArena(newArena.getID())
                            .setWorld(Bukkit.getWorld("temp1"));

                    Game game = new Game(ArenaManager.getArena(newArena.getID()));
                    GameManager.addGame(game);
                    Debugger.debug("A new game has been created.");

                }

                else{

                    Debugger.debug("Please create a world named template");

                }
            }
        }, 60L);

        for(Player p : Bukkit.getOnlinePlayers()){

            UHCPlayer player = new UHCPlayer(p);
            PlayerManager.addPlayer(player);

        }

    }

    @Override
    public void onDisable(){

        for(Player p : Bukkit.getOnlinePlayers()){

            if(!PlayerManager.isUHCPlayer(p)){

                UHCPlayer player = new UHCPlayer(p);
                PlayerManager.addPlayer(player);

            }

        }

        km = new KitManager(this);
        lobby = new Lobby(this);
        spawn = new Spawn(this);

        km.saveKit();
        lobby.saveData();
        spawn.saveData();
        this.savePlayerData();

    }

    public void savePlayerData(){

        fm = new FileManager(this);
        try {
            if (PlayerManager.getPlayers() != null) {
                for (UHCPlayer p : PlayerManager.getPlayers()) {

                    if (p != null) {
                        String path = p.getUUID().toString() + ".";

                        fm.getConfig("data.yml").get().set(path + "wins", p.getWins());
                        fm.getConfig("data.yml").get().set(path + "kills", p.getKills());
                        fm.getConfig("data.yml").get().set(path + "deaths", p.getDeaths());
                        fm.getConfig("data.yml").save();
                        return;
                    }

                }
            }

            Debugger.debug("No player data was save because none existed.");
        }catch (Exception e){ }

    }

    public void registerCommands(){

        for(CommandFactory cmd : CommandManager.getCommands()){

            this.getCommand(cmd.getName()).setExecutor(this);
            this.getCommand(cmd.getName()).setPermission(cmd.getPermission());

        }

    }

}
