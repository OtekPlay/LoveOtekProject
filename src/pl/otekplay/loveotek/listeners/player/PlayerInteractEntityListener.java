package pl.otekplay.loveotek.listeners.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Users;

public class PlayerInteractEntityListener implements Listener {


    @EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event){
        Player p = event.getPlayer();
        User user = Users.get(p.getUniqueId());
        if(!user.hasPermissions(UserRank.HEADADMIN)){
            return;
        }
        event.getRightClicked().setPassenger(p);
    }
}
