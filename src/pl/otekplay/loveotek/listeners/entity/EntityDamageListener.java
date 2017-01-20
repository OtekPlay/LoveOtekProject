package pl.otekplay.loveotek.listeners.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Users;

public class EntityDamageListener implements Listener {


    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event){
        Entity entity = event.getEntity();
        if(entity instanceof Player == false){
            return;
        }
        if(event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK){
            return;
        }
        Player player = (Player) entity;
        User user = Users.get(player.getUniqueId());
        if(!user.hasPermissions(UserRank.HELPER)){
            return;
        }
        event.setCancelled(true);
    }

}
