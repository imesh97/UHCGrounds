package xyz.imdafatboss.uhcgrounds.kits;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.config.FileManager;
import xyz.imdafatboss.uhcgrounds.player.UHCPlayer;
import xyz.imdafatboss.uhcgrounds.utils.Utils;

public class KitManager {

    Home plugin;
    public KitManager(Home instance){

        this.plugin = instance;

    }
    FileManager fm;

    private Kit kit;

    public Kit getKit(){

        return this.kit;

    }

    public void setKit(Kit kit){

        this.kit = kit;

    }

    public void updateKit(){

        fm = new FileManager(plugin);
        YamlConfiguration config = fm.getConfig("kit.yml").get();

        String invPath = config.getString("inventory");
        String armPath = config.getString("armor");

        Inventory inv = Utils.getInv(invPath);
        ItemStack[] armor = Utils.getArmor(armPath);

        Kit kit = new Kit(inv, armor);
        this.setKit(kit);
        kit.setInventory(inv);
        kit.setArmor(armor);

    }

    public void saveKit(){

        fm = new FileManager(plugin);
        YamlConfiguration config = fm.getConfig("kit.yml").get();
        FileManager.Config cfg = fm.getConfig("kit.yml");


        if(this.getKit().getInventory() != null) {
            Inventory inv = this.getKit().getInventory();
            if(this.getKit().getArmor() != null) {
                ItemStack[] armor = this.getKit().getArmor();

                String invString = Utils.halfInv(inv);
                String armString = Utils.armorInv(armor);

                config.set("inventory", invString);
                config.set("armor", armString);
                cfg.save();
            }
        }

    }

    public void giveKit(UHCPlayer player){

        player.getPlayer().getInventory().clear();
        for(ItemStack is : this.getKit().getInventory()){

            player.getPlayer().getInventory().addItem(is);

        }
        player.getPlayer().getInventory().setArmorContents(this.getKit().getArmor());

    }

}
