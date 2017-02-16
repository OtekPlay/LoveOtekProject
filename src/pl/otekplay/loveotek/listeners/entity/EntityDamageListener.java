package pl.otekplay.loveotek.listeners.entity;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.util.Vector;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Users;

public class EntityDamageListener implements Listener {


    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.CREEPER) {

            Block block = entity.getLocation().clone().subtract(0, 1, 0).getBlock();
            if (block.getType() == Material.REDSTONE_BLOCK) {
                entity.setVelocity(entity.getLocation().getDirection().multiply(10.0).add(new Vector(0, 1.5, 0)));
                return;
            }
        }
        if (entity instanceof Player == false) {
            return;
        }
        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            return;
        }
        Player player = (Player) entity;
        User user = Users.get(player.getUniqueId());
        if (!user.hasPermissions(UserRank.HELPER)) {
            return;
        }
        event.setCancelled(true);
    }

}
