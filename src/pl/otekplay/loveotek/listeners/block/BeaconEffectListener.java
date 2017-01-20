package pl.otekplay.loveotek.listeners.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.github.paperspigot.event.block.BeaconEffectEvent;

public class BeaconEffectListener implements Listener {



    @EventHandler
    public void onBeaconEffectEvent(BeaconEffectEvent event){
        event.setCancelled(true);
    }
}
