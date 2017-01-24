package pl.otekplay.loveotek.listeners.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import pl.otekplay.loveotek.utils.LocationUtil;

public class PlayerBucketEmptyListener implements Listener {


    @EventHandler
    public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent event){
        Player p = event.getPlayer();
        Location loc = event.getBlockClicked().getLocation();
        if (LocationUtil.canBuild(p, loc)) {
            event.setCancelled(true);
            return;
        }
    }
}