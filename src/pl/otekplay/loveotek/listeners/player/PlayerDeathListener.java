package pl.otekplay.loveotek.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import pl.otekplay.loveotek.enums.BackupType;
import pl.otekplay.loveotek.main.Backups;
import pl.otekplay.loveotek.main.Combats;
import pl.otekplay.loveotek.main.Core;

public class PlayerDeathListener implements Listener {



    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event){
        event.setDeathMessage(null);
        Player p = event.getEntity();
        Bukkit.getScheduler().runTaskAsynchronously(Core.getInstance(), () -> {
            Combats.get(p.getUniqueId()).dead();
            Backups.save(p.getUniqueId(), BackupType.DEATH);
        });
    }
}
