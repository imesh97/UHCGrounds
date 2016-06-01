package xyz.imdafatboss.uhcgrounds.config;

import org.bukkit.configuration.file.YamlConfiguration;
import xyz.imdafatboss.uhcgrounds.Home;
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

}
