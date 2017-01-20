package pl.otekplay.loveotek.listeners.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.otekplay.loveotek.main.Chat;
import pl.otekplay.loveotek.main.Combats;
import pl.otekplay.loveotek.main.Drops;
import pl.otekplay.loveotek.utils.TagUtil;

public class PlayerQuitListener implements Listener {


    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e){
        e.setQuitMessage(null);
        Player p =e.getPlayer();
        if(Combats.get(p.getUniqueId()).hasValidAttackers()){
            p.damage(20.0);
        }
        Drops.unregister(p.getUniqueId());
        Combats.unregister(p.getUniqueId());
        Chat.unregister(p.getUniqueId());
        TagUtil.removeBoard(p);

    }
}
