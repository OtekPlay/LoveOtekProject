package pl.otekplay.loveotek.listeners.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.otekplay.loveotek.main.*;
import pl.otekplay.loveotek.storage.RankingSettings;
import pl.otekplay.loveotek.utils.TagUtil;

public class PlayerJoinListener implements Listener {


    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player p = event.getPlayer();
        TagUtil.createBoard(p);
        if (!Users.is(p.getUniqueId())) {
            Users.register(p.getUniqueId(), p.getName());
            Rankings.register(p.getUniqueId(), RankingSettings.RANKING_START_POINTS, RankingSettings.RANKING_START_KILLS, RankingSettings.RANKING_START_DEATHS, RankingSettings.RANKING_START_ASSISTS);
            Deposits.register(p.getUniqueId());
        }
        Drops.register(p.getUniqueId());
        Combats.register(p.getUniqueId());
        Chat.register(p.getUniqueId());
        TagUtil.updateBoard(p);
        Vanish.join(p);
    }
}
