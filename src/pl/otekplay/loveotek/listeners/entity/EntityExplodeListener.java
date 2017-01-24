package pl.otekplay.loveotek.listeners.entity;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import pl.otekplay.loveotek.basic.Cuboid;
import pl.otekplay.loveotek.basic.Guild;
import pl.otekplay.loveotek.main.Cuboids;
import pl.otekplay.loveotek.main.Guilds;
import pl.otekplay.loveotek.storage.CombatSettings;
import pl.otekplay.loveotek.utils.RandomUtil;
import pl.otekplay.loveotek.utils.SpaceUtil;

import java.util.Calendar;
import java.util.List;

public class EntityExplodeListener implements Listener {


    @EventHandler
    public void onEntityExplodeEvent(EntityExplodeEvent event) {
        int atm = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if(atm < CombatSettings.TIME_HOUR_TNT_START || atm > CombatSettings.TIME_HOUR_TNT_END || !CombatSettings.TNT_ENABLED){
            event.setCancelled(true);
            return;
        }
        Location location = event.getLocation();
        Cuboid cuboid = null;
        if (Cuboids.inside(location)) {
             cuboid = Cuboids.cub(location);
            if (!cuboid.isGuildTerrain()) {
                return;
            }
            Guild guild = Guilds.tag(cuboid.getKey());
        }
        List<Location> locations = SpaceUtil.getSphere(location, CombatSettings.TNT_EXPLODE_RADIUS, CombatSettings.TNT_EXPLODE_RADIUS, false, true, 0);
        for (Location loc : locations) {
            if(cuboid == null){
                cuboid = Cuboids.cub(loc);
            }
            Block block = loc.getBlock();
            if(block.getType() == Material.BEDROCK){
                continue;
            }
            if (CombatSettings.HARDER_BLOCKS.contains(block.getTypeId())) {
                continue;
            }
            if (!RandomUtil.getChance(CombatSettings.HARDER_BLOCK_EXPLOSION_CHANCE)) {
                continue;
            }
            block.setType(Material.AIR);

        }
    }
}
