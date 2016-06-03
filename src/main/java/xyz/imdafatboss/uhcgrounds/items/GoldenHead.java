package xyz.imdafatboss.uhcgrounds.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.imdafatboss.uhcgrounds.utils.ItemBuilder;
import xyz.imdafatboss.uhcgrounds.utils.Msg;

public class GoldenHead {

    public static ItemStack getGoldenHead(){

        ItemBuilder is = new ItemBuilder(Material.GOLDEN_APPLE, 0)
                .setName(Msg.translate("&6&lGolden &e&lHead"))
                .setLore(new String[]{Msg.translate("&7Get &c4 hearts &7 back."), "Use it wisely!"});
        ItemStack itemStack = is.getStack();

        return itemStack;

    }

    public static boolean isHead(ItemStack is){

        if(is == getGoldenHead()){

            if(is.getItemMeta().getDisplayName().equals(Msg.translate("&6&lGolden &e&lHead"))){

                return true;

            }

            return false;

        }
        return false;

    }

    public static void applyHead(Player player){

        double health = player.getHealth();
        double newHealth = health + 4.0;

        player.setHealth(newHealth);

    }

}
