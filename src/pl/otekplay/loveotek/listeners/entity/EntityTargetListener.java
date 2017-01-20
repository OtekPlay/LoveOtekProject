package pl.otekplay.loveotek.listeners.entity;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.main.Users;

public class EntityTargetListener implements Listener {


    @EventHandler
    public void onEntityTargetEvent(EntityTargetEvent event){
       if(event.getTarget() instanceof Player == false){
           return;
       }
        Player player = (Player) event.getTarget();
        User user = Users.get(player.getUniqueId());
        if(user.isStaff()){
            event.setCancelled(true);
        }
    }
}
