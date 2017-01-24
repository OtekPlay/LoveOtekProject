package pl.otekplay.loveotek.main;

import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.managers.CobbleXManager;

public class CobbleX {


    private static CobbleXManager manager() {
        return Core.getInstance().getCobbleXManager();
    }

    public static void add(Location location) {
        manager().addCobble(location);
    }

    public static void remove(Location location) {
        manager().removeCobble(location);
    }

    public static boolean is(Location location) {
        return manager().isCobble(location);
    }

    public static ItemStack item() {
        return manager().getItem();
    }

    public static boolean cobblex(ItemStack itemStack){
        return manager().isCobbleXItem(itemStack);
    }

    public static boolean craft(Inventory inventory) {
        return manager().hasMaterialsForCraft(inventory);
    }

    public static ItemStack random() {
        return manager().getRandomItem();
    }
}
