package pl.otekplay.loveotek.listeners.craft;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import pl.otekplay.loveotek.main.CobbleX;

public class CraftItemListener implements Listener {


    @EventHandler
    public void onCraftItemEvent(CraftItemEvent e) {
        if (!CobbleX.cobblex(e.getRecipe().getResult())) {
            return;
        }
        for(int i=0;i<9;i++){
            e.getClickedInventory().clear();
            e.getInventory().clear();
        }
        e.setCurrentItem(CobbleX.item());
    }
}
