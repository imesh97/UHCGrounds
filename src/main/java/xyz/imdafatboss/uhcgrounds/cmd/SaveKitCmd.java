package xyz.imdafatboss.uhcgrounds.cmd;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.cmd.api.CommandFactory;
import xyz.imdafatboss.uhcgrounds.config.FileManager;
import xyz.imdafatboss.uhcgrounds.config.Messages;
import xyz.imdafatboss.uhcgrounds.kits.Kit;
import xyz.imdafatboss.uhcgrounds.kits.KitManager;

public class SaveKitCmd extends CommandFactory{

    Home plugin;
    JavaPlugin jp;
    public SaveKitCmd(JavaPlugin instance){

        super("savekit", "eximus.uhcgrounds.savekit", false);
        this.jp = instance;

    }
    FileManager fm;
    KitManager km;
    Messages msg;

    public void execute(CommandSender sender, String[] args){

        fm = new FileManager(plugin);
        km = new KitManager(plugin);
        msg = new Messages(plugin);
        FileManager.Config mcfg = fm.getConfig("messages.yml");

        Player player = (Player) sender;
        Kit kit = km.getKit();

        Inventory inv = player.getInventory();
        ItemStack[] armor = player.getInventory().getArmorContents();

        kit.setInventory(inv);
        kit.setArmor(armor);
        km.saveKit();

        player.sendMessage(msg.prefix() + msg.getSavedKit());

    }

}
