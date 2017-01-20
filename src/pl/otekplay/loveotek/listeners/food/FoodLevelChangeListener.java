package pl.otekplay.loveotek.listeners.food;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.main.Users;

public class FoodLevelChangeListener implements Listener {


    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent e) {
        Player p = (Player) e.getEntity();
        User user = Users.get(p.getUniqueId());
        if(user.isStaff()){
            e.setFoodLevel(20);
            return;
        }
    }
}
