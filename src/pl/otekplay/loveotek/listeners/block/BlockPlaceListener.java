package pl.otekplay.loveotek.listeners.block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import pl.otekplay.loveotek.enums.GeneratorType;
import pl.otekplay.loveotek.main.Generators;
import pl.otekplay.loveotek.storage.GeneratorSettings;
import pl.otekplay.loveotek.utils.LocationUtil;

public class BlockPlaceListener implements Listener {


    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        Player p = event.getPlayer();
        Block block = event.getBlock();
        Location loc = block.getLocation();
        if (LocationUtil.canBuild(p, loc)) {
            event.setCancelled(true);
            return;
        }
        checkGenerator(p,event.getBlockPlaced());
    }

    private void checkGenerator(Player p, Block b) {
        ItemStack itemStack = p.getItemInHand();
        if(itemStack == null){
            return;
        }
        if(itemStack.getType() != Material.ENDER_STONE){
            return;
        }
        if(itemStack.getItemMeta() == null){
            return;
        }
        if(itemStack.getItemMeta().getDisplayName() == null){
            return;
        }
        if(itemStack.getItemMeta().getDisplayName().isEmpty()){
            return;
        }
        GeneratorType type = null;
        if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(GeneratorSettings.GENERATOR_ITEMSTACK_NAME_OBSIDIAN)){
            type = GeneratorType.OBSIDIAN;
        }else if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(GeneratorSettings.GENERATOR_ITEMSTACK_NAME_STONE)){
            type = GeneratorType.STONE;
        }
        Generators.register(b.getLocation(), type).repair();
    }
}
