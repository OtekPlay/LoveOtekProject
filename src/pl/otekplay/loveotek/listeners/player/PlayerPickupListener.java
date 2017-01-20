package pl.otekplay.loveotek.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import pl.otekplay.loveotek.main.Core;
import pl.otekplay.loveotek.main.Deposits;
import pl.otekplay.loveotek.main.Vanish;

public class PlayerPickupListener implements Listener {


    @EventHandler
    public void onPlayerPickupEvent(PlayerPickupItemEvent event) {
        Player p = event.getPlayer();
        if (Vanish.has(p)) {
            event.setCancelled(true);
            return;
        }
        Bukkit.getScheduler().runTaskLater(Core.getInstance(), () -> Deposits.check(event.getPlayer()), 1);
    }
}
