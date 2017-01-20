package pl.otekplay.loveotek.listeners.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.otekplay.loveotek.basic.ChatUser;
import pl.otekplay.loveotek.basic.Replacer;
import pl.otekplay.loveotek.basic.User;
import pl.otekplay.loveotek.enums.UserRank;
import pl.otekplay.loveotek.main.Chat;
import pl.otekplay.loveotek.main.Users;
import pl.otekplay.loveotek.storage.ChatSettings;

public class AsyncPlayerChatListener implements Listener {


    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        User user = Users.get(p.getUniqueId());
        if (event.getMessage().startsWith("#")) {
            if (user.hasGuild()) {
                event.setMessage(Replacer.build(event.getMessage()).add("#", "").get()[0]);
                Replacer.build(ChatSettings.MESSAGE_CHAT_GUILD_FORMAT)
                        .add("%PLAYER%", user.getName())
                        .add("%MESSAGE%", event.getMessage()).send(user.getGuild());
                event.setCancelled(true);
                return;
            }
        }
        if (!user.hasPermissions(UserRank.HELPER)) {
            if (!Chat.mode()) {
                p.sendMessage(ChatSettings.MESSAGE_CHAT_DISABLED);
                event.setCancelled(true);
            }
            ChatUser chat = Chat.get(p.getUniqueId());
            if (!chat.canSend()) {
                long time = System.currentTimeMillis() - chat.getLastMessage();
                int seconds = (int) (time / 1000);
                seconds = (int) ((ChatSettings.CHAT_COOLDOWN_TIME / 1000) - seconds);
                Replacer.build(ChatSettings.MESSAGE_CHAT_BLOCKED_TIME).add("%time%", seconds + " ").send(p);
                event.setCancelled(true);
                return;
            }
            if (!Chat.valid(event.getMessage())) {
                p.sendMessage(ChatSettings.MESSAGE_CHAT_BLOCKED_MESSAGE);
                event.setCancelled(true);
                return;
            }
            chat.setLastMessage(System.currentTimeMillis());
        }
        Replacer replacer = Replacer.build(ChatSettings.MESSAGE_CHAT_GLOBAL_FORMAT);
        replacer.add("%GROUP%", user.getRank() == UserRank.PLAYER ? "" : user.getRank().getPrefix());
        replacer.add("%PLAYER%", user.getName());
        if(!user.hasPermissions(UserRank.MODERATOR)) {
            replacer.add("%POINTS%", user.getRanking().getPoints() + "");
        }else{
            replacer.add("[%POINTS%]","");
        }
        replacer.add("%MESSAGE%", event.getMessage()).broadcast();
        event.setCancelled(true);
    }
}
