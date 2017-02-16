package pl.otekplay.loveotek.main;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.basic.Kit;
import pl.otekplay.loveotek.managers.KitManager;

public class Kits {

    private static KitManager manager() {
        return Core.getInstance().getInstance().getKitManager();
    }


    public static Kit icon(ItemStack item){
        return manager().getKitByItem(item);
    }
    public static void open(Player player){
        manager().getKitMenu().open(player);
    }
}
