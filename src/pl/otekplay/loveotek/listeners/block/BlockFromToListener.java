package pl.otekplay.loveotek.listeners.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class BlockFromToListener implements Listener {


    @EventHandler
    public void onBlockFromToEvent(BlockFromToEvent event){
        Block block = event.getBlock();
        if(block.getType() == Material.WATER || block.getType() == Material.LAVA || block.getType() == Material.STATIONARY_WATER || block.getType() == Material.STATIONARY_LAVA){
            event.setCancelled(true);
        }
    }
}
