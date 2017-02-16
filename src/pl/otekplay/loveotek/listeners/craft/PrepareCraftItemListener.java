package pl.otekplay.loveotek.listeners.craft;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import pl.otekplay.loveotek.main.CobbleX;

public class PrepareCraftItemListener implements Listener {


    @EventHandler
    public void onPrepareCraftItemEvent(PrepareItemCraftEvent e){
        if(!CobbleX.cobblex(e.getRecipe().getResult())){
            return;
        }
        if(!CobbleX.craft(e.getInventory())){
            e.getInventory().setResult(null);
        }
    }
}
