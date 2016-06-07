package xyz.imdafatboss.uhcgrounds.config;

import org.bukkit.configuration.file.YamlConfiguration;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;
import xyz.imdafatboss.uhcgrounds.utils.Msg;

public class Messages {

    Home plugin;
    public Messages(Home instance){

        this.plugin = instance;

    }
    FileManager fm;

    public YamlConfiguration getConfig(){

        fm = new FileManager(plugin);
        return fm.getConfig("messages.yml").get();

    }

    public String prefix(){

        return this.getPrefix() + " ";

    }

    public String getPrefix(){

        fm = new FileManager(plugin);
        return Msg.translate(fm.getConfig("config.yml").get().getString("prefix"));

    }

    public String getSavedKit(){

        return Msg.translate(this.getConfig().getString("saved-kit"));

    }

    public String getStatsGUITitle(){

        return Msg.translate(this.getConfig().getString("statsgui-title"));

    }

    public String getHasntPlayed(){

        return Msg.translate(this.getConfig().getString("player-hasnt-played"));

    }

    public String getPlayerNotExist(){

        return Msg.translate(this.getConfig().getString("player-not-exist"));

    }

    public String getEnderpearlCooldown(){

        return Msg.translate(this.getConfig().getString("enderpearl-cooldown-msg"));

    }

    public String getAlreadyInGame(){

        return Msg.translate(this.getConfig().getString("already-in-game"));

    }

    public String getKicked(){

        return Msg.translate(this.getConfig().getString("kicked-higher-rank"));

    }

    public String getJoinedLobby(){

        return Msg.translate(this.getConfig().getString("joined-game-lobby"));

    }

    public String getSetLobby(){

        return Msg.translate(this.getConfig().getString("you-set-lobby"));

    }

    public String getStarted(){

        return Msg.translate(this.getConfig().getString("game-started"));

    }

    public String getSetSpawn(){

        return Msg.translate(this.getConfig().getString("you-set-spawn"));

    }

    public String getYouWon(){

        return Msg.translate(this.getConfig().getString("you-won"));

    }

    public String getPlayerLeft(UHCPlayer player){

        String s =  Msg.translate(this.getConfig().getString("player-left"));
        String s1 = s.replaceAll("%player%", player.getName()).toString();

        return s1;

    }

    public String getPlayerDied(UHCPlayer killer, UHCPlayer victim){

        String s = Msg.translate(this.getConfig().getString("player-death"));
        String s1 = s.replaceAll("%killer%", killer.getName()).toString();
        String s2 = s1.replaceAll("%victim%", victim.getName()).toString();

        return s2;

    }

    public String getPlayersLeft(int first, int now){

        String s = Msg.translate(this.getConfig().getString("players-left"));
        String s1 = s.replaceAll("%total%", first + "").toString();
        String s2 = s1.replaceAll("%now%", now + "").toString();

        return s2;

    }

}
