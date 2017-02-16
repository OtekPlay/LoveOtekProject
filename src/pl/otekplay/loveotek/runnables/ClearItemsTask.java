package pl.otekplay.loveotek.runnables;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import pl.otekplay.loveotek.storage.GlobalSettings;

public class ClearItemsTask implements Runnable {
    private final static Inventory inv = Bukkit.createInventory(null, 9*6);
    @Override
    public void run() {
        inv.clear();
        World world = Bukkit.getWorlds().get(0);
        for(Entity entity:world.getEntities()){
            if(entity instanceof Item == false){
                continue;
            }
            Item item = (Item) entity;
            if(item.getTicksLived() > GlobalSettings.CLEAR_ITEM_LIVED_TICKS){

            }
        }
    }
}
