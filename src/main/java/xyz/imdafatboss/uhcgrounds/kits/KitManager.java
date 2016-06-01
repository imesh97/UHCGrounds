package xyz.imdafatboss.uhcgrounds.kits;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.imdafatboss.uhcgrounds.Home;
import xyz.imdafatboss.uhcgrounds.config.FileManager;
import xyz.imdafatboss.uhcgrounds.utils.Utils;

public class KitManager {

    Home plugin;
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

    }

}
