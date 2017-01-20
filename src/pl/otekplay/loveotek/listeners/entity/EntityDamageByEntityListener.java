package pl.otekplay.loveotek.listeners.entity;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.main.Combats;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.GlobalSettings;

public class EntityDamageByEntityListener implements Listener {


    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player == false) {
            return;
        }
        Player p = (Player) e.getEntity();
        Player dmg = null;
        if (e.getDamager() instanceof Player) {
            dmg = (Player) e.getDamager();
        } else if (e.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) e.getDamager();
            if (arrow.getShooter() instanceof Player == false) {
                return;
            }
            dmg = (Player) arrow.getShooter();
        }
        if (dmg == null) {
            entityAttack(p, e.getDamager(), e);
            return;
        }
        playerAttack(p, dmg, e);
    }

    private void playerAttack(Player p, Player dmg, EntityDamageByEntityEvent e) {
        User damager = Users.get(dmg.getUniqueId());
        if (damager.isStaff()) {
            Replacer.build(GlobalSettings.MESSAGE_ADMIN_CANT_ATTACK).send(damager);
            e.setCancelled(true);
            return;
        }
        User user = Users.get(p.getUniqueId());
        if (user.isStaff()) {
            damager.sendMessage(GlobalSettings.MESSAGE_ADMIN_GOD_MODE);
            e.setCancelled(true);
            return;
        }
        Combats.get(p.getUniqueId()).attack(dmg.getUniqueId(), (int) e.getDamage());
    }

    private void entityAttack(Player p, Entity entity, EntityDamageByEntityEvent e) {
        User user = Users.get(p.getUniqueId());
        if (!user.isStaff()) {
            return;
        }
        e.setCancelled(true);
    }
}