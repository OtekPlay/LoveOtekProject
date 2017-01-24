package pl.otekplay.loveotek.listeners.block;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;

public class BlockPistonExtendListener implements Listener {

    @EventHandler
    public void onBlockPistonExtendEvent(BlockPistonExtendEvent e) {
        BlockFace bf = e.getDirection();
        for (int i = 1; i <= 15; i++) {
            if (e.getBlock().getRelative(bf, i).getType() == Material.DRAGON_EGG || (e.getBlock().getRelative(bf, i).getTypeId() == 122)) {
                e.setCancelled(true);
                return;
            }
        }
    }
}