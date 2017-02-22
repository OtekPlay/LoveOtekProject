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
        if (!CombatSettings.TNT_ENABLED) {
            event.setCancelled(true);
            return;
        }
        if (CombatSettings.TIME_HOUR_TNT_START > atm && CombatSettings.TIME_HOUR_TNT_END < atm) {
            event.setCancelled(true);
            return;
        }
        event.blockList().removeIf(block -> block.getType() == Material.DRAGON_EGG);
        Location location = event.getLocation();
        Cuboid cuboid;
        if (!Cuboids.inside(location)) {
            event.setCancelled(true);
            return;
        }
        cuboid = Cuboids.cub(location);
        if (!cuboid.isGuildTerrain()) {
            event.setCancelled(true);
            return;
        }
        Guild guild = Guilds.tag(cuboid.getKey());

        List<Location> locations = SpaceUtil.getSphere(location, CombatSettings.TNT_EXPLODE_RADIUS, CombatSettings.TNT_EXPLODE_RADIUS, false, true, 0);
        for (Location loc : locations) {
            if (cuboid == null) {
                cuboid = Cuboids.cub(loc);
            }
            Block block = loc.getBlock();
            if (!CombatSettings.HARDER_BLOCKS.contains(block.getTypeId())) {
                continue;
            }
            if (!RandomUtil.getChance(CombatSettings.HARDER_BLOCK_EXPLOSION_CHANCE)) {
                continue;
            }
            block.setType(Material.AIR);
        }
    }
}
