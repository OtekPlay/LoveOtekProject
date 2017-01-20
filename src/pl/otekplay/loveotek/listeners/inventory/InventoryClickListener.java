package pl.otekplay.loveotek.listeners.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Core;
import pl.otekplay.loveotek.main.Cuboids;
import pl.otekplay.loveotek.main.Deposits;
import pl.otekplay.loveotek.main.Users;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        Inventory clicked = event.getClickedInventory();
        if (clicked == null) {
            return;
        }
        User user = Users.get(p.getUniqueId());
        if (!user.isStaff()) {
            Bukkit.getScheduler().runTaskLater(Core.getInstance(), () -> Deposits.check((Player) event.getWhoClicked()), 1);
            return;
        }
        if (clicked.getType() == InventoryType.CHEST) {
            Location location = p.getLocation();
            if (clicked.getHolder() instanceof Chest) {
                Chest chest = (Chest) clicked.getHolder();
                location = chest.getLocation();
            }
            if (!Cuboids.inside(location)) {
                return;
            }
            if (user.hasPermissions(UserRank.HEADADMIN)) {
                return;
            }
            event.setCancelled(true);
            return;
        }
        InventoryHolder holder = clicked.getHolder();
        if (holder == p) {
            return;
        }
        if (!user.hasPermissions(UserRank.HEADADMIN)) {
            event.setCancelled(true);
        }
    }
}
