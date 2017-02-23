package pl.otekplay.loveotek.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import pl.otekplay.loveotek.basic.Ban;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.main.Bans;
import pl.otekplay.loveotek.storage.BanSettings;
import pl.otekplay.loveotek.utils.TimeUtil;

public class AsyncPlayerPreLoginListener implements Listener {


    @EventHandler
    public void onAsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent event) {
        String name = event.getName();
        if (Bans.is(name)) {
            Ban b = Bans.get(name);
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Replacer.build(BanSettings.MESSAGE_YOU_GOT_BAN).add("%admin%", b.getBanner()).add("%reason%", b.getReason()).add("%time%", TimeUtil.getPrettyDate(b.getTime())).get()[0]);
            return;
        }
    }
}
